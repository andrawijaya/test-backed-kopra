package com.kopra.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter @Getter
@ToString
public class ProfileCreateRequest {
    @NotBlank(message = "firstname is required")
    private String firstName;

    @NotBlank(message = "lastname is required")
    private  String lastName;

    @NotNull(message = "address is required")
    private Float address;

    @NotNull(message = "birthday is required")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "country is required")
    private String country;
}
