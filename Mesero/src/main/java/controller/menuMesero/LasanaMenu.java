package controller.menuMesero;

import controller.ControladorCarrito;
import controller.Plato;
import controller.PlatoCarrito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Carrito;
import services.PlatosServices;

import java.io.IOException;
import java.util.Objects;

public class LasanaMenu {
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecReservar;
    @FXML
    public ImageView LasanaBolo;
    @FXML
    public ImageView LasanaMixta;
    @FXML
    public ImageView LasanaPollo;
    @FXML
    public Button LasBolonesa;
    @FXML
    public Button LasMixta;
    @FXML
    public Button LasPollo;
    @FXML
    public Button selecVolver;
    @FXML
    public Button revisarCarritoBoton; // Botón para revisar el carrito
    @FXML
    public Text precioLasanaBolo;
    @FXML
    public Text precioLasanaMix;
    @FXML
    public Text precioLasanaPollo;
    @FXML
    public Text dispoBolo;
    @FXML
    public Text dispoMixto;
    @FXML
    public Text dispoPollo;

    private RedireccionGeneral Ira = new RedireccionGeneral();
    private PlatosServices platoService = new PlatosServices();
    private Carrito carrito = Carrito.getInstance();

    public void initialize() {
        Image Bolonesa = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/BOLOÑESA.png")));
        Image Mixta = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Mixta.png")));
        Image Pollo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/pollo.png")));

        LasanaBolo.setImage(Bolonesa);
        LasanaMixta.setImage(Mixta);
        LasanaPollo.setImage(Pollo);

        precioLasanaMix.setText(String.valueOf(platoService.obtenerPrecioPlato("Lasagna Mixta")) + " COP");
        precioLasanaBolo.setText(String.valueOf(platoService.obtenerPrecioPlato("Lasagna Bolognese")) + " COP");
        precioLasanaPollo.setText(String.valueOf(platoService.obtenerPrecioPlato("Lasagna de Pollo")) + " COP");

        actualizarDisponibilidad();
    }

    private void actualizarDisponibilidad() {
        actualizarTextoDisponibilidad(dispoBolo, "Lasagna Bolognese");
        actualizarTextoDisponibilidad(dispoMixto, "Lasagna Mixta");
        actualizarTextoDisponibilidad(dispoPollo, "Lasagna de Pollo");
    }

    private void actualizarTextoDisponibilidad(Text textElement, String nombrePlato) {
        if (platoService.estaDisponible(nombrePlato)) {
            textElement.setText("Disponible");
            textElement.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            textElement.setText("No disponible");
            textElement.setFill(javafx.scene.paint.Color.RED);
        }
    }

    @FXML
    public void onClickLasBolonesa(ActionEvent actionEvent) {
        agregarPlato("Lasagna Bolognese");
    }

    @FXML
    public void onClickLasMixta(ActionEvent actionEvent) {
        agregarPlato("Lasagna Mixta");
    }

    @FXML
    public void onClickLasPollo(ActionEvent actionEvent) {
        agregarPlato("Lasagna de Pollo");
    }

    private void agregarPlato(String nombrePlato) {
        // Verificar la disponibilidad antes de agregar al carrito
        if (!platoService.estaDisponible(nombrePlato)) {
            mostrarAlerta("No disponible", "El plato \"" + nombrePlato + "\" no está disponible para servir.");
            return;
        }

        Plato plato = platoService.obtenerPlatoPorNombre(nombrePlato);
        if (plato != null) {
            boolean platoEncontrado = false;
            for (PlatoCarrito platoCarrito : carrito.obtenerPlatosEnCarrito()) {
                if (platoCarrito.getPlato().equals(plato)) {
                    platoCarrito.incrementarCantidad(); // Incrementa la cantidad si el plato ya está en el carrito
                    platoEncontrado = true;
                    break;
                }
            }

            if (!platoEncontrado) {
                carrito.agregarPlato(plato); // Agregar el plato si no se encuentra en el carrito
            }

            mostrarAlerta("Plato agregado", "El plato \"" + nombrePlato + "\" ha sido agregado al carrito.");
        } else {
            mostrarAlerta("Error", "No se pudo agregar el plato al carrito.");
        }
    }

    @FXML
    public void revisarCarrito(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CarritoCompras.fxml"));
            Parent root = loader.load();

            ControladorCarrito controlador = loader.getController();
            controlador.setCarrito(carrito); // Pasar la instancia del carrito al controlador del carrito

            Stage stage = new Stage();
            stage.setTitle("Carrito");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista del carrito.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(selecMenu);
    }
    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        Ira.IrPlatosFuertes(selecVolver);
    }


}
