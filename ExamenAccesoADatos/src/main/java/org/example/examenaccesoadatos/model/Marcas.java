package org.example.examenaccesoadatos.model;

public class Marcas {
    private String nombreEspecilidad;

    public Marcas() {
    }

    public String getNombreEspecilidad() {
        return nombreEspecilidad;
    }

    public void setNombreEspecilidad(String nombreEspecilidad) {
        this.nombreEspecilidad = nombreEspecilidad;
    }

    @Override
    public String toString() {
        return nombreEspecilidad;
    }
}
