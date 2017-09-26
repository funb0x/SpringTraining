package com.epam.springtraining;

import beans.models.Ticket;
import beans.models.User;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class SringTrainingRestWSTest {

    private static RestTemplate restTemplate;

    @BeforeClass
    public static void setup() {
       restTemplate = new RestTemplate();
       
    }
 
    @Test
    public void testGetTicketPrice() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ticket/price/movie/imax/2015-04-05/1,2,3", HttpMethod.GET, entity, String.class);
        System.out.println(response);
    }
    
//    @RequestMapping(value = "/book", method = POST)
//    @ResponseBody
//    public Ticket bookTicket(User user, @DateTimeFormat(pattern="yyyy-MM-dd") Date date, Ticket ticket) {
//        Random random = new Random();
//        ticket.setPrice(100.0 + 110.* random.nextDouble());
//        return bookingService.bookTicket(user, ticket);
//    }
      
    
    @Test
    public void testBookTicket() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        

        MultiValueMap<String,Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("user", new User());
        parameters.add("ticket", new Ticket());
        parameters.add("ticket", new Date());

        // Create the http entity for the request
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);
        
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ticket/book", HttpMethod.POST, entity, String.class);
        System.out.println(response);
    }
        
    
    @Test
    public void testGetTicketsForEvent() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ticket/event/movie/imax/2015-04-05", HttpMethod.GET, entity, String.class);
        System.out.println(response);
    }
    
    @Test
    public void testGetAllTickets() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/ticket/all", HttpMethod.GET, entity, String.class);
        System.out.println(response);
    }
}
