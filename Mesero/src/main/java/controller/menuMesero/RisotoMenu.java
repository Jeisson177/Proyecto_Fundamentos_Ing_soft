package controller.menuMesero;

import controller.ControladorCarrito;
import entities.Plato;
import entities.PlatoCarrito;
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

public class RisotoMenu {
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecReservar;
    @FXML
    public ImageView RisotoVeg;
    @FXML
    public ImageView RisotoSal;
    @FXML
    public ImageView RisotoHon;
    @FXML
    public Button risotoVegetariano;
    @FXML
    public Button risotoSalmon;
    @FXML
    public Button risotoHongos;
    @FXML
    public Button selecVolver;
    @FXML
    public Text precioRisotoVeg;
    @FXML
    public Text precioRisotoSal;
    @FXML
    public Text precioRisotoHon;
    @FXML
    public Text dispoVeg;
    @FXML
    public Text dispoSal;
    @FXML
    public Text dispoHon;

    private RedireccionGeneral Ira = new RedireccionGeneral();
    private PlatosServices platoService = new PlatosServices();
    private Carrito carrito = Carrito.getInstance(); // Usar la instancia singleton del carrito

    public void initialize() {
        Image vegetariano = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/vegetariano.png")));
        Image hongos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Hongos.png")));
        Image imgSalmon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Salmon.png")));

        RisotoVeg.setImage(vegetariano);
        RisotoHon.setImage(hongos);
        RisotoSal.setImage(imgSalmon);

        precioRisotoHon.setText(String.valueOf(platoService.obtenerPrecioPlato("Risotto de Hongos")) + " COP");
        precioRisotoSal.setText(String.valueOf(platoService.obtenerPrecioPlato("Risotto de Salmon")) + " COP");
        precioRisotoVeg.setText(String.valueOf(platoService.obtenerPrecioPlato("Risotto Vegetariano")) + " COP");

        actualizarDisponibilidad();
    }

    private void actualizarDisponibilidad() {
        actualizarTextoDisponibilidad(dispoVeg, "Risotto Vegetariano");
        actualizarTextoDisponibilidad(dispoSal, "Risotto de Salmon");
        actualizarTextoDisponibilidad(dispoHon, "Risotto de Hongos");
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
    public void onClickVegetariano(ActionEvent actionEvent) {
        agregarPlato("Risotto Vegetariano");
    }

    @FXML
    public void onClickSalmon(ActionEvent actionEvent) {
        agregarPlato("Risotto de Salmon");
    }

    @FXML
    public void onClickHongos(ActionEvent actionEvent) {
        agregarPlato("Risotto de Hongos");
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
                    platoCarrito.incrementarCantidad(-1); // Incrementa la cantidad si el plato ya está en el carrito
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
            controlador.setCarrito(Carrito.getInstance()); // Pasar la instancia del carrito

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
    public void onClickHome(ActionEvent actionEvent) {
        Ira.IrHome(selecHome);
    }
    @FXML
    public void onClickMenu(ActionEvent actionEvent) {
        Ira.IrMenu(selecMenu);
    }
    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        Ira.IrMenu(selecVolver);
    }



}
