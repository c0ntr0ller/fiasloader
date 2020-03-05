package ru.progmatik.main.services;

import com.github.junrar.Junrar;
import com.github.junrar.exception.RarException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fias.Object;
import ru.fias.House;
import ru.progmatik.main.DAO.AddrObjDAOBatchInsert;
import ru.progmatik.main.DAO.DBService;
import ru.progmatik.main.DAO.HouseDAOBatchInsert;
import ru.progmatik.main.other.XMLFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * сервис предназначен для обработки скачанных файлов
 */
@Service
public class ProceedFileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${batchsize:1000}")
    private int BATCH_SIZE;


    private static final File UNPACKFOLDER = new File("unpack");

    @Value("${archDir:archive}")
    String archDir;

    public void proceedFiasRarFile(final File fiasArchFile){

        try(Connection connection = dbService.getConnection()){

            boolean unpackSuccess = true;

            if(fiasArchFile != null){
                unpackSuccess = extractArchFile(fiasArchFile);
            }
            if(unpackSuccess){
                for (File sourceFile: Objects.requireNonNull(UNPACKFOLDER.listFiles())) {
                    if(!sourceFile.isDirectory()
                            && FilenameUtils.getExtension(sourceFile.getName()).equalsIgnoreCase("xml")) {
                        String filename = FilenameUtils.getName(sourceFile.getName());
                        if(filename.contains("AS_ADDROBJ")){
                            proceedAddrObj(sourceFile, connection);
                        }
                        if(filename.contains("AS_HOUSE")){
                            proceedHouses(sourceFile, connection);
                        }
                    }
                }
                clearUnpackFolder();
            }
            if(fiasArchFile != null){
                fiasArchFile.renameTo(new File(archDir + File.separatorChar + fiasArchFile.getName()));
            }
        } catch (Exception e) {
            logger.error("proceedFiasRarFile error", e);
            e.printStackTrace();
        }
    }


    @Autowired
    AddrObjDAOBatchInsert addrObjDAOBatchInsert;

    @Autowired
    DBService dbService;

    private void proceedAddrObj(File sourceFile, Connection connection){
        long totalCnt = 0;

        try(XMLFileReader xmlFileReader = new XMLFileReader(sourceFile, ru.fias.Object.class)) {

            long start_nanotime = System.nanoTime();

            // бежим по файлу и создаем объекты
            while (xmlFileReader.hasNext()) {
//                logger.info("Address objects read started...");

                List<Object> objectList = xmlFileReader.readAddrObjFromStream(BATCH_SIZE);

                totalCnt = totalCnt + objectList.size();

                // insert into base here
                addrObjDAOBatchInsert.insertAddrObjArray(objectList, connection);

                long end_nanotime = System.nanoTime();
                long duration = ((end_nanotime - start_nanotime) / 1000000000);
                long diff = 0;
                if (duration > 0) {
                    diff = totalCnt / duration;
                }

                logger.info(String.format("Address objects inserted: %d; Avg. speed: %d records/sec", totalCnt, diff));
            }
            logger.info("Address objects insert finished");
        } catch (Exception e) {
            logger.error("proceedAddrObj error", e);
            e.printStackTrace();
        }
    }

    @Autowired
    HouseDAOBatchInsert houseDAOBatchInsert;

    private void proceedHouses(File sourceFile, Connection connection) {
        long totalCnt = 0;

        try(XMLFileReader xmlFileReader = new XMLFileReader(sourceFile, House.class)){
            long start_nanotime = System.nanoTime();

            // бежим по файлу и создаем объекты
            while (xmlFileReader.hasNext()) {
//                logger.info("House objects read started...");

                List<House> houseList = xmlFileReader.readHousesFromStream(BATCH_SIZE);


                totalCnt = totalCnt + houseList.size();

                // insert into base here
                houseDAOBatchInsert.insertHouseArray(houseList, connection);

                long end_nanotime = System.nanoTime();
                long duration = ((end_nanotime - start_nanotime) / 1000000000);
                long diff = 0;
                if (duration > 0) {
                    diff = totalCnt / duration;
                }

                logger.info(String.format("House objects inserted: %d; Avg. speed: %d records/sec", totalCnt, diff));
            }

            logger.info("House objects insert finished");
        } catch (Exception e) {
            logger.error("proceedHouses error", e);
            e.printStackTrace();
        }
    }

    private boolean extractArchFile(File fiasArchFile) throws IOException, RarException {
        if(fiasArchFile == null) return false;

        if (!Files.exists(fiasArchFile.toPath())) {
            throw new IOException(String.format("File not found: %s", fiasArchFile.toPath()));
        }

        prepareUnpackFolder();

        logger.info("extract file" + fiasArchFile.toPath());

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fiasArchFile));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = new File(UNPACKFOLDER, zipEntry.getName());
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        logger.info(fiasArchFile.toPath() + " extracted");

        return true;
    }

    private void prepareUnpackFolder() {
        if (!UNPACKFOLDER.exists()) {
            UNPACKFOLDER.mkdir();
        }

        clearUnpackFolder();
    }
    private void clearUnpackFolder(){
        for(File file : Objects.requireNonNull(UNPACKFOLDER.listFiles())){
            file.delete();
        }
        logger.info("Unpack folder cleared");
    }
}
