package com.juan.HibernateRelacionNaN;

import model.Categorias;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import util.HibernateUtil;


public class CrearCategoria {

	public static void main(String[] args) {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		// Crear la sesion

		Session session = HibernateUtil.getSession();

		try {

			// Iniciar transaccion
			session.beginTransaction();

			// Creamos una categoria

			for (int i = 7; i < 12; i++) {
				Categorias cat = new Categorias("Cat" + i + " desde hibernate");
				// Guardar categoria
				session.save(cat);
			}

			// commit de la transaccion
			session.getTransaction().commit();

		} finally {
			factory.close();
		}
	}

}
