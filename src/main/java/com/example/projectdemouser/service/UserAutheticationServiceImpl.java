package com.example.projectdemouser.service;


import com.example.projectdemouser.models.RequestUser;
import com.example.projectdemouser.models.Users;
import com.example.projectdemouser.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAutheticationServiceImpl {




    @Autowired
    private UsersRepository usersRepository;



    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users saveUsers(RequestUser requestUser){
        Users user=new Users();
        String pass= passwordEncoder.encode(requestUser.getPassword());
        user.setId(UUID.randomUUID().toString());
        user.setEmail(requestUser.getEmail());
        user.setUsername(requestUser.getName());
        user.setPhones(requestUser.getPhones().toString());
        user.setPassword(pass);
        user=usersRepository.save(user);
        return user;
    }
}
