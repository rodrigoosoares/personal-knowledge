package com.personal.knowledge.personalknowledge.fixtures.templates

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorData
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse

import static ErrorDataFixtures.MIN_VALUE_ERROR_DATA
import static com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse.Fields.*

class ErrorResponseFixtures implements TemplateLoader {

    public static final String DATA_MIN_VALUE_METHOD_GET_VALIDATION_ERROR = "data min value method get validation error"

    @Override
    void load() {
        Fixture.of(ErrorResponse).addTemplate(DATA_MIN_VALUE_METHOD_GET_VALIDATION_ERROR, new Rule(){
            {
                add(method, "GET")
                add(errors, has(1).of(ErrorData, MIN_VALUE_ERROR_DATA))
                add(status, "400 Bad Request")
            }
        })
    }
}