package com.personal.knowledge.personalknowledge.controllers.validation.entities;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class ErrorData {

    private String fieldName;
    private String message;
}
