package ru.progmatik.main.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import ru.progmatik.main.messagehandler.ClientMessageHandler;
import ru.progmatik.main.messagehandler.DefaultHandlerResolver;
import ru.progmatik.main.webclient.FiasClient;

import javax.net.ssl.SSLContext;
import javax.xml.ws.handler.Handler;
import java.util.ArrayList;
import java.util.List;

/**
 * класс конфигурации для бинов маршаллинга
 */
@Configuration
public class FiasConfiguration {

    @Value("${client.default-uri}")
    private String fiasurl;

    @Value("${client.ssl.trust-store}")
    private Resource trustStore;

    @Value("${client.ssl.trust-store-password}")
    private String trustStorePassword;

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("fias.wsdl");
        return jaxb2Marshaller;
    }

    @Bean
    public FiasClient fiasClient(Jaxb2Marshaller marshaller) throws Exception {
        FiasClient client = new FiasClient();

        client.setDefaultUri(fiasurl);
        client.setUnmarshaller(marshaller);
        client.setMarshaller(marshaller);
//        client.setMessageSender(httpComponentsMessageSender());

        return client;
    }

//    @Bean
//    public HttpComponentsMessageSender httpComponentsMessageSender() throws Exception {
//        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
//        httpComponentsMessageSender.setHttpClient(httpClient());
//
//        return httpComponentsMessageSender;
//    }
//
//    public HttpClient httpClient() throws Exception {
//        return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory())
//                .addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor()).build();
//    }
//
//    public SSLConnectionSocketFactory sslConnectionSocketFactory() throws Exception {
//        // NoopHostnameVerifier essentially turns hostname verification off as otherwise following error
//        // is thrown: java.security.cert.CertificateException: No name matching localhost found
//        return new SSLConnectionSocketFactory(sslContext(), NoopHostnameVerifier.INSTANCE);
//    }
//
//    public SSLContext sslContext() throws Exception {
//        return SSLContextBuilder.create()
//                .loadTrustMaterial(trustStore.getFile(), trustStorePassword.toCharArray()).build();
//    }


    public DefaultHandlerResolver handlerResolver(){
        final DefaultHandlerResolver defaultHandlerResolver = new DefaultHandlerResolver();
        final ClientMessageHandler clientMessageHandler = new ClientMessageHandler();
        List<Handler> handlerList = new ArrayList<>();
        handlerList.add(clientMessageHandler);
        defaultHandlerResolver.setHandlerList(handlerList);
        return defaultHandlerResolver;
    }
}
