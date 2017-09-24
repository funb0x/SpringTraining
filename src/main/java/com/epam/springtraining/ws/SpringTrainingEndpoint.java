package com.epam.springtraining.ws;

import beans.models.gen.ObjectFactory;
import beans.models.gen.User;
import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

@Endpoint
public class SpringTrainingEndpoint {

    @Autowired
    private UserService userService;


    @PayloadRoot(localPart = "userByIdRequest")
    @ResponsePayload
    public User getById(@XPathParam("/userByIdRequest/id") long id) {
        ObjectFactory objectFactory = new ObjectFactory();
        beans.models.User foundUser = userService.getById(id);

        if (foundUser != null) {
            User user = objectFactory.createUser();
            user.setId(foundUser.getId());
            user.setEmail(foundUser.getEmail());
            user.setName(foundUser.getName());

            return user;
        }
        return null;
    }
}
