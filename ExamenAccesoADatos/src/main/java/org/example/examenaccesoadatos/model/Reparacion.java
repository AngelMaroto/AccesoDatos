package org.example.examenaccesoadatos.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reparaciones")
public class Reparacion implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int idReparacion;

    @Column(name = "fechareparacion")
    private LocalDate fechaReparacion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private String precio;

    @Column(name = "pagado")
    private String pagado;

    @ManyToOne
    @JoinColumn(name = "idCoche", referencedColumnName = "idCoche")
    private Coches coche;

    public Reparacion() {
    }

    public Reparacion(int idReparacion, LocalDate fechaReparacion, String descripcion, String precio, String pagado, Coches coche) {
        this.idReparacion = idReparacion;
        this.fechaReparacion = fechaReparacion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.pagado = pagado;
        this.coche = coche;
    }

    public int getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(int idReparacion) {
        this.idReparacion = idReparacion;
    }

    public LocalDate getFechaReparacion() {
        return fechaReparacion;
    }

    public void setFechaReparacion(LocalDate fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public Coches getCoche() {
        return coche;
    }

    public void setCoche(Coches coche) {
        this.coche = coche;
    }
}
