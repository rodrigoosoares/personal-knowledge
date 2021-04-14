package com.personal.knowledge.personalknowledge.controllers.validation.headers;

import com.personal.knowledge.personalknowledge.controllers.validation.entities.Header;
import com.personal.knowledge.personalknowledge.controllers.validation.headers.validators.HeaderValidator;
import com.personal.knowledge.personalknowledge.controllers.validation.headers.validators.HeaderValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RequestHeaderValidator {

    public void execute(final Header... headers) {
        for(Header header : headers) {
            final HeaderValidator validator = HeaderValidatorFactory.getValidator(header);

            if(Objects.nonNull(validator)) {
                validator.validate(header);
            }
        }
    }

}
