package com.epam.springtraining.mvc;

import beans.models.Event;
import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import beans.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class WebController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;


    private Map<String, User> stringUserMap;
    private List<Ticket> tickets;

    @PostConstruct
    private void init() {
        User user1 = new User("m@m.com", "John Doe", new Date(1990, 01, 10));
        user1.setRoles("ROLE_REGISTERED_USER,ROLE_BOOKING_MANAGER");
        user1.setPassword("111");
        User user2 = new User("u@u.com", "Bob Smith", new Date(1990, 01, 10));
        user2.setRoles("ROLE_REGISTERED_USER");
        user2.setPassword("222");

        userService.register(user1);
        userService.register(user2);
        
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
        Random random = new Random();
        ticket.setPrice(100.0 + 110.* random.nextDouble());
        bookingService.bookTicket(user, ticket);
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
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            PDFont font = PDType1Font.TIMES_ROMAN;
            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(0, 0);
                bookingService.getAllTickets().stream().forEach(s -> {
                    try {
                        contents.showText(s.toString());
                        contents.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                contents.endText();
            }
            doc.save(bos);
        }
        byte[] contents = bos.toByteArray();

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
    public Collection<User> getAllUsers() {
        Collection<User> users = userService.getRegisteredUsers();
        return users;
    }

    @RequestMapping(value = "/register_user", method = POST)
    public String registerUser(User user) {
        user.setRoles("ROLE_REGISTERED_USER");
        userService.register(user);
        return "index";
    }

    @RequestMapping(value = "/register_manager", method = POST)
    public String registerManager(User user) {
        user.setRoles("ROLE_REGISTERED_USER,ROLE_BOOKING_MANAGER");
        userService.register(user);
        return "index";
    }

    @RequestMapping(value = "/login", method = GET)
    public String loginView() {
        return "login";
    }


}
