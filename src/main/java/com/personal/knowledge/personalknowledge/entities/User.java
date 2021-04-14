package com.personal.knowledge.personalknowledge.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    private String username;
    private String email;
    private String password;
    private int processNumber;

}
