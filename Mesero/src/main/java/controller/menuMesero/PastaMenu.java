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

public class PastaMenu {
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    public Button PasCarbonara;
    @FXML
    public Button PasFetu;
    @FXML
    public Button Pastrene;
    @FXML
    public Button PasBolonesa;
    @FXML
    public Button selecVolver;
    @FXML
    public Button revisarCarritoBoton; // Botón para revisar el carrito
    @FXML
    public ImageView pastaCarbonara;
    @FXML
    public ImageView pastaFetu;
    @FXML
    public ImageView pastaTrene;
    @FXML
    public ImageView pastaBolo;
    @FXML
    public Text precioPastaCar;
    @FXML
    public Text precioPastaFetu;
    @FXML
    public Text precioPastaTrene;
    @FXML
    public Text precioPastaSpa;
    @FXML
    public Text dispoCarbonara;
    @FXML
    public Text dispoPuttanesca;
    @FXML
    public Text dispoPesto;
    @FXML
    public Text dispoBolonesa;

    private RedireccionGeneral Ira = new RedireccionGeneral();
    private PlatosServices platoService = new PlatosServices();
    private Carrito carrito = Carrito.getInstance(); // Instancia singleton del carrito

    public void initialize() {
        Image carbonara = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Carbonara.png")));
        Image fetu = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Fetu.png")));
        Image trene = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/trene.png")));
        Image bolo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/spa.png")));

        pastaCarbonara.setImage(carbonara);
        pastaFetu.setImage(fetu);
        pastaTrene.setImage(trene);
        pastaBolo.setImage(bolo);

        precioPastaCar.setText(String.valueOf(platoService.obtenerPrecioPlato("Pasta alla Carbonara")) + " COP");
        precioPastaFetu.setText(String.valueOf(platoService.obtenerPrecioPlato("Fettuccine alla Puttanesca")) + " COP");
        precioPastaTrene.setText(String.valueOf(platoService.obtenerPrecioPlato("Trenette al Pesto")) + " COP");
        precioPastaSpa.setText(String.valueOf(platoService.obtenerPrecioPlato("Spaghetti alla Bolognese")) + " COP");

        actualizarDisponibilidad();
    }

    private void actualizarDisponibilidad() {
        actualizarTextoDisponibilidad(dispoCarbonara, "Pasta alla Carbonara");
        actualizarTextoDisponibilidad(dispoPuttanesca, "Fettuccine alla Puttanesca");
        actualizarTextoDisponibilidad(dispoPesto, "Trenette al Pesto");
        actualizarTextoDisponibilidad(dispoBolonesa, "Spaghetti alla Bolognese");
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
    public void onClickCarbonara(ActionEvent actionEvent) {
        agregarPlato("Pasta alla Carbonara");
    }

    @FXML
    public void onClickFetu(ActionEvent actionEvent) {
        agregarPlato("Fettuccine alla Puttanesca");
    }

    @FXML
    public void onClickTrene(ActionEvent actionEvent) {
        agregarPlato("Trenette al Pesto");
    }

    @FXML
    public void onClickBolonesa(ActionEvent actionEvent) {
        agregarPlato("Spaghetti alla Bolognese");
    }

    private void agregarPlato(String nombrePlato) {
        if (!platoService.estaDisponible(nombrePlato)) {
            mostrarAlerta("No disponible", "El plato \"" + nombrePlato + "\" no está disponible para servir.");
            return;
        }

        Plato plato = platoService.obtenerPlatoPorNombre(nombrePlato);
        if (plato != null) {
            boolean platoEncontrado = false;
            for (PlatoCarrito platoCarrito : carrito.obtenerPlatosEnCarrito()) {
                if (platoCarrito.getPlato().equals(plato)) {
                    platoCarrito.incrementarCantidad(-1);
                    platoEncontrado = true;
                    break;
                }
            }

            if (!platoEncontrado) {
                carrito.agregarPlato(plato);
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
            controlador.setCarrito(carrito);

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
