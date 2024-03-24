package com.cda.locappartback.dto;


import com.cda.locappartback.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
    private long id;
    private String username;
    private String password;
    private String email;

}
