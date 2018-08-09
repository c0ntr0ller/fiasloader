package ru.progmatik.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class FiasConfiguration {

    @Value("${fiasurl}")
    private String fiasurl;

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("fias.wsdl");
        return jaxb2Marshaller;
    }

    @Bean
    public FiasClient fiasClient(Jaxb2Marshaller marshaller){
        FiasClient client = new FiasClient();

        client.setDefaultUri(fiasurl);
        client.setUnmarshaller(marshaller);
        client.setMarshaller(marshaller);

        return client;
    }
}
