package com.personal.knowledge.personalknowledge.fixtures.templates

import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.Rule
import br.com.six2six.fixturefactory.loader.TemplateLoader
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorData

import static com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorData.Fields.fieldName
import static com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorData.Fields.message

class ErrorDataFixtures implements TemplateLoader {

    public static final String MIN_VALUE_ERROR_DATA = "minimum value error data"

    @Override
    void load() {
        Fixture.of(ErrorData).addTemplate(MIN_VALUE_ERROR_DATA, new Rule() {
            {
                add(fieldName, "age")
                add(message, "must be greater than or equal to 18")
            }
        })
    }
}
