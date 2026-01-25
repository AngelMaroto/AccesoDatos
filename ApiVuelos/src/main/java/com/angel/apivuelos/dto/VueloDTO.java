package com.angel.apivuelos.dto;

import lombok.Data;

@Data
public class VueloDTO {
    private String idVuelo;
    private String horaSalida;
    private String origen;
    private String destino;
    private Float precio;
    private Integer numeroEscalas;
    // En lugar del objeto Compañía entero, solo devolvemos el nombre
    private String nombreCompania;
}
