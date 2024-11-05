/*
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Reserva;
import repository.consultarReservasRepository;

import java.time.LocalDate;
import java.util.List;

public class ReservasControl {

    @FXML
    private DatePicker Calendario;
    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnAtender;

    @FXML
    private TableView<Reserva> tabla_reservas;

    @FXML
    private TableColumn<Reserva, String> horariosColumn; // Columna de horarios de reservas

    private consultarReservasRepository repository; // Repositorio para manejar reservas
    private Integer reservaIdAtendida; // Variable para almacenar el ID de la reserva atendida

    public ReservasControl() {
        this.repository = new consultarReservasRepository(); // Inicializa el repositorio
    }

    @FXML
    public void initialize() {
        horariosColumn.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));

        // Configura la acción del botón "Consultar"
        btnConsultar.setOnAction(event -> consultarReservas());

        // Configura la acción del botón "Atender"
        btnAtender.setOnAction(event -> atenderReserva());
    }

    private void consultarReservas() {
        LocalDate fechaSeleccionada = Calendario.getValue();
        if (fechaSeleccionada != null) {
            // Obtiene reservas de la fecha seleccionada
            List<Reserva> reservas = repository.obtenerReservasPorFecha(fechaSeleccionada);
            tabla_reservas.getItems().clear(); // Limpia la tabla antes de mostrar resultados
            tabla_reservas.getItems().addAll(reservas); // Agrega las reservas a la tabla
        } else {
            mostrarAlerta("Por favor, selecciona una fecha."); // Mensaje de advertencia
        }
    }

    private void atenderReserva() {
        // Obtiene la reserva seleccionada de la tabla
        Reserva reservaSeleccionada = tabla_reservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            reservaIdAtendida = reservaSeleccionada.getIdReserva(); // Almacena el ID de la reserva atendida
            repository.atenderReserva(reservaIdAtendida); // Actualiza la reserva en el repositorio
            tabla_reservas.getItems().remove(reservaSeleccionada); // Elimina la reserva de la tabla
        } else {
            mostrarAlerta("Por favor, selecciona una reserva para atender."); // Mensaje de advertencia
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje); // Contenido de la alerta
        alert.showAndWait(); // Muestra la alerta y espera a que el usuario la cierre
    }

    public void Atender(ActionEvent event) {
        atenderReserva();
    }

    public void ConsultarReserva(ActionEvent event) {
        consultarReservas();
    }
}*/
