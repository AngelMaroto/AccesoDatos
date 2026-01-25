package com.angel.apivuelos.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    private String username;
    private String password;
}
