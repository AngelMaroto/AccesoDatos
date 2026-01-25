package com.angel.apivuelos.repository;

import com.angel.apivuelos.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, String> {

    // 1. Filtrar por 3 campos (si son nulos, los ignora)
    @Query("SELECT v FROM Vuelo v WHERE " +
            "(:origen IS NULL OR v.origen = :origen) AND " +
            "(:destino IS NULL OR v.destino = :destino) AND " +
            "(:escalas IS NULL OR v.numeroEscalas = :escalas)")
    List<Vuelo> buscarVuelos(@Param("origen") String origen,
                             @Param("destino") String destino,
                             @Param("escalas") Integer escalas);

    // 2. Destino y precio máximo
    List<Vuelo> findByDestinoAndPrecioLessThanEqual(String destino, Float precio);

    // 3. Conteo vuelos origen sin escalas
    long countByOrigenAndNumeroEscalas(String origen, Integer numeroEscalas);
}
