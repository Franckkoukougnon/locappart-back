package com.cda.locappartback.dto;


import com.cda.locappartback.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String password;
    private String userName;
    private String email;

}
