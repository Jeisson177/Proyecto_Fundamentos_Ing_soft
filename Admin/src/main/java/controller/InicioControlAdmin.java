package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioControlAdmin {


    public Button btnplato;
    public Button btnInventario;
    public Button btnMenu;
    public Button btnmeseros;
    public Button btnreservas;
    public Button btnmesas;

    private RedirijirAdmin Ira = new RedirijirAdmin();

    public void IrEditarMesas(ActionEvent actionEvent) {;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ModificarMesas.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificar mesas"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) btnmesas.getScene().getWindow()).close();
        } catch (IOException e) {
            System.err.println("Error al cargar la vista Home: " + e.getMessage());
        }
    }

    public void IrPlato(ActionEvent actionEvent) {
        Ira.IrPlato(btnplato);
    }

    public void IrInventario(ActionEvent actionEvent) {
        Ira.IrInventario(btnInventario);
    }

    public void IrEditarMeseros(ActionEvent actionEvent) {
        Ira.IrEditarMeseros(btnmeseros);
    }

    public void IrVerReservas(ActionEvent actionEvent) {Ira.IrVerReserva(btnreservas);}
}
