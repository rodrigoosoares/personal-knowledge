package com.personal.knowledge.personalknowledge.gateways.impl;

import com.personal.knowledge.personalknowledge.entities.User;
import com.personal.knowledge.personalknowledge.gateways.UserGateway;
import com.personal.knowledge.personalknowledge.gateways.repositories.mongo.UserMongoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGatewayMongoImpl implements UserGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserGatewayMongoImpl.class);

    private final UserMongoRepository userMongoRepository;

    @Override
    public void createUser(final User user) {
        try {
            userMongoRepository.createUser(user);
        } catch (final Exception exception) {
            // TODO message manager
            final String message = "Error creating new user";
            LOGGER.error(message);
            throw exception;
        }
    }
}
