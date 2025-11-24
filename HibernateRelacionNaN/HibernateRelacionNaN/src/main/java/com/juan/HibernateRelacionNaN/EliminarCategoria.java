package com.juan.HibernateRelacionNaN;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import model.*;
import util.HibernateUtil;

public class EliminarCategoria {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		// Crear la sesion

		Session session = HibernateUtil.getSession();

		try {

			// Iniciar transaccion
			session.beginTransaction();

			// Creamos una categoria

			Categorias cat = session.get(Categorias.class, 10);

			session.delete(cat);
			// commit de la transaccion
			session.getTransaction().commit();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			factory.close();
		}

	}

}
