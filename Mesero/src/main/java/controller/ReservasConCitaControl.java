package controller;

import controller.menuMesero.MesaControl;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import services.GestionarReserva;

import java.time.LocalDate;
import java.util.List;

public class ReservasConCitaControl {

    @FXML
    private TableView<Reserva> tabla_reservas;
    @FXML
    private TableColumn<Reserva, String> fechaColumn;
    @FXML
    private TableColumn<Reserva, String> horaColumn;
    @FXML
    private TableColumn<Reserva, Integer> mesaColumn;
    @FXML
    private DatePicker Calendario;
    @FXML
    private Button btnConsultar;
    @FXML
    private Button btnAtender;

    private GestionarReserva gestionarReserva;

    public ReservasConCitaControl() {
        this.gestionarReserva = new GestionarReserva(new MesaControl());
    }

    // Inicializa las columnas del TableView para las reservas
    public void initialize() {
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        mesaColumn.setCellValueFactory(new PropertyValueFactory<>("idMesa"));
    }

    @FXML
    private void ConsultarReserva(ActionEvent actionEvent) {
        LocalDate fechaSeleccionada = Calendario.getValue();
        if (fechaSeleccionada != null) {
            List<Reserva> reservas = gestionarReserva.obtenerReservasPorFecha(fechaSeleccionada);
            tabla_reservas.getItems().setAll(reservas);
        } else {
            showAlert("Por favor, selecciona una fecha.");
        }
    }

    @FXML
    private void Atender(ActionEvent actionEvent) {
        Reserva reservaSeleccionada = tabla_reservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            InicioControlMesero.setReservaAtendida(reservaSeleccionada);
            showAlert("Reserva atendida exitosamente.");
        } else {
            showAlert("Por favor, selecciona una reserva.");
        }
    }

    private void showAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("aaaaa prueba");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
