package com.personal.knowledge.personalknowledge.controllers.validation.headers.validators;

import com.personal.knowledge.personalknowledge.controllers.validation.entities.Header;
import com.personal.knowledge.personalknowledge.controllers.validation.exceptions.InvalidHeaderException;
import org.apache.commons.lang.StringUtils;

public class RequestTraceIdHeaderValidator implements HeaderValidator{
    @Override
    public void validate(Header header) {
        final String requestTraceId = (String) header.getValue();

        if(StringUtils.isEmpty(requestTraceId)) {
            throw new InvalidHeaderException("Request Trace Id header must not be null or empty.", "request-trace-id");
        }

    }
}
