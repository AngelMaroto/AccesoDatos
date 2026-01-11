package org.example.examenaccesoadatos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.examenaccesoadatos.Connection.DBConnection;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/reparaciones.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setTitle("Reparaciones");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() throws Exception {
        DBConnection.desconectar();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
