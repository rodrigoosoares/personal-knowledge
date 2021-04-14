package com.personal.knowledge.personalknowledge.fixtures.processors

import br.com.six2six.fixturefactory.processor.Processor
import com.personal.knowledge.personalknowledge.controllers.validation.entities.ErrorResponse

class ErrorResponseProcessor implements Processor{

    private String path

    ErrorResponseProcessor(String path) {
        this.path = path
    }

    @Override
    void execute(Object o) {
        if(o instanceof ErrorResponse) {
            (o as ErrorResponse).setPath(path)
        }
    }
}
