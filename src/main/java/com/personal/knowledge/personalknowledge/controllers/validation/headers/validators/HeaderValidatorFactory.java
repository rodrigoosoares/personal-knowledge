package com.personal.knowledge.personalknowledge.controllers.validation.headers.validators;

import com.personal.knowledge.personalknowledge.controllers.validation.entities.Header;
import com.personal.knowledge.personalknowledge.controllers.validation.headers.enums.HeaderType;

public class HeaderValidatorFactory {

    private HeaderValidatorFactory() { }

    public static HeaderValidator getValidator(final Header header) {
        if (header.getType() == HeaderType.REQUEST_TRACE_ID) {
            return new RequestTraceIdHeaderValidator();
        }
        return null;
    }

}
