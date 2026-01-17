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

    //UN COCHE TIENE MUCHAS REPARACIONES
    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Reparacion> reparaciones;

    public Coches() {
    }

    public Coches(int idCoche, String matricula, String marca, String km, List<Reparacion> reparaciones) {
        this.idCoche = idCoche;
        this.matricula = matricula;
        this.marca = marca;
        this.km = km;
        this.reparaciones = reparaciones;
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

    public List<Reparacion> getCoches() {
        return reparaciones;
    }

    public void setCoches(List<Reparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }
}
