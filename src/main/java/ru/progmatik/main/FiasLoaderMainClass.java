package ru.progmatik.main;

import fias.wsdl.DownloadFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class FiasLoaderMainClass {
    private static Logger log = LoggerFactory.getLogger(FiasLoaderMainClass.class);
    public static void main(String[] args) {
        SpringApplication.run(FiasLoaderMainClass.class);
    }

//    @Bean
//    CommandLineRunner lookup(FiasClient fiasClient) {
//        return args -> {
//            List<DownloadFileInfo> getAllDownloadFileList = fiasClient.getAllDownloadFileList();
//
//            for (DownloadFileInfo downloadFileInfo : getAllDownloadFileList) {
//                log.info("FiasCompleteXmlUrl = " + downloadFileInfo.getFiasCompleteXmlUrl());
//            }
//        };
//    }
}
