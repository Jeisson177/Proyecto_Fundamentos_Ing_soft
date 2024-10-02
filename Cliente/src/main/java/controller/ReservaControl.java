package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

    private GestionarReserva gestionarReserva = new GestionarReserva();  // Usar el servicio
    private MesaControl mesa= new MesaControl();
    @FXML
    public void initialize() {
        // Asociar la columna con los datos
        horariosColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()));
        Tabla.setItems(horariosDisponibles);  // Vincula la lista observable al TableView
    }

    @FXML
    public void ConsultarReserva(ActionEvent actionEvent) {
        LocalDate fechaSeleccionada = Calendario.getValue();  // Obtener la fecha seleccionada del DatePicker


        if (fechaSeleccionada != null) {
            try {
                // Consultar los horarios disponibles para la fecha seleccionada y la mesa especificada
                List<String> horarios = gestionarReserva.obtenerHorariosDisponibles(fechaSeleccionada, mesa.getMesa());

                if (horarios.isEmpty()) {
                    horariosDisponibles.clear();
                    horariosDisponibles.add("No hay horarios disponibles para la fecha " + fechaSeleccionada);
                } else {
                    horariosDisponibles.setAll(horarios);  // Mostrar los horarios disponibles
                }
                Tabla.refresh();
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlertaError("Error", "Hubo un problema al consultar los horarios.",1);
            }
        } else {
            mostrarAlertaError("Error", "No hay fecha especificada",1);
        }
    }

    private void mostrarAlertaError(String titulo, String contenido,int tipo) {
        if(tipo==1){//error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(contenido);
            alert.showAndWait();

        }else {//confirmacion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(contenido);
            alert.showAndWait();
        }

    }
    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        // Implementación futura
    }
    @FXML
    public void IrHome(ActionEvent actionEvent) {
        // Implementación futura
    }
    @FXML
    public void IrReserva(ActionEvent actionEvent) {
        // Implementación futura
    }
    @FXML
    public void Reservar(ActionEvent actionEvent) {
        String h = this.Tabla.getSelectionModel().getSelectedItem();
        if(h==null){
            mostrarAlertaError("ERROR","No has seleccionado hora",1);
        }
        else{
            LocalDate fechaSeleccionada = Calendario.getValue();
            if(gestionarReserva.GReserva(fechaSeleccionada,h)){
                mostrarAlertaError("Aprobado","La reserva fue hecha satisfactoriamente",2);
            }else{
                mostrarAlertaError("ERROR","No se realizo la reserva",1);
            }

        }

    }

}
