package controller;

import javafx.collections.FXCollections;
import controller.RedirijirAdmin;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import Entities.Reserva;
import services.verReservaServicio;
import java.time.LocalDateTime;
import java.util.List;

public class verReservasControl {
    public Button regresarBoton;
    public Button buscarBoton;
    public TextField nombreClienteBoton;
    public TextField idReservaBoton;
    public DatePicker ingresoFecha;
    public Text textoReservas;
    public TableColumn<Reserva, Integer> columnaIdReserva;
    public TableColumn<Reserva, Integer> columnaIdCliente;
    public TableColumn<Reserva, LocalDateTime> columnaFecha;
    public TableColumn<Reserva, Integer> columnaMesa;
    public TableView<Reserva> tablaReservas;
    public Text reservaText;
    public Button eliminarBoton;
    private verReservaServicio servicio = new verReservaServicio();
    private List<Reserva> reservas;
    private RedirijirAdmin red = new RedirijirAdmin();

    public void verReservasPorId(TextField idReservaBoton) {
        Reserva reserva = servicio.verReserva(idReservaBoton.getText());
        if (reserva != null) {
            reservas = List.of(reserva);
            tablaReservas.setItems(FXCollections.observableArrayList(reservas));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No se encontró ninguna reserva con ese ID.");
            alert.show();
        }
    }

    public void verReservasPorNombreCliente(TextField nombreClienteBoton) {
        reservas = servicio.verReservaCliente(nombreClienteBoton.getText());
        if (reservas != null && !reservas.isEmpty()) {
            tablaReservas.setItems(FXCollections.observableArrayList(reservas));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No se encontraron reservas para ese cliente.");
            alert.show();
        }
    }

    public void verReservasPorFecha(DatePicker ingresoFecha) {
        reservas = servicio.verReservaFecha(ingresoFecha.getValue());
        if (reservas != null && !reservas.isEmpty()) {
            tablaReservas.setItems(FXCollections.observableArrayList(reservas));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No se encontraron reservas para esa fecha.");
            alert.show();
        }
    }

    public void buscarReserva() {
        if (!idReservaBoton.getText().isEmpty()) {
            verReservasPorId(idReservaBoton);
        } else if (!nombreClienteBoton.getText().isEmpty()) {
            verReservasPorNombreCliente(nombreClienteBoton);
        } else if (ingresoFecha.getValue() != null) {
            verReservasPorFecha(ingresoFecha);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, complete al menos un criterio de búsqueda.");
            alert.show();
        }
    }

    public void eliminarReserva() {
        Reserva seleccionada = tablaReservas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            boolean exito = servicio.eliminarReserva(seleccionada.getId_Reserva());
            if (exito) {
                tablaReservas.getItems().remove(seleccionada);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "La reserva ha sido eliminada exitosamente.");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se pudo eliminar la reserva.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, seleccione una reserva para eliminar.");
            alert.show();
        }
    }

    public void regresarInicio(){ red.IrInicio(regresarBoton); }

    public void initialize() {
        columnaIdReserva.setCellValueFactory(new PropertyValueFactory<>("id_Reserva"));
        columnaIdCliente.setCellValueFactory(new PropertyValueFactory<>("id_Cliente"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaReserva"));
        columnaMesa.setCellValueFactory(new PropertyValueFactory<>("id_mesa"));
        buscarBoton.setOnAction(e -> buscarReserva());
    }

}
