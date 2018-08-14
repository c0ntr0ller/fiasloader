package ru.progmatik.main.services;

import ru.fias.Object;
import ru.fias.House;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLFileReader implements AutoCloseable {

    private BufferedInputStream bis;
    private XMLStreamReader xmlStreamReader;
    private Unmarshaller jaxbUnmarshaller;

    public XMLFileReader(File inFile) throws IOException, XMLStreamException{

        // подготовка к чтению файла
        bis = new BufferedInputStream(new FileInputStream(inFile));

        XMLInputFactory factory = XMLInputFactory.newInstance();

        xmlStreamReader = factory.createXMLStreamReader(bis);
    }


    public List<Object> readAddrObjFromStream(int arraySize) throws XMLStreamException, JAXBException {

        if(xmlStreamReader == null || !xmlStreamReader.hasNext()){
            return null;
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(Object.class);

        jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // лист для результата
        List<Object> addrObjList = new ArrayList<>();
        // указатель на текущую ноду
        Object addrObj = null;
        // счетчик чтений
        int count = 0;

        // если в ридере еще есть что читать
        while (xmlStreamReader.hasNext()) {
            try {
                // читаем следующий
                xmlStreamReader.next();
                // если элемент стартовый и его имя node
                if (xmlStreamReader.isStartElement() &&
                        xmlStreamReader.getLocalName().equalsIgnoreCase("Object")) {
                    // создаем объект тип LocalNode
                    addrObj = (Object) jaxbUnmarshaller.unmarshal(xmlStreamReader);
                    // добавляем в лист ноду
                    addrObjList.add(addrObj);
                    // инкрементируем счетчик
                    count++;
                    if(count%arraySize == 0){
                        return addrObjList;
                    }
                }
                // если достигли установленного размера - возвращаем лист
            } catch (XMLStreamException e1) {
                e1.printStackTrace();
            }
        }
        // закрываем ридер, если больше нечего читать
        xmlStreamReader.close();
        // возвращаем лист с остатком
        return addrObjList;
    }

    public List<House> readHousesFromStream(int arraySize) throws XMLStreamException, JAXBException {

        if(xmlStreamReader == null || !xmlStreamReader.hasNext()){
            return null;
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(House.class);

        jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // лист для результата
        List<House> houseList = new ArrayList<>();
        // указатель на текущую ноду
        House house = null;
        // счетчик чтений
        int count = 0;

        // если в ридере еще есть что читать
        while (xmlStreamReader.hasNext()) {
            try {
                // читаем следующий
                xmlStreamReader.next();
                // если элемент стартовый и его имя node
                if (xmlStreamReader.isStartElement() &&
                        xmlStreamReader.getLocalName().equalsIgnoreCase("House")) {
                    // создаем объект тип LocalNode
                    house = (House) jaxbUnmarshaller.unmarshal(xmlStreamReader);
                    // добавляем в лист ноду
                    houseList.add(house);
                    // инкрементируем счетчик
                    count++;
                    if(count%arraySize == 0){
                        return houseList;
                    }
                }
                // если достигли установленного размера - возвращаем лист
            } catch (XMLStreamException e1) {
                e1.printStackTrace();
            }
        }
        // закрываем ридер, если больше нечего читать
        xmlStreamReader.close();
        // возвращаем лист с остатком
        return houseList;
    }

    @Override
    public void close() throws Exception {
        bis.close();
        xmlStreamReader.close();
    }

    public boolean hasNext() {
        try {
            return xmlStreamReader.hasNext();
        } catch (XMLStreamException e) {
            e.printStackTrace();
            return false;
        }
    }
}