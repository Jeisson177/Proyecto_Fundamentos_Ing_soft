package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RedirijirAdmin {

    public void IrHome(Button Home) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/inicio.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Home"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Home.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Home: " + e.getMessage());
        }
    }

    public void IrMenu(Button Menu) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/Menu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Home"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Menu.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Home: " + e.getMessage());
        }
    }

    public void IrInicio(Button Inicio) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Inicio.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Inicio"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Inicio.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Home: " + e.getMessage());
        }
    }

    public void IrInventario(Button Anterior) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/modificarInventario.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificar Inventario"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Anterior.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Home: " + e.getMessage());
        }
    }

    public void IrPlato(Button Plato) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/modificarPlato.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificar Plato"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) Plato.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Home: " + e.getMessage());
        }
    }

}
