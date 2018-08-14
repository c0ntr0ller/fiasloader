package ru.progmatik.main.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.progmatik.main.UtilClass;

import java.io.File;
import java.util.Map;

@Service
public class ProceedFilesSheduler {
    private static Logger log = LoggerFactory.getLogger(ProceedFilesSheduler.class);
    private static final int timeRate = 24*60*60*1000;

    @Value("${archDir:}")
    String archDir;

    @Value("${workDir:}")
    String workDir;

    @Autowired
    ProceedFileController proceedFileController;

    @Scheduled(fixedRate = timeRate) // every day
    public void proceedFiasFiles() {
        // получаем список файлов в рабочей папке

        if(workDir == null || workDir.isEmpty()) {
            workDir = System.getProperty("user.dir") + File.separatorChar + "work";
        }
        Map<Integer,File> workFilesMap = UtilClass.getDirFiles(workDir, "rar");

        // обрабатываем их с переносом в архив
        for (File file : workFilesMap.values()) {
            proceedFileController.proceedFiasArchFile(file);
        }
    }
}
