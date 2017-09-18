package com.epam.springtraining.ws;

import beans.models.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by Анна on 18.09.2017.
 */

@Endpoint
public class SpringTrainingWs {

    @Autowired
    private UserService userService;

    @PayloadRoot(localPart = "getUserRequest")
    @ResponsePayload
    public User getUserByEmail(String email){
        return userService.getUserByEmail(email);
    }

}
