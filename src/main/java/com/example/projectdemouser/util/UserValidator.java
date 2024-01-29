package com.example.projectdemouser.util;

import com.example.projectdemouser.exception.IncorrectOrderRequestException;
import com.example.projectdemouser.models.Users;

public class UserValidator {
    public static void validate(Users user){
        if(user.getUsername()==null || user.getUsername().trim().isEmpty()){
            throw new IncorrectOrderRequestException("El nombre de usuario es requerido");
        }

        if(user.getEmail()==null || user.getEmail().trim().isEmpty()){
            throw new IncorrectOrderRequestException("El correo es requerido");
        }
        if(user.getPassword()==null || user.getPassword().trim().isEmpty()){
            throw new IncorrectOrderRequestException("El password es requerido");
        }
        if(user.getUsername().length()>30){
            throw new IncorrectOrderRequestException("El nombre de usuario es muy largo (max 30)");
        }

        if(user.getPassword()==null || user.getPassword().isEmpty()){
            throw new IncorrectOrderRequestException("El password es requerido");
        }
        if(user.getPassword().length()>30){
            throw new IncorrectOrderRequestException("El password de usuario es muy largo (max 30)");
        }
    }
}