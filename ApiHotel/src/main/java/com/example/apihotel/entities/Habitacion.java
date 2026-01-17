package com.example.apihotel.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "HABITACION")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhabitacion")
    private int idHabitacion;

    @Column(name = "tamano")
    private String tamano;

    @Column(name = "personas")
    private int personas;

    @Column(name = "precio_noche")
    private BigDecimal precioNoche;

    @Column(name = "desayuno")
    private boolean desayuno;

    @Column(name = "ocupada")
    private boolean ocupada;

    //Muchas Habitaciones pertenecen a un Hotel
    @ManyToOne
    @JoinColumn(name = "idhotel", referencedColumnName = "idhotel")
    @JsonBackReference
    private Hotel hotel;

    public Habitacion() {}

    public int getIdHabitacion() { return idHabitacion; }
    public void setIdHabitacion(int idHabitacion) { this.idHabitacion = idHabitacion; }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    public int getPersonas() { return personas; }
    public void setPersonas(int personas) { this.personas = personas; }

    public BigDecimal getPrecioNoche() { return precioNoche; }
    public void setPrecioNoche(BigDecimal precioNoche) { this.precioNoche = precioNoche; }

    public boolean isDesayuno() { return desayuno; }
    public void setDesayuno(boolean desayuno) { this.desayuno = desayuno; }

    public boolean isOcupada() { return ocupada; }
    public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }
}
