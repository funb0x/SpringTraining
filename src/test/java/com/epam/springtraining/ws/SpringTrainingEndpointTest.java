package com.epam.springtraining.ws;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

public class SpringTrainingEndpointTest {
    private static WebServiceTemplate webServiceTemplate;


    @BeforeClass
    public static void setup() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        webServiceTemplate = new WebServiceTemplate(jaxb2Marshaller, jaxb2Marshaller);
        webServiceTemplate.setDefaultUri("http://localhost:8080/ws");
    }

    @Test
    public void testUserByIdRequest() {
        String MESSAGE = "<userByIdRequest><id>1</id></userByIdRequest>";
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);
    }

}
