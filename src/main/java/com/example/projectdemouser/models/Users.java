package com.example.projectdemouser.models;

import com.example.projectdemouser.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import javax.persistence.*;



@Entity
@Table(name = "users")
public class Users extends Auditable implements Serializable{

    @Id
    private String id;

    private String username;


    @Email
    private String email;

    private String password;

    private String token;

    @JsonIgnore
    private String phones;

    private boolean isactive;


    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Users() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
