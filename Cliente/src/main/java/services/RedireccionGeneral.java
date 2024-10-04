package services;

import com.example.cliente.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class RedireccionGeneral {

    public void IrHome(Button Home) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Menu.fxml"));
        Parent root = loader.load();

        // Crea una nueva escena
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Home"); // Título de la nueva ventana
        stage.show();

        // Opcionalmente, cierra la ventana actual
        ((Stage) Home.getScene().getWindow()).close();

    }
    public void IrMenu(Button Menu) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Menu.fxml"));
        Parent root = loader.load();

        // Crea una nueva escena
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Menu"); // Título de la nueva ventana
        stage.show();

        // Opcionalmente, cierra la ventana actual
        ((Stage) Menu.getScene().getWindow()).close();
    }

    public void IrReserva(Button Reserva) throws IOException {
        //cambiar por mesa
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Antipasti.fxml"));
        Parent root = loader.load();

        // Crea una nueva escena
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login"); // Título de la nueva ventana
        stage.show();

        // Opcionalmente, cierra la ventana actual
        ((Stage) Reserva.getScene().getWindow()).close();
    }
    public void IrLogin(Button login) throws IOException {
        //cambiar Antipasti por login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Antipasti.fxml"));
        Parent root = loader.load();

        // Crea una nueva escena
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login"); // Título de la nueva ventana
        stage.show();

        // Opcionalmente, cierra la ventana actual
        ((Stage) login.getScene().getWindow()).close();
    }

}
