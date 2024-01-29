package com.example.projectdemouser.run;

import com.example.projectdemouser.exception.UserErrorResponse;
import com.example.projectdemouser.models.Phones;
import com.example.projectdemouser.models.RequestUser;
import com.example.projectdemouser.models.Users;
import com.example.projectdemouser.repository.UsersRepository;
import com.example.projectdemouser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class DataLoader implements CommandLineRunner {


    String EXP_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;


    @Override
    public void run(String... strings) throws Exception {
        Phones phone= new Phones();
        phone.setCitycode("0390388");
        phone.setNumber("732762636");
        phone.setCountrycode("2424553");
        List<Phones> phones= new ArrayList<>();
        phones.add(phone);


        RequestUser request = new RequestUser();
        request.setEmail("prueba@gmail.com");
        request.setName("Prueba");
        request.setPassword("Prueba123");
        request.setPhones(phones);

        Users enc = usersRepository.findByEmail(request.getEmail()).orElse(null);
        if (enc != null) {
            if (enc.getEmail().equals(request.getEmail())) {
                 ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserErrorResponse("El correo ya registrado"));
            }
            if (!enc.getEmail().matches(EXP_EMAIL)) {
                 ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserErrorResponse("mensaje de error"));
            }
        }
        if (enc == null) {
            if (!request.getEmail().matches(EXP_EMAIL)) {
                 ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserErrorResponse("mensaje de error"));
            }
            Users pru=new Users();
            pru.setId(UUID.randomUUID().toString());
            pru.setPassword(request.getPassword());
            pru.setUsername(request.getName());
            pru.setPhones(request.getPhones().toString());
            pru.setEmail(request.getEmail());
            pru.setIsactive(false);
            Users prueba = userService.createUser(pru);
             ResponseEntity.ok(prueba);
        }

    }
}
