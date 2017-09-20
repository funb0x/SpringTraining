package com.epam.springtraining.ws;

import beans.models.User;
import beans.services.UserService;
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

    @PayloadRoot(localPart = "getUserRequest")
    //@ResponsePayload
    public void getUserByEmail(@RequestPayload String email){
        //return userService.getUserByEmail(email);
    }

}
