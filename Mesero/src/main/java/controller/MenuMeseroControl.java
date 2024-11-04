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

public class MenuMeseroControl {
    @FXML
    public Button atender_reserva;
    @FXML
    public Button imprimir_factura;
    @FXML
    public Button menu_button;

    private boolean reservaatendida = false;

    @FXML
    public void menu_click(ActionEvent event) {
        cargarPantalla("Menu.fxml");
    }
    @FXML
    public void atender_reserva_click(ActionEvent event) {
        reservaatendida = true;
        cargarPantalla("/vista/Reservas.fxml");
    }
    @FXML
    public void imprimir_factura_click(ActionEvent event) {
        if (reservaatendida)
        {
            cargarPantalla("/vista/Factura.fxml");
        }
        else {
            mostrarAdvertencia();
        }
    }
    @FXML
    private void cargarPantalla(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) atender_reserva.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void mostrarAdvertencia() {

        Alert alerta = new Alert(Alert.AlertType.WARNING, "No se ha atendido ninguna reserva, ¿Deseas atender una ahora?", ButtonType.OK);
        alerta.setHeaderText("Advertencia");
        alerta.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                atender_reserva_click(null);
            }
        });
    }

    }

