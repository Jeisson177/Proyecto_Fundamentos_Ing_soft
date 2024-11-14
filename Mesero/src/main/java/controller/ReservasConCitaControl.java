package controller;

import controller.menuMesero.MenuControl;
import controller.menuMesero.MesaControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.GestionarReserva;
import services.ReservaService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ReservasConCitaControl {

    @FXML
    public TableView<Reserva> tabla_reservas;
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
    @FXML
    private Button btnInicio;

    private GestionarReserva gestionarReserva;
    private Reserva reservaSeleccionada;
    private int idReservaSeleccionada;

    public ReservasConCitaControl() {
        this.gestionarReserva = new GestionarReserva(new MesaControl());
    }

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
        if (reservaSeleccionada != null) {
            if (gestionarReserva.esReservaActual(reservaSeleccionada)) {
                ReservaService.getInstance().setReservaSeleccionada(reservaSeleccionada);
                cargarMenu();
            } else {
                showAlert("Solo se pueden atender reservas actuales.");
            }
        } else {
            showAlert("Por favor, selecciona una reserva.");
        }
    }

    @FXML
    private void handleReservaSelection(MouseEvent mouseEvent) {
        reservaSeleccionada = tabla_reservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            idReservaSeleccionada = reservaSeleccionada.getIdReserva();
            System.out.println("ID de reserva seleccionada: " + idReservaSeleccionada);
        }
    }


    private void showAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/Menu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Menú");
            stage.setScene(new Scene(root));
            stage.show();

            Stage currentStage = (Stage) btnAtender.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error al cargar el menú.");
        }
    }
    @FXML
    private void irInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Inicio.fxml"));
            Stage stage = (Stage) btnInicio.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
