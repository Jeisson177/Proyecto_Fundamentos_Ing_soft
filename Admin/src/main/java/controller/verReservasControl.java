package controller;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Entities.Reserva;
import services.verReservaServicio;

public class verReservasControl {
    public Button regresarBoton;
    public Button buscarBoton;
    public TextField nombreClienteBoton;
    public TextField idReservaBoton;
    public DatePicker ingresoFecha;
    public Text textoReservas;
    private verReservaServicio servicio = new verReservaServicio();
    private Reserva reserva;

    public void verReservasPorId (TextField idReservaBoton){
        String det=servicio.verReserva(idReservaBoton.getText());
        if(det!=null){
            textoReservas.setText(det);
        }else{
            textoReservas.setText("No hay reservas asociadas a ese ID");
        }
    }

    public void verReservasPorNombreCliente (TextField nombreClienteBoton){
        String det=servicio.verReservaCliente(idReservaBoton.getText());
        if(det!=null){
            textoReservas.setText(det);
        }else{
            textoReservas.setText("No hay reservas asociadas a ese Nombre");
        }
    }

    public void verReservasPorFecha (DatePicker ingresoFecha){
        String det=servicio.verReservaCliente(idReservaBoton.getText());
        if(det!=null){
            textoReservas.setText(det);
        }else{
            textoReservas.setText("No hay reservas asociadas a esa fecha");
        }
    }

    public void buscarReserva() {
        if (!idReservaBoton.getText().isEmpty()) {
            verReservasPorId(idReservaBoton);
        }
        else if (!nombreClienteBoton.getText().isEmpty()) {
            verReservasPorNombreCliente(nombreClienteBoton);
        }
        else if(!ingresoFecha.getValue().toString().isEmpty()) {
            verReservasPorFecha(ingresoFecha);
        }
    }

    // Asignar la acción al botón buscar
    public void initialize() {
        buscarBoton.setOnAction(e -> buscarReserva());
    }

}
