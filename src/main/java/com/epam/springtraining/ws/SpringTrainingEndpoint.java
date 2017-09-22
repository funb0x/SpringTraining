package com.epam.springtraining.ws;

import beans.models.gen.ObjectFactory;
import beans.models.gen.User;
import beans.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class SpringTrainingEndpoint {

    @Autowired
    private UserService userService;

    @PayloadRoot(localPart = "userRegisterRequest", namespace = "http://schemas.xmlsoap.org/soap/envelope/")
    @ResponsePayload
    public User register(User user) {
        //return userService.register(user);
        return null;
    }

//    @PayloadRoot(localPart = "userRemoveRequest")
//    @ResponsePayload
//    public void remove(User user) {
//        userService.remove(user);
//    }

    @PayloadRoot(localPart = "userByIdRequest")
    @ResponsePayload
    public User getById(@RequestPayload JAXBElement<Long> id) {
        ObjectFactory objectFactory = new ObjectFactory();
        beans.models.User foundUser = userService.getById(1);

        if (foundUser != null) {
            User user = objectFactory.createUser();
            user.setId(foundUser.getId());
            user.setEmail(foundUser.getEmail());
            user.setName(foundUser.getName());

            return user;
        }
        return null;
    }

//    @PayloadRoot(localPart = "userByEmailRequest")
//    @ResponsePayload
//    public User getUserByEmail(String email) {
//        return userService.getUserByEmail(email);
//    }
//
//    @PayloadRoot(localPart = "userByNameRequest")
//    @ResponsePayload
//    public List<User> getUsersByName(String name) {
//        return userService.getUsersByName(name);
//    }
//
//    @PayloadRoot(localPart = "registeredUsersRequest")
//    @ResponsePayload
//    public List<User> getRegisteredUsers() {
//        return userService.getRegisteredUsers();
//    }
}
