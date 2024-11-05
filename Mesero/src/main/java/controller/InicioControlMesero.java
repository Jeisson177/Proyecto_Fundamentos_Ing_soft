package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioControlMesero {

    @FXML
    public Button btnAtdReserva;
    public Button btnImprimir;
    public Button btnMenu;
    public Button btnAtdSinReserva;

    // Booleano para saber si ya se está atendiendo una reserva
    // Si no se ha atendido una aparece una advertencia
    private boolean reservaatendida = false;

    @FXML
    public void IrAtenderConReserva(ActionEvent actionEvent) {
        reservaatendida = true;
        cargarPantalla("/vista/ReservasConCita.fxml");
    }

    @FXML
    public void IrImprimirFactura(ActionEvent actionEvent) {
        if (reservaatendida) {
            cargarPantalla("/vista/Factura.fxml");
        } else {
            mostrarAdvertencia();
        }
    }

    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        cargarPantalla("Menu.fxml");
    }

    @FXML
    public void IrAtenderSinReserva(ActionEvent actionEvent) {

        reservaatendida = true; // asumo que se atiende algo
        // cargarPantalla("/vista/Menu.fxml"); // idea para despues
    }

    @FXML
    private void cargarPantalla(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) btnAtdReserva.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mostrarAdvertencia() {
        Alert alerta = new Alert(Alert.AlertType.WARNING,
                "No se ha atendido ninguna reserva, ¿Deseas atender una ahora?",
                ButtonType.OK);
        alerta.setHeaderText("Advertencia");
        alerta.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                IrAtenderConReserva(null);
            }
        });
    }
}
