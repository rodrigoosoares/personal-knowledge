package com.personal.knowledge.personalknowledge.controllers.validation.entities;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class ErrorResponse {

    private String timestamp;
    private String status;
    private String path;
    private String method;
    private List<ErrorData> errors;

}


