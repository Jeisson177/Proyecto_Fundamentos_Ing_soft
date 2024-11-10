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
import java.util.Optional;

public class InicioControlMesero {

    @FXML
    public Button btnAtdReserva;
    public Button btnImprimir;
    public Button btnMenu;
    public Button btnAtdSinReserva;

    // Variable estática para gestionar el estado de la reserva atendida
    private static Optional<Reserva> reservaAtendida = Optional.empty();

    @FXML
    public void IrAtenderConReserva(ActionEvent actionEvent) {
        cargarPantalla("/vista/ReservasConCita.fxml");
    }

    @FXML
    public void IrImprimirFactura(ActionEvent actionEvent) {
        // Verifica si la reserva ha sido atendida antes de permitir la impresión
        if (reservaAtendida.isPresent()) {
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
        // reservaAtendida = Optional.of(new Reserva()); // simulación de atender sin reserva
        // cargarPantalla("/vista/Menu.fxml");
    }

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

    // Método estático para establecer la reserva atendida
    public static void setReservaAtendida(Reserva reserva) {
        reservaAtendida = Optional.of(reserva);
    }

    // Método opcional para verificar si hay una reserva atendida
    public static boolean isReservaAtendida() {
        return reservaAtendida.isPresent();
    }
}
