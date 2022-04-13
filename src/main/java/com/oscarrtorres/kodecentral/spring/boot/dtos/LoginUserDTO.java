package com.oscarrtorres.kodecentral.spring.boot.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class LoginUserDTO {

    @NotNull(message = "username is required")
    @Length(min = 1, message = "username can not be blank")
    private String username;

    @NotNull(message = "password is required")
    private String password;
}
