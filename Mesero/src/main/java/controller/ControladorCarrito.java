
package controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Carrito;
import services.ReservaService;

import java.io.IOException;
import java.util.Optional;

public class ControladorCarrito {

    @FXML
    public TableView<PlatoCarrito> tableViewCarrito;
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
    public Text textTotal;

    private Carrito carrito;

    public ControladorCarrito() {
        this.carrito = Carrito.getInstance();
    }

    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlato().getNombre()));
        colPrecioUnidad.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPlato().getPrecio()).asObject());
        colCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCantidad()).asObject());
        colPrecioTotal.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCostoTotal()).asObject());

        tableViewCarrito.setItems(carrito.obtenerPlatosEnCarrito());
        actualizarTotal();

        btnEliminar.setOnAction(event -> eliminarPlatoDelCarrito());
        btnTotal.setOnAction(event -> actualizarTotal());
        btnCrearFactura.setOnAction(event -> crearFactura());
    }

    private void crearFactura() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Añadir Servicio");
        alert.setHeaderText("¿Desea añadir el servicio?");
        alert.setContentText("Seleccione una opción:");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");
        ButtonType buttonCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonYes, buttonNo, buttonCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonCancel) {
            return;
        }

        boolean incluirServicio = result.isPresent() && result.get() == buttonYes;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Factura.fxml"));
            Parent root = loader.load();

            FacturaControlador facturaControlador = loader.getController();
            facturaControlador.actualizarTotales(incluirServicio);

            // Cargar datos de la reserva seleccionada en la factura
            facturaControlador.cargarDatosReserva();  // Asegura que se carga la reserva seleccionada

            Stage stage = new Stage();
            stage.setTitle("Factura");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la pantalla de factura.");
        }
    }

    private void eliminarPlatoDelCarrito() {
        PlatoCarrito platoSeleccionado = tableViewCarrito.getSelectionModel().getSelectedItem();
        if (platoSeleccionado != null) {
            int nuevaCantidad = platoSeleccionado.getCantidad() - 1;
            if (nuevaCantidad > 0) {
                platoSeleccionado.setCantidad(nuevaCantidad);
                tableViewCarrito.refresh();
            } else {
                carrito.eliminarPlato(platoSeleccionado.getPlato());
            }
            actualizarTotal();
        } else {
            mostrarAlerta("Información", "Selecciona un plato para eliminar.");
        }
    }

    private void actualizarTotal() {
        double total = carrito.calcularTotalCarrito();
        textTotal.setText("Total: $" + total);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
        tableViewCarrito.setItems(carrito.obtenerPlatosEnCarrito());
        actualizarTotal();
    }
}
