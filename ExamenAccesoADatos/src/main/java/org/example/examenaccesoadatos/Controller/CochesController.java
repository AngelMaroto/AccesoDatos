package org.example.examenaccesoadatos.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.examenaccesoadatos.DAO.CochesDAO;
import org.example.examenaccesoadatos.DAO.CochesDAOimpl;
import org.example.examenaccesoadatos.model.Coches;
import org.example.examenaccesoadatos.model.Marcas;
import org.example.examenaccesoadatos.model.Reparacion;
import org.example.examenaccesoadatos.util.HibernateUtil;
import org.hibernate.Session;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CochesController {

    @FXML private TextField txtMatriculaCoches, txtKmCoches;
    @FXML private ComboBox<Marcas> cmbMarca;
    @FXML private Button btnModificar;

    private Coches coche;
    CochesDAO cochesDAO = new CochesDAOimpl();


    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @FXML
    private void initialize(){
        txtMatriculaCoches.setDisable(true);
        cargaMarca();
    }

    public void setCoche(Coches car){
        this.coche = car;
        txtKmCoches.setText(car.getKm());
        txtMatriculaCoches.setText(car.getMatricula());
    }

    private void cargaMarca(){
        cmbMarca.getItems().clear();
        ArrayList<Marcas> marcas;
        try {
            marcas = JSON_MAPPER.readValue(new File("src/main/resources/JSON/marcas.json"),
                    JSON_MAPPER.getTypeFactory().constructCollectionType
                            (ArrayList.class, Marcas.class));

            ObservableList<Marcas> datos = FXCollections.observableArrayList(marcas);
            cmbMarca.setItems(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void modificarCoche(){
        String matricula = txtMatriculaCoches.getText();
        String km = txtKmCoches.getText();
        Marcas marcaModificada = cmbMarca.getValue();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {


            session.beginTransaction();;
            coche.setKm(km);
            coche.setMarca(String.valueOf(marcaModificada));
            cochesDAO.modificarCoche(session, coche);
            session.getTransaction().commit();

            mostrarInfo("Coche Actualizado");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/reparaciones.fxml"));
                Scene scene = new Scene(loader.load());

                Stage stage = new Stage();
                stage.setTitle("Reparaciones");
                stage.setScene(scene);
                stage.show();

                Stage currenStage = (Stage) btnModificar.getScene().getWindow();
                currenStage.close();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al cargar la interfaz");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarError("Error al modificar el coche.");
        }
    }

    // --- Métodos auxiliares para mostrar alertas ---
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
