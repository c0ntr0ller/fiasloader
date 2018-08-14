package ru.progmatik.main.webclient;

import fias.wsdl.DownloadFileInfo;
import fias.wsdl.GetAllDownloadFileInfo;
import fias.wsdl.GetAllDownloadFileInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

public class FiasClient extends WebServiceGatewaySupport {

    @Value("${fiasurl}")
    private String fiasurl;

    private static final Logger log = LoggerFactory.getLogger(FiasClient.class);

    public List<DownloadFileInfo> getAllDownloadFileList(){
        GetAllDownloadFileInfo request = new GetAllDownloadFileInfo();

        log.info("Requesting FIAS files list");

        GetAllDownloadFileInfoResponse response = (GetAllDownloadFileInfoResponse) getWebServiceTemplate().
                marshalSendAndReceive(fiasurl, request,
                        new SoapActionCallback(
                                fiasurl + "/GetAllDownloadFileInfo"));


        return response.getGetAllDownloadFileInfoResult().getDownloadFileInfo();
    }
}
