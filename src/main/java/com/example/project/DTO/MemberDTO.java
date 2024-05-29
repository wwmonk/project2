package com.example.project.DTO;

import com.example.project.Rolltype.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Role role;
    private String confirm_password;
}

