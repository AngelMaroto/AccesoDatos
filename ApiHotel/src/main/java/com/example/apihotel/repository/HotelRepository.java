package com.example.apihotel.repository;

import com.example.apihotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    //Busqueda de hotel por localidad o categoría
    List<Hotel> findByLocalidadOrCategoria(String localidad, int categoria);
}
