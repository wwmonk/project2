package com.example.project.Entity;

import com.example.project.Rolltype.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(length = 15)
    private String name;
    @Column(length = 20)
    private String username;
    private String password;
    @Column(length = 30)
    private String email;
    @Column(length = 30)
    private String confirm_password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
