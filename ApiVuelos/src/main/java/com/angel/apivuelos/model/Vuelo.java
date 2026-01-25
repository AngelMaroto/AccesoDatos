package com.angel.apivuelos.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Vuelos")
public class Vuelo {
    @Id
    @Column(name = "idvuelo")
    private String idVuelo;

    @Column(name = "horasalida")
    private String horaSalida;

    private String origen;
    private String destino;
    private Float precio;

    @Column(name = "numeroescalas")
    private Integer numeroEscalas;

    @ManyToOne
    @JoinColumn(name = "idcompañia")
    private Compania compania;
}
