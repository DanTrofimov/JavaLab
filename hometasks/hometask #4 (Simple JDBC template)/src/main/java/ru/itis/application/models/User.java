package ru.itis.application.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
