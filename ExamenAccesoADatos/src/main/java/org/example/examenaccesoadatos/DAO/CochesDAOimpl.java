package org.example.examenaccesoadatos.DAO;

import org.example.examenaccesoadatos.Connection.DBConnection;
import org.example.examenaccesoadatos.model.Coches;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CochesDAOimpl implements CochesDAO{
    @Override
    public Coches buscarPorMatricula(String matricula) throws SQLException {
        Coches coche = null;

        String sql = "SELECT * FROM Coches WHERE Matricula = ?";

        try (Connection conn = DBConnection.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Sustituimos el parámetro ? por el DNI real
            ps.setString(1, matricula);

            // Ejecutamos la consulta
            ResultSet rs = ps.executeQuery();

            // Si existe un resultado, creamos un objeto Paciente con los datos
            if (rs.next()) {
                coche = new Coches();
                coche.setMarca(rs.getString("marca"));
                coche.setKm(rs.getString("km"));
                coche.setIdCoche(rs.getInt("idcoche"));
                coche.setMatricula(rs.getString("matricula"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si no se encontró, se devolverá null
        return coche;
    }

    @Override
    public void modificarCoche(Session session, Coches coche) {
        session.update(coche);
    }
}
