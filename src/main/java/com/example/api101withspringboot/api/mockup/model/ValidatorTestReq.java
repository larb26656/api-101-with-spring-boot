package com.example.api101withspringboot.api.mockup.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ValidatorTestReq {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "nickName cannot be blank")
    private String nickName;

    @Size(min = 10, max = 200, message
            = "About Me must be between 10 and 200 characters")
    private String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @Email(message = "Email should be valid")
    private String email;

}