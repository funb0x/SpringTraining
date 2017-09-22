package com.epam.springtraining.ws;

import beans.models.gen.ObjectFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * Created by Анна on 21.09.2017.
 */
public class SpringTrainingEndpointTest {
    private static WebServiceTemplate webServiceTemplate;


    @BeforeClass
    public static void setup() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        webServiceTemplate = new WebServiceTemplate(jaxb2Marshaller, jaxb2Marshaller);
        webServiceTemplate.setDefaultUri("http://localhost:8080/ws");
    }

    @Test
    public void test() {
///{http://schemas.xmlsoap.org/soap/envelope/}Envelope
//        String MESSAGE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
//                "\t\t\t\t  xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\">\n" +
//                "   <soapenv:Header/>\n" +
//                "   <soapenv:Body>\n" +
//                "      <gs:getCountryRequest>\n" +
//                "         <gs:name>Spain</gs:name>\n" +
//                "      </gs:getCountryRequest>\n" +
//                "   </soapenv:Body>\n" +
//                "</soapenv:Envelope>";
        String MESSAGE = "<userByIdRequest><id>1</id></userByIdRequest>";
        StreamSource source = new StreamSource(new StringReader(MESSAGE));
        StreamResult result = new StreamResult(System.out);
//        webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/AnotherWebService",
//                source, result);
        webServiceTemplate.sendSourceAndReceiveToResult(source, result);

    }
}
