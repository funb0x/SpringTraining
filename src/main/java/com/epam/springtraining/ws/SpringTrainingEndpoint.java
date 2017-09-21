package com.epam.springtraining.ws;

import beans.models.User;
import beans.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SpringTrainingEndpoint {

    @Autowired
    private UserService userService;

    public SpringTrainingEndpoint() {
        System.out.println("SpringTrainingWs");
    }

    @PayloadRoot(localPart = "userRegisterRequest")
    @ResponsePayload
    public User register(User user) {
        return userService.register(user);
    }

    @PayloadRoot(localPart = "userRemoveRequest")
    @ResponsePayload
    public void remove(User user) {
        userService.remove(user);
    }

    @PayloadRoot(localPart = "userByIdRequest")
    @ResponsePayload
    public User getById(long id) {
        return userService.getById(id);
    }

    @PayloadRoot(localPart = "userByEmailRequest")
    @ResponsePayload
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @PayloadRoot(localPart = "userByNameRequest")
    @ResponsePayload
    public List<User> getUsersByName(String name) {
        return userService.getUsersByName(name);
    }

    @PayloadRoot(localPart = "registeredUsersRequest")
    @ResponsePayload
    public List<User> getRegisteredUsers() {
        return userService.getRegisteredUsers();
    }
}
