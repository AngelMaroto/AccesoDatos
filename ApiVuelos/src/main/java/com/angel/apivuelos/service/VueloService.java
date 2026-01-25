package com.angel.apivuelos.service;

import com.angel.apivuelos.dto.VueloDTO;
import com.angel.apivuelos.model.Vuelo;
import com.angel.apivuelos.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    public List<VueloDTO> buscar(String origen, String destino, Integer escalas) {
        List<Vuelo> lista = vueloRepository.buscarVuelos(origen, destino, escalas);
        return lista.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public List<VueloDTO> buscarOfertas(String destino, Float precio) {
        List<Vuelo> lista = vueloRepository.findByDestinoAndPrecioLessThanEqual(destino, precio);
        return lista.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public long contarVuelos(String origen) {
        return vueloRepository.countByOrigenAndNumeroEscalas(origen, 0);
    }

    // Método privado para convertir Entidad -> DTO
    private VueloDTO convertirADTO(Vuelo v) {
        VueloDTO dto = new VueloDTO();
        dto.setIdVuelo(v.getIdVuelo());
        dto.setHoraSalida(v.getHoraSalida());
        dto.setOrigen(v.getOrigen());
        dto.setDestino(v.getDestino());
        dto.setPrecio(v.getPrecio());
        dto.setNumeroEscalas(v.getNumeroEscalas());
        if (v.getCompania() != null) {
            dto.setNombreCompania(v.getCompania().getNombreCompania());
        }
        return dto;
    }
}