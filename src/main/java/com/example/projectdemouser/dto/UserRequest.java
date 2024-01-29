package com.example.projectdemouser.dto;

import lombok.*;

@Getter
@Setter

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String id;
    private String username;
}