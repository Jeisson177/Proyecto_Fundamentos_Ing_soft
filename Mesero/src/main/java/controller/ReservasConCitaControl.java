package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import controller.menuMesero.MesaControl;
import model.Reserva;
import services.GestionarReserva;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservasConCitaControl {
    @FXML
    private DatePicker calendario;
    @FXML
    private Button btnConsultar;
    @FXML
    private Button btnAtender;
    @FXML
    private TableView<Reserva> tabla_reservas;
    @FXML
    private TableColumn<Reserva, String> horariosColumn;

    private GestionarReserva gestionarReserva;

    public ReservasConCitaControl() {
        MesaControl mesaControl = new MesaControl();
        this.gestionarReserva = new GestionarReserva(mesaControl);
    }

    @FXML
    public void initialize() {
        horariosColumn.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));

        btnConsultar.setOnAction(event -> consultarHorarios());
        btnAtender.setOnAction(event -> atenderReserva());
    }

    private void consultarHorarios() {
        LocalDate fechaSeleccionada = calendario.getValue();
        if (fechaSeleccionada != null) {
            try {
                int mesaId = gestionarReserva.getMesa(); // Obtener el ID de la mesa actual
                List<String> horariosDisponibles = gestionarReserva.obtenerHorariosDisponibles(fechaSeleccionada, mesaId);

                tabla_reservas.getItems().clear();
                for (String horario : horariosDisponibles) {
                    // Asumiendo que '0' es un valor por defecto para idReserva, idCliente y idMesa no se utilizan aquí
                    Reserva reserva = new Reserva(0, 0, mesaId, LocalDateTime.parse(fechaSeleccionada + "T" + horario));
                    tabla_reservas.getItems().add(reserva);
                }
            } catch (Exception e) {
                mostrarAlerta("Error al obtener los horarios disponibles: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Por favor, selecciona una fecha.");
        }
    }

    private void atenderReserva() {
        Reserva reservaSeleccionada = tabla_reservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            LocalDate fechaSeleccionada = calendario.getValue();
            String hora = reservaSeleccionada.getFechaHora().toLocalTime().toString(); // Extraer solo la hora

            boolean reservaExitosa = gestionarReserva.GReserva(fechaSeleccionada, hora);
            if (reservaExitosa) {
                mostrarAlerta("Reserva atendida exitosamente.");
            } else {
                mostrarAlerta("Error al atender la reserva.");
            }
        } else {
            mostrarAlerta("Por favor, selecciona una reserva para atender.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void Atender(ActionEvent event) {
        atenderReserva();
    }

    public void ConsultarReserva(ActionEvent event) {
        consultarHorarios();
    }
}
