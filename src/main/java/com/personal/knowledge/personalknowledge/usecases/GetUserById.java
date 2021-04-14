package com.personal.knowledge.personalknowledge.usecases;

import com.personal.knowledge.personalknowledge.entities.User;
import org.springframework.stereotype.Component;

@Component
public class GetUserById {

    public User execute(final int id) {
        return User.builder().build();
    }
}
