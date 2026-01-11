package org.example.examenaccesoadatos.DAO;

import org.example.examenaccesoadatos.model.Coches;
import org.hibernate.Session;

import java.sql.SQLException;

public interface CochesDAO {

    public Coches buscarPorMatricula(String matricula) throws SQLException;

    void modificarCoche(Session session, Coches coche);
}
