package ru.progmatik.main.other;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * check URL for redirect an return old or new URL
     * @param url
     * @return
     * @throws MalformedURLException
     */
    public static String checkUrlFoRedirect(String url) throws IOException {
        URL hh= new URL(url);
        URLConnection connection = hh.openConnection();
        int code = ((HttpURLConnection)connection).getResponseCode();
        if(code == HttpURLConnection.HTTP_MOVED_PERM || code == HttpURLConnection.HTTP_MOVED_TEMP) {
            String redirect = connection.getHeaderField("Location");
            if (redirect != null) {
                url = redirect;
            }
        }
        return url;
    }


    /**
     * метод берет файлы из указанной директории
     * в данном проекте берутся только файлы с расширением rar и с именем из цифр (номер версии файла)
     * @param dir папка для получения списка файлов ФИАС
     * @return
     */
    public static Map<Integer,File> getDirFiles(String dir, String extension){
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
                    && FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(extension)){
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
