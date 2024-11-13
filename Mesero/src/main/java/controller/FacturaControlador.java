package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import services.Carrito;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FacturaControlador {

    @FXML
    private TableView<ItemFactura> tablaFactura;
    @FXML
    private TableColumn<ItemFactura, Integer> colCantidad;
    @FXML
    private TableColumn<ItemFactura, String> colDescripcion;
    @FXML
    private TableColumn<ItemFactura, Double> colPrecio;
    @FXML
    private TableColumn<ItemFactura, Double> colTotal;

    @FXML
    private Text fechaFactura;
    @FXML
    private Text mesaFactura;
    @FXML
    private Text totalBase;
    @FXML
    private Text servicioAdicional;
    @FXML
    private Text totalConServicio;

    private final Carrito carrito = Carrito.getInstance();

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

        actualizarTotales(false); // Inicialmente sin servicio
    }

    public void actualizarTotales(boolean incluirServicio) {
        double totalBaseValue = carrito.calcularTotalCarrito();
        double servicioValue = incluirServicio ? totalBaseValue * 0.08 : 0;
        double totalConServicioValue = totalBaseValue + servicioValue;

        totalBase.setText(String.format("%.2f COP", totalBaseValue));
        servicioAdicional.setText(String.format("%.2f COP", servicioValue));
        totalConServicio.setText(String.format("%.2f COP", totalConServicioValue));
    }

    @FXML
    private void imprimirFactura() {
        StringBuilder facturaTexto = new StringBuilder();
        facturaTexto.append("Bella Ventura\n")
                .append(fechaFactura.getText()).append("\n")
                .append(mesaFactura.getText()).append("\n")
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

        try (PrintWriter writer = new PrintWriter("Factura.txt")) {
            writer.print(facturaTexto.toString());
            mostrarAlerta("Factura", "Factura guardada como Factura.txt");
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo guardar la factura.");
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
