package ru.progmatik.main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyURLToFile;

public class UtilClass {
    /**
     * check string is integer
     * @param s строка для проверки
     * @return
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    /**
     * download file from URL
     * @param filename имя для сохранения
     * @param url URL файла
     */
    public static void downLoadFileFromURL(String filename, String url) throws IOException {
        File targetFile = new File(filename);

        boolean canWrite = true;
        if(targetFile.exists()){
            canWrite = targetFile.delete();
        }

        if (canWrite) {
            copyURLToFile(
                    new URL(url),
                    new File(filename),
                    60*60*1000,
                    12*60*60*1000);
        }
    }
}
