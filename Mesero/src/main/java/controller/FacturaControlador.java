// FacturaControlador.java
package controller;

import entities.ItemFactura;
import entities.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import services.Carrito;
import services.GestionarReserva;
import services.ReservaService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FacturaControlador {

    @FXML
    public TableView<ItemFactura> tablaFactura;
    @FXML
    private TableColumn<ItemFactura, Integer> colCantidad;
    @FXML
    private TableColumn<ItemFactura, String> colDescripcion;
    @FXML
    private TableColumn<ItemFactura, Double> colPrecio;
    @FXML
    private TableColumn<ItemFactura, Double> colTotal;

    @FXML
    public Text fechaFactura;
    @FXML
    public Text mesaFactura;
    @FXML
    public Text totalBase;
    @FXML
    public Text servicioAdicional;
    @FXML
    public Text totalConServicio;
    @FXML
    public Text horaFactura;

    private final Carrito carrito = Carrito.getInstance();
    private final GestionarReserva gestionarReserva = new GestionarReserva();
    private int idReservaActual;

    @FXML
    public void initialize() {
        colCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
        colDescripcion.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        colTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        cargarDatosFactura();
    }

    private void cargarDatosFactura() {
        List<ItemFactura> itemsFactura = carrito.obtenerPlatosEnCarrito().stream()
                .map(platoCarrito -> new ItemFactura(
                        platoCarrito.getCantidad(),
                        platoCarrito.getPlato().getNombre(),
                        platoCarrito.getPlato().getPrecio()))
                .collect(Collectors.toList());

        ObservableList<ItemFactura> itemsObservable = FXCollections.observableArrayList(itemsFactura);
        tablaFactura.setItems(itemsObservable);

        actualizarTotales(false);
    }

    public void actualizarTotales(boolean incluirServicio) {
        double totalBaseValue = carrito.calcularTotalCarrito();
        double servicioValue = incluirServicio ? totalBaseValue * 0.08 : 0;
        double totalConServicioValue = totalBaseValue + servicioValue;

        totalBase.setText(String.format("%.2f COP", totalBaseValue));
        servicioAdicional.setText(String.format("%.2f COP", servicioValue));
        totalConServicio.setText(String.format("%.2f COP", totalConServicioValue));
    }

    public void cargarDatosReserva() {
        Reserva reserva = ReservaService.getInstance().getReservaSeleccionada();
        if (reserva != null) {
            System.out.println("Reserva seleccionada encontrada: " + reserva.getIdReserva());
            fechaFactura.setText("Fecha: " + reserva.getFechaHora().toLocalDate());
            horaFactura.setText("Hora: " + reserva.getFechaHora().toLocalTime());
            mesaFactura.setText("Mesa: " + reserva.getIdMesa());
            idReservaActual = reserva.getIdReserva(); // Guardamos el ID para eliminar después
        } else {
            System.out.println("Error: No se encontró la reserva seleccionada en ReservaService.");
            mostrarAlerta("Error", "No se encontró la reserva seleccionada.");
        }
    }


    @FXML
    public void imprimirFactura() {
        System.out.println("ID de reserva al intentar imprimir factura: " + idReservaActual);

        StringBuilder facturaTexto = new StringBuilder();
        facturaTexto.append("Bella Ventura\n")
                .append(fechaFactura.getText()).append("\n")
                .append(mesaFactura.getText()).append("\n")
                .append(horaFactura.getText()).append("\n")
                .append("====================================\n")
                .append("CANT\tDESCRIPCIÓN\tPRECIO\tTOTAL\n");

        for (ItemFactura item : tablaFactura.getItems()) {
            facturaTexto.append(item.getCantidad()).append("\t")
                    .append(item.getNombre()).append("\t")
                    .append(String.format("%.2f", item.getPrecio())).append(" COP\t")
                    .append(String.format("%.2f", item.getTotal())).append(" COP\n");
        }

        facturaTexto.append("====================================\n")
                .append("BASE: ").append(totalBase.getText()).append("\n")
                .append("SERVICIO: ").append(servicioAdicional.getText()).append("\n")
                .append("TOTAL: ").append(totalConServicio.getText()).append("\n")
                .append("GRACIAS POR SU VISITA\n");

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MMdd_HH_mmss"));
        String filename = "facturas/Factura_" + timestamp + ".txt";
        File folder = new File("facturas");
        if (!folder.exists()) folder.mkdir();

        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.print(facturaTexto.toString());
            mostrarAlerta("Factura", "Factura guardada en: " + filename);

            // Verificación del ID de la reserva temporal para eliminación
            Integer idReservaTemporal = gestionarReserva.getIdReservaTemporal();
            System.out.println("ID de reserva temporal obtenido en imprimirFactura: " + idReservaTemporal); // Verificación del ID de reserva temporal
            if (idReservaTemporal != null && idReservaActual == idReservaTemporal) {
                gestionarReserva.eliminarReservaTemporal(); // Eliminar reserva temporal
                System.out.println("Reserva temporal eliminada con ID: " + idReservaTemporal); // Confirmación de eliminación
            } else {
                gestionarReserva.eliminarReserva(idReservaActual); // Eliminar reserva normal
                System.out.println("Reserva normal eliminada con ID: " + idReservaActual); // Confirmación de eliminación
            }
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error al guardar la factura.");
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
