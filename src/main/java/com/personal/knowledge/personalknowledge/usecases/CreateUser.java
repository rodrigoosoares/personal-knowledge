package com.personal.knowledge.personalknowledge.usecases;

import com.personal.knowledge.personalknowledge.adapters.UserAdapter;
import com.personal.knowledge.personalknowledge.controllers.entities.UserRequest;
import com.personal.knowledge.personalknowledge.gateways.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

    private final UserGateway userGateway;

    @Autowired
    public CreateUser(final UserGateway userGateway) {
        this.userGateway = userGateway;
    }


    public void execute(final UserRequest userRequest) {
        userGateway.createUser(
            UserAdapter.fromUserRequest(userRequest)
        );
    }
}
