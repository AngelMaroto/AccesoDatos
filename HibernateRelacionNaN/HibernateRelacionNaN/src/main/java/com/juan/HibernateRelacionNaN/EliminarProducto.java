package com.juan.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.*;
import util.HibernateUtil;

public class EliminarProducto {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		// Crear la sesion

		Session session = HibernateUtil.getSession();

		try {

			// Iniciar transaccion
			session.beginTransaction();

			// Creamos una categoria

			Productos prod = session.get(Productos.class, 6);

			session.delete(prod);
			// commit de la transacci�n
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

}
