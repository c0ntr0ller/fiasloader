package ru.progmatik.main.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.progmatik.main.other.UtilClass;

import java.io.File;
import java.util.Map;

/**
 * сервис работает по расписанию. Обнаруживает файлы в рабочей папке и запускает их в обработку
 */
@Service
public class ProceedFilesSheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${archDir:}")
    String archDir;

    @Value("${workDir:}")
    String workDir;

    @Autowired
    ProceedFileController proceedFileController;

    @Scheduled(fixedRateString = "${proceedperiod:86400000}") // default every day
    public void proceedFiasFiles() {
        // получаем список файлов в рабочей папке

        if(workDir == null || workDir.isEmpty()) {
            workDir = "work";
        }
        Map<Integer,File> workFilesMap = UtilClass.getDirFiles(workDir, "rar");

        logger.info(String.format("Files for proceed to database: %d", workFilesMap.size()));
        // обрабатываем их с переносом в архив
        for (File file : workFilesMap.values()) {
            proceedFileController.proceedFiasRarFile(file);
        }
    }
}
