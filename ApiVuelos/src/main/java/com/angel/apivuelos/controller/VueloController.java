package com.angel.apivuelos.controller;

import com.angel.apivuelos.dto.VueloDTO;
import com.angel.apivuelos.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @GetMapping
    public List<VueloDTO> buscar(@RequestParam(required=false) String origen,
                                 @RequestParam(required=false) String destino,
                                 @RequestParam(required=false) Integer escalas) {
        return vueloService.buscar(origen, destino, escalas);
    }

    @GetMapping("/ofertas")
    public List<VueloDTO> ofertas(@RequestParam String destino, @RequestParam Float precio) {
        return vueloService.buscarOfertas(destino, precio);
    }

    @GetMapping("/conteo")
    public long conteo(@RequestParam String origen) {
        return vueloService.contarVuelos(origen);
    }
}