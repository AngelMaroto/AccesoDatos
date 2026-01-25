package com.angel.apivuelos.model;

import javax.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "Compañias")
public class Compania {
    @Id
    @Column(name = "idcompañia")
    private Integer idCompania;

    @Column(name = "nombrecompañia")
    private String nombreCompania;

    // IMPORTANTE: CascadeType.ALL para borrar sin errores, JsonIgnore para evitar bucles infinitos
    @OneToMany(mappedBy = "compania", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vuelo> vuelos;
}
