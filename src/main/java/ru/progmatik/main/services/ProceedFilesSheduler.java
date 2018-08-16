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

/**
 * класс работает по расписанию. Обнаруживает файлы в рабочей папке и запускает их в обработку
 */
@Service
public class ProceedFilesSheduler {
    private static Logger log = LoggerFactory.getLogger(ProceedFilesSheduler.class);

    @Value("${archDir:}")
    String archDir;

    @Value("${workDir:}")
    String workDir;

    @Autowired
    ProceedFileController proceedFileController;

    @Scheduled(fixedRateString = "100000") //"${proceedperiod:86400000}") // default every day
    public void proceedFiasFiles() {
        // получаем список файлов в рабочей папке

        if(workDir == null || workDir.isEmpty()) {
            workDir = "work";
        }
        Map<Integer,File> workFilesMap = UtilClass.getDirFiles(workDir, "rar");

        log.info(String.format("Files for proceed to database: $d", workFilesMap.size()));
        // обрабатываем их с переносом в архив
//        for (File file : workFilesMap.values()) {
            proceedFileController.proceedFiasArchFile(null);
//        }
    }
}
