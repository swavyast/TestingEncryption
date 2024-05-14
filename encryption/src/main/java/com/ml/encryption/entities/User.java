package com.ml.encryption.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private String roles;
}
