package controller.menuMesero;

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
    private MesaControl mesaControl;
    private GestionarReserva gestionarReserva;
    private int idUsuario; // Nuevo campo para almacenar el ID del usuario

    // Método para configurar MesaControl y GestionarReserva
    public void setMesaControl(MesaControl mesaControl) {
        this.mesaControl = mesaControl;
        this.gestionarReserva = new GestionarReserva(mesaControl);
    }

    // Método para configurar el ID del usuario (llamado cuando se carga la sesión del usuario)
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        horariosColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        Tabla.setItems(horariosDisponibles);
    }

    @FXML
    public void ConsultarReserva(ActionEvent actionEvent) {
        LocalDate fechaSeleccionada = Calendario.getValue();

        if (fechaSeleccionada != null && mesaControl != null) {
            try {
                List<String> horarios = gestionarReserva.obtenerHorariosDisponibles(fechaSeleccionada, mesaControl.getMesa());

                if (horarios.isEmpty()) {
                    horariosDisponibles.clear();
                    horariosDisponibles.add("No hay horarios disponibles para la fecha " + fechaSeleccionada);
                } else {
                    horariosDisponibles.setAll(horarios);
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

    @FXML
    public void Reservar(ActionEvent actionEvent) {
        String horaSeleccionada = this.Tabla.getSelectionModel().getSelectedItem();
        if (horaSeleccionada == null) {
            mostrarAlertaError("ERROR", "No has seleccionado una hora", 1);
        } else {
            LocalDate fechaSeleccionada = Calendario.getValue();
            if (gestionarReserva.crearReserva(fechaSeleccionada, horaSeleccionada, idUsuario)) {
                mostrarAlertaError("Aprobado", "La reserva fue hecha satisfactoriamente", 2);
            } else {
                mostrarAlertaError("ERROR", "No se realizó la reserva", 1);
            }
        }
    }

    private void mostrarAlertaError(String titulo, String contenido, int tipo) {
        Alert alert;
        if (tipo == 1) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
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
}
