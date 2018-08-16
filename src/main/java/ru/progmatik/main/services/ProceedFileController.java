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
import ru.progmatik.main.DAO.HouseDAOBatchInsert;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

@Service
public class ProceedFileController {

    @Value("${batchsize:1000}")
    private int BATCH_SIZE;

    private static Logger log = LoggerFactory.getLogger(ProceedFileController.class);

    private static final File UNPACKFOLDER = new File("unpack");

    public void proceedFiasArchFile(final File fiasArchFile){

        try{
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
                            proceedAddrObj(sourceFile);
                        }
                        if(filename.contains("AS_HOUSE")){
                            proceedHouses(sourceFile);
                        }
                    }
                }
                clearUnpackFolder();
            }
        } catch (IOException | RarException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Autowired
    AddrObjDAOBatchInsert addrObjDAOBatchInsert;

    private void proceedAddrObj(File sourceFile){
        long totalCnt = 0;

        try(XMLFileReader xmlFileReader = new XMLFileReader(sourceFile, ru.fias.Object.class)) {
            long start_nanotime = System.nanoTime();


            // бежим по файлу и создаем объекты
            while (xmlFileReader.hasNext()) {
//                log.info("Address objects read started...");

                List<Object> objectList = xmlFileReader.readAddrObjFromStream(BATCH_SIZE);

                totalCnt = totalCnt + objectList.size();

                // insert into base here
                addrObjDAOBatchInsert.insertAddrObjArray(objectList);

                long end_nanotime = System.nanoTime();
                long duration = ((end_nanotime - start_nanotime) / 1000000000);
                long diff = 0;
                if (duration > 0) {
                    diff = totalCnt / duration;
                }

                log.info(String.format("Address objects inserted: %d; Avg. speed: %d records/sec", totalCnt, diff));
            }
            log.info("Address objects insert finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    HouseDAOBatchInsert houseDAOBatchInsert;

    private void proceedHouses(File sourceFile) {
        long totalCnt = 0;

        try(XMLFileReader xmlFileReader = new XMLFileReader(sourceFile, House.class)){
            long start_nanotime = System.nanoTime();

            // бежим по файлу и создаем объекты
            while (xmlFileReader.hasNext()) {
//                log.info("House objects read started...");

                List<House> houseList = xmlFileReader.readHousesFromStream(BATCH_SIZE);


                totalCnt = totalCnt + houseList.size();

                // insert into base here
                houseDAOBatchInsert.insertHouseArray(houseList);

                long end_nanotime = System.nanoTime();
                long duration = ((end_nanotime - start_nanotime) / 1000000000);
                long diff = 0;
                if (duration > 0) {
                    diff = totalCnt / duration;
                }

                log.info(String.format("House objects inserted: %d; Avg. speed: %d records/sec", totalCnt, diff));
            }

            log.info("House objects insert finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean extractArchFile(File fiasRarFile) throws IOException, RarException {
        if(fiasRarFile == null) return false;

        if (!Files.exists(fiasRarFile.toPath())) {
            throw new IOException(String.format("File not found: %s", fiasRarFile.toPath()));
        }

        prepareUnpackFolder();

        log.info("extract file" + fiasRarFile.toPath());

        Junrar.extract(fiasRarFile, UNPACKFOLDER);

        log.info(fiasRarFile.toPath() + " extracted");

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
        log.info("Unpack folder cleared");
    }
}
