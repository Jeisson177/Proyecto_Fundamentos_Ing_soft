package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.GestionarReserva;

import java.time.LocalDate;

public class ReservaControl {
    @FXML
    private TableView<String> Tabla;
    @FXML
    private TableColumn<String, String> horariosColumn;
    @FXML
    private Button btnReserva;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnHome;
    @FXML
    private DatePicker Calendario;
    @FXML
    private Button btnConsultar;
    private ObservableList<String> horariosDisponibles = FXCollections.observableArrayList();

    private GestionarReserva gestionarReserva = new GestionarReserva();  // Usar el servicio

    @FXML
    public void initialize() {
        // Asociar la columna con los datos
        horariosColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        Tabla.setItems(horariosDisponibles);  // Vincula la lista observable al TableView
    }

    @FXML
    public void ConsultarReserva(ActionEvent actionEvent) {
        LocalDate fechaSeleccionada = Calendario.getValue();  // Obtener la fecha seleccionada del DatePicker

        if (fechaSeleccionada != null) {
            String fechaConsulta = fechaSeleccionada.toString();  // Convertir LocalDate a String

            // Verificar si la fecha está ocupada utilizando el servicio
            if (gestionarReserva.isFechaOcupada(fechaConsulta)) {
                horariosDisponibles.clear();  // Limpiar la tabla
                horariosDisponibles.add("La fecha " + fechaConsulta + " ya está ocupada.");
            } else {
                cargarHorariosDisponibles();  // Cargar horarios disponibles
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No hay fecha especificada");
            alert.showAndWait();
        }
    }

    private void cargarHorariosDisponibles() {
        horariosDisponibles.clear();  // Limpiar la lista observable antes de cargar nuevos datos
        horariosDisponibles.addAll(gestionarReserva.obtenerHorariosDisponibles());  // Cargar datos desde el servicio
    }

    public void IrMenu(ActionEvent actionEvent) {
        // Implementación futura
    }

    public void IrHome(ActionEvent actionEvent) {
        // Implementación futura
    }

    public void IrReserva(ActionEvent actionEvent) {
        // Implementación futura
    }
}
