package com.works.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(length = 100)
    private String name;
    @Column(length = 200)
    private String email;
    private String password;
    private Boolean status = true;

    @ManyToMany
    private List<Role> roles;
}
