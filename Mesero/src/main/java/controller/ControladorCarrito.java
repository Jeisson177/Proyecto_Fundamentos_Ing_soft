package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import services.Carrito;
import controller.PlatoCarrito;

public class ControladorCarrito {

    @FXML
    private TableView<PlatoCarrito> tableViewCarrito;
    @FXML
    private TableColumn<PlatoCarrito, String> colNombre;
    @FXML
    private TableColumn<PlatoCarrito, Double> colPrecioUnidad;
    @FXML
    private TableColumn<PlatoCarrito, Integer> colCantidad;
    @FXML
    private TableColumn<PlatoCarrito, Double> colPrecioTotal;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnTotal;
    @FXML
    private Button btnCrearFactura;
    @FXML
    private Text textTotal;

    private Carrito carrito;

    public ControladorCarrito() {
        this.carrito = new Carrito();
    }

    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlato().getNombre()));
        colPrecioUnidad.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPlato().getPrecio()).asObject());
        colCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCantidad()).asObject());
        colPrecioTotal.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCostoTotal()).asObject());
        cargarPlatosEnCarrito();
        btnEliminar.setOnAction(event -> eliminarPlatoDelCarrito());
        btnTotal.setOnAction(event -> calcularTotal());
        btnCrearFactura.setOnAction(event -> crearFactura());
    }

    private void cargarPlatosEnCarrito() {
        tableViewCarrito.getItems().clear();
        tableViewCarrito.getItems().addAll(carrito.obtenerPlatosEnCarrito());
    }

    private void eliminarPlatoDelCarrito() {
        PlatoCarrito platoSeleccionado = tableViewCarrito.getSelectionModel().getSelectedItem();
        if (platoSeleccionado != null) {
            carrito.obtenerPlatosEnCarrito().remove(platoSeleccionado);
            cargarPlatosEnCarrito();
        } else {
            showAlert("Selecciona un plato para eliminar.");
        }
    }

    private void calcularTotal() {
        double total = carrito.calcularTotalCarrito();
        textTotal.setText("Total: $" + total);
    }

    private void crearFactura() {
        double total = carrito.calcularTotalCarrito();
        if (total > 0) {
            showAlert("Factura creada con éxito. Total: $" + total);
            carrito.vaciarCarrito();
            cargarPlatosEnCarrito();
        } else {
            showAlert("El carrito está vacío, no se puede crear una factura.");
        }
    }

    private void showAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
