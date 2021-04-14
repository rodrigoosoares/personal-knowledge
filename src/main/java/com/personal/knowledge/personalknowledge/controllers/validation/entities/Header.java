package com.personal.knowledge.personalknowledge.controllers.validation.entities;

import com.personal.knowledge.personalknowledge.controllers.validation.headers.enums.HeaderType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Header {

    private HeaderType type;
    private Object value;

}
