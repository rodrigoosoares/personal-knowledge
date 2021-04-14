package com.personal.knowledge.personalknowledge.gateways.repositories.mongo;

import com.personal.knowledge.personalknowledge.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UserMongoRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public void createUser(final User user) {
        mongoOperations.insert(user);
    }

}
