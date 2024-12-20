package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.GestionarReserva;

import java.time.LocalDate;
import java.util.List;

public class ReservaControl {
    @FXML
    public Button btnReservar;
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
    private MesaControl mesaControl;  // Aquí almacenamos la instancia de MesaControl
    private GestionarReserva gestionarReserva;  // No la inicializamos todavía

    public void setMesaControl(MesaControl mesaControl) {
        this.mesaControl = mesaControl;
        this.gestionarReserva = new GestionarReserva(mesaControl);  // Inicializamos GestionarReserva con la instancia correcta
    }

    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        // Asociar la columna con los datos
        horariosColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        Tabla.setItems(horariosDisponibles);  // Vincula la lista observable al TableView
    }

    @FXML
    public void ConsultarReserva(ActionEvent actionEvent) {
        LocalDate fechaSeleccionada = Calendario.getValue();  // Obtener la fecha seleccionada del DatePicker

        if (fechaSeleccionada != null && mesaControl != null) {  // Asegúrate de que mesaControl no es null
            try {
                // Consultar los horarios disponibles para la fecha seleccionada y la mesa especificada
                List<String> horarios = gestionarReserva.obtenerHorariosDisponibles(fechaSeleccionada, mesaControl.getMesa());

                if (horarios.isEmpty()) {
                    horariosDisponibles.clear();
                    horariosDisponibles.add("No hay horarios disponibles para la fecha " + fechaSeleccionada);
                } else {
                    horariosDisponibles.setAll(horarios);  // Mostrar los horarios disponibles
                }
                Tabla.refresh();
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlertaError("Error", "Hubo un problema al consultar los horarios.", 1);
            }
        } else {
            mostrarAlertaError("Error", "No hay fecha especificada o la mesa no se ha seleccionado.", 1);
        }
    }

    private void mostrarAlertaError(String titulo, String contenido, int tipo) {
        if (tipo == 1) {  // error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(contenido);
            alert.showAndWait();
        } else {  // confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(contenido);
            alert.showAndWait();
        }
    }

    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(btnMenu);
    }

    @FXML
    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(btnHome);
    }

    @FXML
    public void IrReserva(ActionEvent actionEvent) {
        Ira.IrReserva(btnReserva);
    }

    @FXML
    public void Reservar(ActionEvent actionEvent) {
        String h = this.Tabla.getSelectionModel().getSelectedItem();
        if (h == null) {
            mostrarAlertaError("ERROR", "No has seleccionado hora", 1);
        } else {
            LocalDate fechaSeleccionada = Calendario.getValue();
            if (gestionarReserva.GReserva(fechaSeleccionada, h)) {
                mostrarAlertaError("Aprobado", "La reserva fue hecha satisfactoriamente", 2);
            } else {
                mostrarAlertaError("ERROR", "No se realizó la reserva", 1);
            }
        }
    }
}
