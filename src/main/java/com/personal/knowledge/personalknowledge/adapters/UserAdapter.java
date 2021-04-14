package com.personal.knowledge.personalknowledge.adapters;

import com.personal.knowledge.personalknowledge.controllers.entities.UserRequest;
import com.personal.knowledge.personalknowledge.entities.User;

public class UserAdapter {

    private UserAdapter() {}

    public static User fromUserRequest(final UserRequest userRequest) {
        return User.builder()
            .username(userRequest.getName())
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .build();
    }

}
