package com.example.apihotel.repository;

import com.example.apihotel.entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

    // Busqueda de habitaciones de un hotel por tamaño y rango de precio
    //(ocupada = false)
    @Query("SELECT h FROM Habitacion h WHERE h.hotel.idHotel = :idHotel AND h.tamano = :tamano AND h.precioNoche BETWEEN :minPrecio AND :maxPrecio AND h.ocupada = false")
    List<Habitacion> buscarHabitacionesLibres(int idHotel, String tamano, BigDecimal minPrecio, BigDecimal maxPrecio);
}
