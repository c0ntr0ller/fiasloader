package ru.progmatik.main;

import fias.wsdl.DownloadFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilesListSheduler {
    private static Logger log = LoggerFactory.getLogger(FilesListSheduler.class);

    @Autowired
    private FiasClient fiasClient;

    @Scheduled(fixedRate = 5000)
    public void getFilesList(){
        List<DownloadFileInfo> getAllDownloadFileList = fiasClient.getAllDownloadFileList();

        for (DownloadFileInfo downloadFileInfo : getAllDownloadFileList) {
            log.info("FiasCompleteXmlUrl = " + downloadFileInfo.getFiasCompleteXmlUrl());
        }
    }
}
