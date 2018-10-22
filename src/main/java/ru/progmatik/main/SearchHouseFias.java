package ru.progmatik.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fias.House;
import ru.progmatik.main.other.XMLFileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * test class for manually search house in file by FIAS
 */
public class SearchHouseFias {
    public static void main(String[] args) throws Exception {
        if(args.length < 2 || args[0] == null || args[0].isEmpty() || args[1] == null || args[1].isEmpty()){
            System.out.println("Error: Short of parameters!");
            return;
        }

        String fiasFileName = args[0];
        String fiasForSearch = args[1];


        File fiasFile = new File(fiasFileName);

        if(!fiasFile.exists()){
            System.out.println(String.format("Error: File %s not exists!", fiasFileName));
            return;
        }

        System.out.println(String.format("Search Fias %s in file %s...", fiasForSearch, fiasFileName));

        List<House> houseList = new ArrayList<>();

        int nStep = 500000;

        try (XMLFileReader xmlFileReader = new XMLFileReader(fiasFile, House.class)) {
            while (xmlFileReader.hasNext()) {

                houseList.addAll(xmlFileReader.readHousesFromStream(nStep, fiasForSearch));
                if(!houseList.isEmpty()){
                    System.out.println(String.format("Success! Fias %s in file %s found!", fiasForSearch, fiasFileName));
                    return;
                }
                else{
                    System.out.println("reading...");
                }
            }
        }

        if(houseList.isEmpty()){
            System.out.println(String.format("Fias %s in file %s not found!", fiasForSearch, fiasFileName));
        }
    }
}
