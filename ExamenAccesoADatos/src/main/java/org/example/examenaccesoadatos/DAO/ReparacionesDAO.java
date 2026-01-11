package org.example.examenaccesoadatos.DAO;

import org.example.examenaccesoadatos.model.Coches;
import org.example.examenaccesoadatos.model.Reparacion;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface ReparacionesDAO {
    List<Reparacion> obtenerReparacionesPorCoche(int idCoche) throws SQLException;

    void crearReparacion(Session session, Reparacion reparacion);

    void borrarCita(Session session, Reparacion reparacion);

    void actualizarReparacion(Session session, Reparacion reparacion);
}
