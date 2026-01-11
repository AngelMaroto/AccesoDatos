package org.example.examenaccesoadatos.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.examenaccesoadatos.DAO.*;
import org.example.examenaccesoadatos.model.Coches;
import org.example.examenaccesoadatos.model.Reparacion;
import org.example.examenaccesoadatos.util.HibernateUtil;
import org.hibernate.Session;
import org.example.examenaccesoadatos.model.Reparacion;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ReparacionesController {
    @FXML
    private TextField txtMatricula, txtMarca, txtKm, txtNumero, txtDescripcion, txtPrecio;
    @FXML private DatePicker dpFecha;
    @FXML private TableView<Reparacion> tableReparaciones;
    @FXML private TableColumn<Reparacion, Integer> colNumero;
    @FXML private TableColumn<Reparacion, LocalDate> colFecha;
    @FXML private TableColumn<Reparacion, String> colDescripcion;
    @FXML private TableColumn<Reparacion, String> colPrecio;
    @FXML private TableColumn<Reparacion, String> colPagado;
    @FXML private ToggleGroup pagadoGroup;
    @FXML private RadioButton siRB;
    @FXML private RadioButton noRB;
    @FXML private Button btnModificar;

    CochesDAO cochesDAO = new CochesDAOimpl();
    ReparacionesDAO reparacionesDAO = new ReparacionesDAOimpl();
    ReparacionMongoDAO reparacionMongoDAO = new ReparacionMongoDAOimpl();
    private Coches car;

    @FXML
    public void initialize(){
        colNumero.setCellValueFactory(new PropertyValueFactory<>("idReparacion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaReparacion"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPagado.setCellValueFactory(new PropertyValueFactory<>("pagado"));

        txtMatricula.setOnAction(e ->{
            try {
                buscarCochePorMatricula();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        tableReparaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Cargar datos en los campos de texto
                txtNumero.setText(String.valueOf(newSelection.getIdReparacion()));
                txtDescripcion.setText(newSelection.getDescripcion());
                txtPrecio.setText(newSelection.getPrecio());
                dpFecha.setValue(newSelection.getFechaReparacion());

                // Cargar el RadioButton correcto (Ahora que es String "Si"/"No")
                if ("Si".equalsIgnoreCase(newSelection.getPagado())) {
                    siRB.setSelected(true);
                } else {
                    noRB.setSelected(true);
                }
            }
        });
    }

    private void buscarCochePorMatricula() throws SQLException{
        String matricula = txtMatricula.getText();
        Coches coche = cochesDAO.buscarPorMatricula(matricula);

        if (coche !=null){
            car = coche;
            txtMarca.setText(coche.getMarca());
            txtKm.setText(coche.getKm());

            txtKm.setDisable(true);
            txtMarca.setDisable(true);
        } else {
            mostrarError("Coche no encontrado con Matriucla: "+matricula);
        }
    }

    @FXML
    private void verReparaciones() throws SQLException{
        int idCoche = car.getIdCoche();
        List<Reparacion> reparaciones = reparacionesDAO.obtenerReparacionesPorCoche(idCoche);

        ObservableList<Reparacion> reparacionesFX = FXCollections.observableArrayList(reparaciones);
        tableReparaciones.setItems(reparacionesFX);

        if (reparaciones.isEmpty()){
            mostrarWarning("El coche no tiene reparaciones");
        }
    }

    @FXML
    private void limpiarCamposReparacion(){
        txtNumero.clear();
        txtDescripcion.clear();
        dpFecha.setValue(null);
        txtPrecio.clear();
        noRB.setSelected(true);
        tableReparaciones.getSelectionModel().clearSelection();
    }

    @FXML
    private void pagarReparacion(){
        // 1. Obtener la reparación seleccionada
        Reparacion reparacion = tableReparaciones.getSelectionModel().getSelectedItem();

        // 2. Validaciones
        if (reparacion == null) {
            mostrarError("Debes seleccionar una reparación de la tabla para pagarla.");
            return;
        }

        // Comprobar si ya está pagada (Ojo: ahora comparamos con String "Si")
        if ("Si".equalsIgnoreCase(reparacion.getPagado())) {
            mostrarWarning("Esta reparación ya está pagada.");
            return;
        }

        // 3. Actualizar el estado
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Cambiamos el estado a "Si"
            reparacion.setPagado("Si");

            // Llamamos al DAO para actualizar en BD (Punto 15)
            reparacionesDAO.actualizarReparacion(session, reparacion);

            session.getTransaction().commit();

            // 4. Actualizar la interfaz (TableView)
            tableReparaciones.refresh();
            mostrarInfo("Reparación pagada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al pagar la reparación: " + e.getMessage());
        }
    }

    @FXML
    private void crearNuevaReparacion(){
        if (car==null){
            mostrarError("Primero busca y selecciona el coche");
        }

        LocalDate fecha = dpFecha.getValue();
        String seleccionado =((RadioButton) pagadoGroup.getSelectedToggle()).getText();
        boolean seleccionadoSelect, mysql, mongo;
        String descripcion = txtDescripcion.getText();
        String precio = txtPrecio.getText();

        if (fecha == null || pagadoGroup == null){
            mostrarError("completa los campos");
        }

        String pagadoTexto=((RadioButton)pagadoGroup.getSelectedToggle()).getText();


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            Reparacion nuevaReparacion = new Reparacion();
            nuevaReparacion.setCoche(car);
            nuevaReparacion.setFechaReparacion(fecha);
            nuevaReparacion.setDescripcion(descripcion);
            nuevaReparacion.setPrecio(precio);
            nuevaReparacion.setPagado(pagadoTexto);

            reparacionesDAO.crearReparacion(session, nuevaReparacion);
            session.getTransaction().commit();

            reparacionMongoDAO.crearReparacionMongo(nuevaReparacion);

        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarError("Error guardando la reparacion con Hibernate.");
            return;
        }

        actualizarCitasTableView();
        limpiarCamposReparacion();
        mostrarInfo("¡Nueva Reparacion guardada!");
    }

    @FXML
    private void borrarReparacionSeleccionada()  {
        Reparacion reparacion = tableReparaciones.getSelectionModel().getSelectedItem();

        if (reparacion==null){
            mostrarError("Selecciona una reparacion para borrar");
        }
        if (pagadoGroup.getSelectedToggle()==noRB){
            mostrarError("No se puede borrar ya que no esta pagada");
            return;
        }

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            reparacionesDAO.borrarCita(session, reparacion);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("error al borrar la cita");
            return;
        }
        actualizarCitasTableView();
    }


    private void actualizarCitasTableView() {
        try {
            List<Reparacion> reparaciones = reparacionesDAO.obtenerReparacionesPorCoche(car.getIdCoche());
            tableReparaciones.setItems(FXCollections.observableArrayList(reparaciones));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void abrir(){
        abrirInterfazCoches(car);
    }

    //Metodo para abrir la interfaz de modificar coche
    private void abrirInterfazCoches(Coches coch){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/coches.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.setTitle("Crear Paciente");
            stage.setScene(scene);
            stage.show();

            CochesController controller = loader.getController();
            controller.setCoche(coch);

            Stage currenStage = (Stage) btnModificar.getScene().getWindow();
            currenStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la interfaz");
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
    private void mostrarWarning(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
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

