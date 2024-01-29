package com.example.projectdemouser.models;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;


@Data
public class RequestUser {

    private String name;



    @Email
    private String email;

    private String password;

    private List<Phones> phones;

}
