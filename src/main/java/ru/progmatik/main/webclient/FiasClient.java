package ru.progmatik.main.webclient;

import fias.wsdl.DownloadFileInfo;
import fias.wsdl.GetAllDownloadFileInfo;
import fias.wsdl.GetAllDownloadFileInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

/**
 * веб-клиент для получения списка файлов ФИАС
 */
public class FiasClient extends WebServiceGatewaySupport {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${client.default-uri}")
    private String fiasurl;

    public List<DownloadFileInfo> getAllDownloadFileList(){

        GetAllDownloadFileInfo request = new GetAllDownloadFileInfo();

        LOGGER.info("Requesting FIAS files list");

        final WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

        GetAllDownloadFileInfoResponse response = (GetAllDownloadFileInfoResponse) webServiceTemplate.
                marshalSendAndReceive(fiasurl, request,
                        new SoapActionCallback(
                                fiasurl + "/GetAllDownloadFileInfo"));


        return response.getGetAllDownloadFileInfoResult().getDownloadFileInfo();
    }
}
