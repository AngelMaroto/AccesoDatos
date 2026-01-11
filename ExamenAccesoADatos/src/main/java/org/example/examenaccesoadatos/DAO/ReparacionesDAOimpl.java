package org.example.examenaccesoadatos.DAO;

import org.example.examenaccesoadatos.Connection.DBConnection;
import org.example.examenaccesoadatos.model.Coches;
import org.example.examenaccesoadatos.model.Reparacion;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReparacionesDAOimpl implements ReparacionesDAO{

    @Override
    public List<Reparacion> obtenerReparacionesPorCoche(int idCoche) throws SQLException {
        List<Reparacion> reparaciones = new ArrayList<>();

        String sql = "SELECT c.idcoche, r.idreparacion, r.fechareparacion, r.descripcion, r.precio, r.pagado " +
                "FROM Reparaciones r JOIN Coches c ON r.idcoche = c.idcoche " +
                "WHERE c.idcoche = ?";

        try (Connection conn = DBConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCoche);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reparacion reparacion1 = new Reparacion();
                reparacion1.setIdReparacion(rs.getInt("idreparacion"));
                java.sql.Date fechaSql = rs.getDate("fechareparacion");
                if (fechaSql != null){
                    reparacion1.setFechaReparacion(fechaSql.toLocalDate());
                }
                reparacion1.setDescripcion(rs.getString("descripcion"));
                reparacion1.setPrecio(rs.getString("precio"));
                String pagadoStr = rs.getString("pagado");
                reparacion1.setPagado(pagadoStr);

                Coches coche = new Coches();
                coche.setIdCoche(rs.getInt("idcoche"));
                reparacion1.setCoche(coche);

                reparaciones.add(reparacion1); // Añadimos la cita a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reparaciones;
    }

    @Override
    public void crearReparacion(Session session, Reparacion reparacion) {
        session.save(reparacion);
    }

    @Override
    public void borrarCita(Session session, Reparacion reparacion) {
        session.beginTransaction();
        session.delete(reparacion);
        session.getTransaction().commit();
    }

    @Override
    public void actualizarReparacion(Session session, Reparacion reparacion) {
        session.update(reparacion);
    }
}
