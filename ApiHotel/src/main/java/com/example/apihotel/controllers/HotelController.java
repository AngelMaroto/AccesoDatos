package com.example.apihotel.controllers;

import com.example.apihotel.entities.Habitacion;
import com.example.apihotel.entities.Hotel;
import com.example.apihotel.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    //Registrar un nuevo hotel
    //url http://localhost:9999/api/hoteles
    //body {
    //    "nombre": "Grand Hotel Costa",
    //    "descripcion": "Hotel de lujo con vistas al mar y buffet libre",
    //    "piscina": true,
    //    "categoria": 5,
    //    "localidad": "Valencia"
    //}
    @PostMapping("/hoteles")
    public ResponseEntity<Hotel> crearHotel(@RequestBody Hotel hotel) {
        return new ResponseEntity<>(hotelService.registrarHotel(hotel), HttpStatus.CREATED);
    }

    //Registrar una nueva habitacion a un hotel
    //url http://localhost:9999/api/hoteles/2/habitaciones
    //Body {
    //    "tamano": "Suite Vista Mar",
    //    "personas": 2,
    //    "precioNoche": 150.00,
    //    "desayuno": true,
    //    "ocupada": false
    //}
    @PostMapping("/hoteles/{idHotel}/habitaciones")
    public ResponseEntity<Habitacion> crearHabitacion(@PathVariable int idHotel, @RequestBody Habitacion habitacion) {
        Habitacion creada = hotelService.registrarHabitacion(idHotel, habitacion);
        if (creada != null) {
            return new ResponseEntity<>(creada, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Eliminar una habitacion
    @DeleteMapping("/habitaciones/{id}")
    public ResponseEntity<?> eliminarHabitacion(@PathVariable int id) {
        hotelService.eliminarHabitacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Modificar habitacion para indicar ocupada
    //http://localhost:9999/api/habitaciones/3/ocupar
    @PutMapping("/habitaciones/{id}/ocupar")
    public ResponseEntity<Habitacion> ocuparHabitacion(@PathVariable int id) {
        Habitacion modificada = hotelService.marcarOcupada(id);
        if (modificada != null) {
            return ResponseEntity.ok(modificada);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Busqueda de hotel por localidad o categoria
    //api/hoteles/buscar?localidad=Madrid&categoria=3
    @GetMapping("/hoteles/buscar")
    public ResponseEntity<List<Hotel>> buscarHoteles(
            @RequestParam(required = false) String localidad,
            @RequestParam(required = false) Integer categoria) {
        return ResponseEntity.ok(hotelService.buscarHoteles(localidad, categoria));
    }

    //Busqueda de habitaciones libres con filtros
    //api/habitaciones/buscar?idHotel=1&tamano=Doble&min=50&max=100
    @GetMapping("/habitaciones/buscar")
    public ResponseEntity<List<Habitacion>> buscarHabitaciones(
            @RequestParam int idHotel,
            @RequestParam String tamano,
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(hotelService.buscarHabitacionesFiltro(idHotel, tamano, min, max));
    }
}