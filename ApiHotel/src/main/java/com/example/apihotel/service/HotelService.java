package com.example.apihotel.service;

import com.example.apihotel.entities.Habitacion;
import com.example.apihotel.entities.Hotel;
import com.example.apihotel.repository.HabitacionRepository;
import com.example.apihotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HabitacionRepository habitacionRepository;

    public HotelService(HotelRepository hotelRepository, HabitacionRepository habitacionRepository) {
        this.hotelRepository = hotelRepository;
        this.habitacionRepository = habitacionRepository;
    }

    //Registrar un nuevo hotel
    public Hotel registrarHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    //Registrar una nueva habitacion a un hotel
    public Habitacion registrarHabitacion(int idHotel, Habitacion habitacion) {
        Hotel hotel = hotelRepository.findById(idHotel).orElse(null);
        if (hotel != null) {
            habitacion.setHotel(hotel);
            return habitacionRepository.save(habitacion);
        }
        return null;
    }

    //Eliminar una habitacion determinada
    public void eliminarHabitacion(int idHabitacion) {
        habitacionRepository.deleteById(idHabitacion);
    }

    //Modificar una habitacion para indicar que está ocupada
    public Habitacion marcarOcupada(int idHabitacion) {
        Optional<Habitacion> habitacionOpt = habitacionRepository.findById(idHabitacion);
        if (habitacionOpt.isPresent()) {
            Habitacion habitacion = habitacionOpt.get();
            habitacion.setOcupada(true);
            return habitacionRepository.save(habitacion);
        }
        return null;
    }

    //Buscar hoteles
    public List<Hotel> buscarHoteles(String localidad, Integer categoria) {
        return hotelRepository.findByLocalidadOrCategoria(localidad, categoria != null ? categoria : -1);
    }

    //Buscar habitaciones específicas libres
    public List<Habitacion> buscarHabitacionesFiltro(int idHotel, String tamano, BigDecimal min, BigDecimal max) {
        return habitacionRepository.buscarHabitacionesLibres(idHotel, tamano, min, max);
    }
}
