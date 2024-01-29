package com.example.projectdemouser.controller;


import com.example.projectdemouser.dto.LoginRequest;
import com.example.projectdemouser.dto.LoginResponse;
import com.example.projectdemouser.exception.UserErrorResponse;
import com.example.projectdemouser.exception.UserNotFoundException;
import com.example.projectdemouser.models.RequestUser;
import com.example.projectdemouser.models.Users;
import com.example.projectdemouser.repository.UsersRepository;
import com.example.projectdemouser.service.UserAutheticationServiceImpl;
import com.example.projectdemouser.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {


    String EXP_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";


    @Autowired
    private UserAutheticationServiceImpl userAutheticationService;


    @Autowired
    private UserService userService;


    @Autowired
    private UsersRepository usersRepository;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RequestUser request) {
        Users enc = usersRepository.findByEmail(request.getEmail()).orElse(null);
        if (enc != null) {
            if (enc.getEmail().equals(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserErrorResponse("El correo ya registrado"));
            }
            if (!enc.getEmail().matches(EXP_EMAIL)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserErrorResponse("mensaje de error"));
            }
        }
        if (enc == null) {
            if (!request.getEmail().matches(EXP_EMAIL)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserErrorResponse("mensaje de error"));
            }
                Users pru=new Users();
                pru.setId(UUID.randomUUID().toString());
                pru.setPassword(request.getPassword());
                pru.setUsername(request.getName());
                pru.setPhones(request.getPhones().toString());
                pru.setEmail(request.getEmail());
                pru.setIsactive(true);
                Users prueba = userService.createUser(pru);
                return ResponseEntity.ok(prueba);
        }
        return null;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response=userService.login(request);
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Users>> Listall(){
        List<Users> response=usersRepository.findAll();
        return new ResponseEntity<List<Users>>(response,HttpStatus.OK);
    }

}
