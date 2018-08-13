package ru.progmatik.main;

import fias.wsdl.DownloadFileInfo;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * класс работает по расписанию, скачивает список файлов ФИАС, сравнивает с имеющимися в архиве,
 * решает какие надо скачать и скачивает их
 */
@Component
public class FilesListSheduler {
    private static Logger log = LoggerFactory.getLogger(FilesListSheduler.class);

    @Autowired
    private FiasClient fiasClient;

    @Value("${archDir:}")
    String archDir;

    @Value("${workDir:}")
    String workDir;

    private List<DownloadFileInfo> fiasFilesList = new ArrayList<>();
    private Map<Integer,File> archFilesMap = new HashMap<>();
    private Map<Integer,File> workFilesMap = new HashMap<>();

    @Scheduled(fixedRate = 5000)
    public void checkAndGetFiasFiles(){

        if (fiasFilesList != null){
            fiasFilesList.clear();
        }

        fiasFilesList = fiasClient.getAllDownloadFileList();

        if(fiasFilesList == null || fiasFilesList.isEmpty()){
            log.error("Empty fias files list!");
            return;
        }

        Map<Integer,String> filesMapForDownload = getFileMapForDownload();

        // если список на скачивание непустой - запускаем скачивание
        downloadFiles(filesMapForDownload);
    }

    private void downloadFiles(Map<Integer, String> filesMapForDownload) {
        // run by sorted list of versions
        for (Integer versionId : filesMapForDownload.keySet().stream().sorted().collect(Collectors.toList())) {
            String url = filesMapForDownload.get(versionId);

            File tmpDir = new File(System.getProperty("user.dir") + File.separatorChar + "tmp");
            if(!tmpDir.exists()){
                tmpDir.mkdir();
            }

            String tmpfilename =  System.getProperty("user.dir") + File.separatorChar + "tmp" + File.separatorChar + versionId.toString() + ".rar";

            try {
                UtilClass.downLoadFileFromURL(tmpfilename, url);
                File tmpFile = new File(tmpfilename);
                if(tmpFile.exists()) {
                    tmpFile.renameTo(new File(workDir + File.separatorChar + tmpFile.getName()));
                }
            } catch (IOException e) {
                log.error("Exception while downloading file " + url);
                e.printStackTrace();
            }
        }
    }

    /**
     * get map of files and their version numbers
     * @return
     */
    private Map<Integer, String> getFileMapForDownload(){
        Map<Integer, String> fileMapForDownload = new HashMap<>();

        if(archFilesMap != null){
            archFilesMap.clear();
        }

        if(workFilesMap != null){
            workFilesMap.clear();
        }

        // получаем список файлов в архивной папке
        if(archDir == null || archDir.isEmpty()) {
            archDir = System.getProperty("user.dir") + File.separatorChar + "archive";
        }

        archFilesMap = getDirFiles(archDir);

        // получаем список файлов в папке для обработки (возможно какие-то еще не обработались либо скачаны частично)
        if(workDir == null || workDir.isEmpty()) {
            workDir = System.getProperty("user.dir") + File.separatorChar + "work";
        }
        workFilesMap = getDirFiles(workDir);

        // определяем какие файлы надо скачать

        // если обе папки пусты - возвращем только имя последнего ПОЛНОГО архива
        if(archFilesMap.isEmpty() && workFilesMap.isEmpty()){

            DownloadFileInfo totalArch  = fiasFilesList
                    .stream()
                    .max(Comparator.comparingInt(DownloadFileInfo::getVersionId)).get();
            fileMapForDownload.put(totalArch.getVersionId(), totalArch.getFiasCompleteXmlUrl());
        }else {
            // Нам нужен максимальный номер скачанной версии
            // объединяем все списки файлов в один
            workFilesMap.putAll(archFilesMap);
            // находим максимальный номер версии
            Integer maxVersion = workFilesMap.keySet().stream().max(Comparator.naturalOrder()).get();

            // все файлы с версией выше добавляем на скачивание
            for (DownloadFileInfo downloadFileInfo : fiasFilesList) {
                if(downloadFileInfo.getVersionId() > maxVersion){
                    // добавляем только ИНКРЕМЕНТЫ
                    fileMapForDownload.put(downloadFileInfo.getVersionId(), downloadFileInfo.getFiasDeltaXmlUrl());
                }
            }
        }
        return fileMapForDownload;
    }


    /**
     * метод берет файлы из указанной директории
     * в данном проекте берутся только файлы с расширением rar и с именем из цифр (номер версии файла)
     * @param dir папка для получения списка файлов ФИАС
     * @return
     */
    private Map<Integer,File> getDirFiles(String dir){
        File root= new File(dir);

        if(!root.exists()){
            root.mkdir();
        }

        Map<Integer,File> fileMap = new HashMap<>();

        File[] files = root.listFiles();
        if (files == null){
            return fileMap;
        }

        for (File file : files) {
            if(!file.isDirectory()
                    && FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("rar")){
                String filename = FilenameUtils.getName(file.getName());
                if (filename.indexOf(".") > 0)
                    filename = filename.substring(0, filename.lastIndexOf("."));
                if(UtilClass.isInteger(filename)) {
                    fileMap.put(Integer.parseInt(filename), file);
                }
            }
        }

        return fileMap;
    }


}
