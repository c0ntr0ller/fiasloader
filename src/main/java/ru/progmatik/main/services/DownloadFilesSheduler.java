package ru.progmatik.main.services;

import fias.wsdl.DownloadFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.progmatik.main.webclient.FiasClient;
import ru.progmatik.main.UtilClass;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * класс работает по расписанию, скачивает список файлов ФИАС, сравнивает с имеющимися в архиве,
 * решает какие надо скачать и скачивает их
 */
@Service
public class DownloadFilesSheduler {
    private static Logger log = LoggerFactory.getLogger(DownloadFilesSheduler.class);

    @Autowired
    private FiasClient fiasClient;

    @Value("${archDir:}")
    String archDir;

    @Value("${workDir:}")
    String workDir;

    private List<DownloadFileInfo> fiasFilesList = new ArrayList<>();
    private Map<Integer,File> archFilesMap = new HashMap<>();
    private Map<Integer,File> workFilesMap = new HashMap<>();

    @Scheduled(fixedRate = 10000000) // * 10 * 60 * 1000) // every 10 minutes
    public void checkAndGetFiasFiles(){
//
//        if (fiasFilesList != null){
//            fiasFilesList.clear();
//        }
//
//        fiasFilesList = fiasClient.getAllDownloadFileList();
//
//        if(fiasFilesList == null || fiasFilesList.isEmpty()){
//            log.error("Empty fias files list!");
//            return;
//        }
//
//        Map<Integer,String> filesMapForDownload = getFileMapForDownload();
//
//        // если список на скачивание непустой - запускаем скачивание
//        downloadFiles(filesMapForDownload);
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

        archFilesMap = UtilClass.getDirFiles(archDir, "rar");

        // получаем список файлов в папке для обработки (возможно какие-то еще не обработались либо скачаны частично)
        if(workDir == null || workDir.isEmpty()) {
            workDir = System.getProperty("user.dir") + File.separatorChar + "work";
        }
        workFilesMap = UtilClass.getDirFiles(workDir, "rar");

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

}
