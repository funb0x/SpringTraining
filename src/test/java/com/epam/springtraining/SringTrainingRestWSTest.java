package com.epam.springtraining;

import beans.dto.BookingTicketDTO;
import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

        User user = new User("u@u.com", "John Connor", new Date());
        Ticket ticket = new Ticket(new Event("Star Wars"), new Date(), Arrays.asList(1, 2, 3), user, 99.99);
        BookingTicketDTO dto = new BookingTicketDTO();
        dto.setUser(user);
        dto.setTicket(ticket);
        

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        

        //MultiValueMap<String,Object> parameters = new LinkedMultiValueMap<>();
//        parameters.add("user", user);
//        parameters.add("ticket", ticket);
        //parameters.add("dto", dto);
//        parameters.add("user", "[{\"id\":1,\"email\":\"m@m.com\",\"name\":\"John Doe\",\"birthday\":60592834800000,\"password\":\"0de7992ea5d6773ada3fcb7f862df8d152a61fc8217a8d6490e311b37c712fbe0157845c1074fbdf\",\"roles\":\"ROLE_REGISTERED_USER,ROLE_BOOKING_MANAGER\",\"account\":{\"id\":1,\"value\":200.0}}]");
//        parameters.add("ticket", "[{\"id\":-1,\"event\":{\"id\":0,\"name\":\"movie\",\"rate\":null,\"basePrice\":0.0,\"dateTime\":null,\"auditorium\":null},\"dateTime\":1428184800000,\"seats\":\"1,2,3\",\"price\":100.1,\"seatsList\":[1,2,3]}]");

        // Create the http entity for the request
        HttpEntity<BookingTicketDTO> entity = new HttpEntity<>(dto, headers);
        
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
