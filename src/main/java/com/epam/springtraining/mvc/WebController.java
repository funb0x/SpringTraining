package com.epam.springtraining.mvc;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class WebController {

    private Map<String, User> stringUserMap;
    private List<Ticket> tickets;

    @PostConstruct
    private void init() {
        User user1 = new User("JohnDoe@aol.com", "John Doe", new Date(1990, 01, 10));
        User user2 = new User("BobSmith@aol.com", "Bob Smith", new Date(1990, 01, 10));

        stringUserMap = new HashMap<>();
        stringUserMap.put("JohnDoe@aol.com", user1);
        stringUserMap.put("BobSmith@aol.com", user2);

        tickets = new ArrayList<>();

        tickets.add(new Ticket(new Event("football"), new Date(), Arrays.asList(1, 2, 3), user1, 100.1));
        tickets.add(new Ticket(new Event("cinema"), new Date(), Arrays.asList(1, 2, 3), user2, 99.9));
    }

    @RequestMapping(value = "/", method = GET)
    public String home(@ModelAttribute("model") ModelMap model) {
        model.put("numbers", new int[]{1,2, 3,4,5,6});
        return "index";
    }

    @RequestMapping(value = "/get_price", method = POST)
    public String getTicketPrice(String eventName, String auditoriumName, @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTime, int[] seats, User user) {
        System.out.println(eventName);
        System.out.println(auditoriumName);
        System.out.println(dateTime);
        System.out.println(seats);
        System.out.println(user);
        return "index";
    }

    @RequestMapping(value = "/book_ticket", method = POST)
    public String bookTicket(User user, @DateTimeFormat(pattern="yyyy-MM-dd") Date date, Ticket ticket) {
        System.out.println(user);
        System.out.println(date);
        System.out.println(ticket);
        return "index";
    }

    @RequestMapping(value = "/user/{email}", method = GET)
    public ModelAndView getUserByEmail(@PathVariable String email, @ModelAttribute("model") ModelMap model) {
        User user = stringUserMap.get(email);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("user");
        return mav;
    }

    @RequestMapping(value = "/tickets", method = GET)
    public ResponseEntity<byte[]> getBookedTickets() throws IOException {
        ByteOutputStream bos = new ByteOutputStream();
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.TIMES_ROMAN;
            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                contents.showText(String.join("", tickets.stream().map(Object::toString).collect(Collectors.toList())));
                contents.endText();
            }
            doc.save(bos);
        }
        byte[] contents = bos.getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

    @RequestMapping(value = "/user_upload", method = POST)
    public String uploadUsers(@RequestPart("users") MultipartFile file) throws IOException {
        String jsonString = new String(file.getBytes());
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users =  objectMapper.readValue(jsonString, new TypeReference<List<User>>(){});
        Map<String, User> newUsers = users.stream().collect(Collectors.toMap(User::getEmail, Function.identity()));
        stringUserMap.putAll(newUsers);
        return "index";
    }

    @RequestMapping(value = "/user", method = GET)
    @ResponseBody
    public Collection<User> getAllUsers() throws IOException {
        return stringUserMap.values();
    }

}
