package com.personal.knowledge.personalknowledge.configuration.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.personal.knowledge.personalknowledge.configuration.objectmapper.deserializers.InstantDeserializer;

import java.time.Instant;

public class ObjectMapperConfiguration {

    private final ObjectMapper objectMapper;

    public ObjectMapperConfiguration() {
        this.objectMapper = new ObjectMapper();

        final SimpleModule module = new SimpleModule();

        module.addDeserializer(Instant.class, new InstantDeserializer());

        objectMapper.registerModule(module);
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
