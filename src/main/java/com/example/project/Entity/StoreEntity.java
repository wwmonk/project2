package com.example.project.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="store")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 25)
    private String name;
    @Column(length = 10)
    private String upname;
    @Column(length = 10)
    private String st;
    @Column(length = 30)
    private String address;
}
