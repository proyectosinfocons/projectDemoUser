package com.example.projectdemouser.dto;

import com.example.projectdemouser.models.RequestUser;
import com.example.projectdemouser.models.Users;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Users user;

}