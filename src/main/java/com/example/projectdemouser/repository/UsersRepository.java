package com.example.projectdemouser.repository;

import com.example.projectdemouser.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users,String> {
        Optional<Users> findByEmail(String email);

        Optional<Users> findByUsername(String username);

}
