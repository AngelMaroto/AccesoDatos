package org.example.examenaccesoadatos.DAO;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.examenaccesoadatos.Connection.MongoDBConnection;
import org.example.examenaccesoadatos.model.Reparacion;

public class ReparacionMongoDAOimpl implements ReparacionMongoDAO{
    @Override
    public void crearReparacionMongo(Reparacion reparacion) {
        try {
            // CORRECCIÓN 1: Cambia el nombre de la colección a "Reparaciones"
            MongoCollection<Document> coleccion = MongoDBConnection.getDatabase().getCollection("Reparaciones");

            Document doc = new Document()
                    .append("idreparacion", reparacion.getIdReparacion())

                    // CORRECCIÓN 2: Guarda solo el ID del coche, no el objeto entero
                    // Si pones reparacion.getCoche() falla porque no sabe convertirlo
                    .append("idcoche", reparacion.getCoche().getIdCoche())

                    .append("fechareparacion", reparacion.getFechaReparacion().toString())
                    // Ojo: asegúrate de usar los nombres exactos que quieres en Mongo (con tilde o sin ella según tu script)
                    .append("descripción", reparacion.getDescripcion()) // En tu script Mongo pusiste "descripción" con tilde
                    .append("precio", reparacion.getPrecio())
                    .append("pagado", reparacion.getPagado());

            coleccion.insertOne(doc);
            System.out.println("Guardado en MongoDB correctamente"); // Mensaje de control

        } catch (Exception e) {
            System.err.println("ERROR AL GUARDAR EN MONGO:");
            e.printStackTrace(); // ¡Esto te dirá por qué falla si vuelve a pasar!
        }
    }
}
