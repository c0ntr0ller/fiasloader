package ru.progmatik.main.webclient;

import fias.wsdl.DownloadFileInfo;
import fias.wsdl.GetAllDownloadFileInfo;
import fias.wsdl.GetAllDownloadFileInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.MessageContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

import static ru.progmatik.main.other.UtilClass.checkUrlFoRedirect;

/**
 * веб-клиент для получения списка файлов ФИАС
 */
public class FiasClient extends WebServiceGatewaySupport {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${client.default-uri}")
    private String fiasurl;

    @Value("${client.readTimeoutMsec:1000}")
    private long readTimeoutMsec;

    @Value("${client.connTimeoutMsec:1000}")
    private int connTimeoutMsec;

    public List<DownloadFileInfo> getAllDownloadFileList() throws SOAPException, IOException {


        LOGGER.info("Requesting FIAS files list");

        GetAllDownloadFileInfo request = new GetAllDownloadFileInfo();


        MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory(msgFactory);
        WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
        webServiceTemplate.setMessageFactory(saajSoapMessageFactory);

        WebServiceMessageSender[] senders = webServiceTemplate.getMessageSenders();

        for (WebServiceMessageSender sender: senders)
        {
            try
            {
                HttpUrlConnectionMessageSender httpSender = (HttpUrlConnectionMessageSender) sender;
                httpSender.setReadTimeout(Duration.ofMillis(readTimeoutMsec));
                httpSender.setConnectionTimeout(Duration.ofMillis(connTimeoutMsec));
            }
            catch (ClassCastException|NumberFormatException cex)
            {
                logger.warn("Cannot set WS timeout: " + cex.getMessage());
            }
        }

        String realURL = checkUrlFoRedirect(fiasurl);

        GetAllDownloadFileInfoResponse response = (GetAllDownloadFileInfoResponse) webServiceTemplate.
                marshalSendAndReceive(realURL, request,
                        new SoapActionCallback(
                                realURL + "/GetAllDownloadFileInfo")
//                        {
//                            @Override
//                            public void doWithMessage(WebServiceMessage message) throws IOException {
//                                SoapMessage msg = (SoapMessage)message;
//                                try (
//                                    FileOutputStream outputStream = new FileOutputStream("/home/bek/tmp/" + UUID.randomUUID().toString() + "soap.xml")) {
//                                    msg.writeTo(outputStream);
//                                    outputStream.flush();
//                                    outputStream.close();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
                        );


        return response.getGetAllDownloadFileInfoResult().getDownloadFileInfo();
    }
}
