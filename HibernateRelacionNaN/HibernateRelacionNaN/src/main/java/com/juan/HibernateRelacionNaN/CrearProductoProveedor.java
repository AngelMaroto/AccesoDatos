package com.juan.HibernateRelacionNaN;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import model.*;

public class CrearProductoProveedor {

    public static void main(String[] args) {

        Transaction transaction = null;

        SessionFactory factory = HibernateUtil.getSessionFactory();
        // Crear la sesion

        Session session = HibernateUtil.getSession();


        try {

            // Iniciar transaccion
            transaction = session.beginTransaction();

            Proveedores ana = new Proveedores("Supermercados Ana", "B221133", "Valladolid");

            session.save(ana);

            Productos pepino = new Productos("Pepino", "Frances", 5);
            Productos endivia = new Productos("Endivia", "Nada envidiosa", 7);

            ana.addProducto(pepino);
            ana.addProducto(endivia);
            session.save(pepino);
            session.save(endivia);
            transaction.commit();
            //ahora al reves voy a asignar uno/varios proveedores a un producto
            Productos garbanzos = new Productos("Garbanzos", "D.O. Segovia", 10);
           session.save(garbanzos);

           //ahora creo los proveedores
            transaction = session.beginTransaction();
            Proveedores rocio = new Proveedores("La Tienda de Rocio", "B78945", "Segovia");
            Proveedores felipe = new Proveedores("Felipe el rey del Huerto", "B4556123", "Leon");
            //esto inserta en la tabla producto_proveedor
            garbanzos.addProveedor(rocio);
            garbanzos.addProveedor(felipe);
            //esto inserta en la tabla proveedor
            session.save(rocio);
            session.save(felipe);

            transaction.commit();
            session.close();



        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {
            factory.close();
        }

    }

}
