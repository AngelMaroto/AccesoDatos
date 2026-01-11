package org.example.examenaccesoadatos.model;

import javafx.scene.control.SingleSelectionModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "coches")
public class Coches implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idcoche")
    private int idCoche;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "marca")
    private String marca;

    @Column(name = "km")
    private String km;

    @OneToMany(mappedBy = "coches", cascade = CascadeType.ALL)
    private List<Coches> coches;

    public Coches() {
    }

    public Coches(int idCoche, String matricula, String marca, String km, List<Coches> coches) {
        this.idCoche = idCoche;
        this.matricula = matricula;
        this.marca = marca;
        this.km = km;
        this.coches = coches;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String  km) {
        this.km = km;
    }

    public List<Coches> getCoches() {
        return coches;
    }

    public void setCoches(List<Coches> coches) {
        this.coches = coches;
    }
}
