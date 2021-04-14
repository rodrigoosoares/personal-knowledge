package com.personal.knowledge.personalknowledge.controllers.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Field name is required")
    @ApiModelProperty(position = 0, notes = "User name", example = "John", required = true)
    private String name;

    @NotBlank(message = "Field name is required")
    @ApiModelProperty(position = 1, notes = "User email", example = "john@email.com", required = true)
    private String email;

    @ApiModelProperty(position = 2, notes = "User password", required = true)
    private String password;

    @Min(0)
    @Max(100)
    private int processNumber;

}
