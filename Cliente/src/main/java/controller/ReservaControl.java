package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.ReservaRepository;

import java.time.LocalDate;

public class ReservaControl {
    @FXML
    private TableView Tabla;
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

    private ReservaRepository reservaRepo = new ReservaRepository();
    // @FXML
   // private Label welcomeText;

    //@FXML
    //protected void onHelloButtonClick() {
    //    welcomeText.setText("Welcome to JavaFX Application!");
    //}
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

            // Verificar si la fecha está ocupada
            if (reservaRepo.FechaOcupada(fechaConsulta)) {
                horariosDisponibles.clear();  // Limpiar la tabla
                horariosDisponibles.add("La fecha " + fechaConsulta + " ya está ocupada.");
            } else {
                cargarHorariosDisponibles();  // Cargar horarios disponibles
            }
        } else {
            horariosDisponibles.clear();  // Limpiar la tabla si no hay fecha seleccionada
            horariosDisponibles.add("Por favor, selecciona una fecha.");
        }


    }
    private void cargarHorariosDisponibles() {
        horariosDisponibles.clear();  // Limpiar la lista observable antes de cargar nuevos datos
        horariosDisponibles.addAll(reservaRepo.obtenerFechasDisponibles());  // Cargar datos desde el repositorio
    }


    public void IrMenu(ActionEvent actionEvent) {
        
    }

    public void IrHome(ActionEvent actionEvent) {
    }

    public void IrReserva(ActionEvent actionEvent) {
    }
}