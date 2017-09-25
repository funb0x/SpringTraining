package com.epam.springtraining;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Анна on 25.09.2017.
 */
public class SringTrainingRestWSTest {

    private static RestTemplate restTemplate;

    @BeforeClass
    public static void setup() {
       restTemplate = new RestTemplate();
    }

    @Test
    public void testUserByIdRequest() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
//                .queryParam("msisdn", msisdn)
//                .queryParam("email", email)
//                .queryParam("clientVersion", clientVersion)
//                .queryParam("clientType", clientType)
//                .queryParam("issuerName", issuerName)
//                .queryParam("applicationName", applicationName);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ticket/all", HttpMethod.GET, entity, String.class);
        System.out.println(response);
    }
}
