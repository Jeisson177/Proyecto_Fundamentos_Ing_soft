package controller.menuMesero;

import controller.Plato;
import controller.PlatoCarrito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Carrito;
import services.PlatosServices;

import java.io.IOException;
import java.util.Objects;

public class BebidaControl {

    @FXML
    private ImageView aguaImage;
    @FXML
    private ImageView aguaGasImage;
    @FXML
    private ImageView champagneImage;
    @FXML
    private ImageView gaseosaImage;
    @FXML
    private ImageView vinosImage;

    @FXML
    private Button botonHome;
    @FXML
    private Button botonReservar;
    @FXML
    private Button botonMenu;
    @FXML
    private Button botonImagenAgua;
    @FXML
    private Button botonImagenAguaGas;
    @FXML
    private Button botonImagenChampagne;
    @FXML
    private Button botonImagenVino;
    @FXML
    private Button botonImagenGaseosa;
    @FXML
    private Button botonRevisarCarrito;
    @FXML
    private Text textoPrecioAgua;
    @FXML
    private Text textoPrecioAguaGas;
    @FXML
    private Text textoPrecioChampagne;
    @FXML
    private Text dispoAgua;
    @FXML
    private Text dispoAguaGas;
    @FXML
    private Text dispoCham;

    private final PlatosServices bebidaService = new PlatosServices();
    private final Carrito carrito = Carrito.getInstance();
    private final RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        try {
            cargarImagenes();
            cargarPrecios();
            cargarDisponibilidad();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error en la inicialización", "Ocurrió un problema al cargar los datos en la vista: " + e.getMessage());
        }

        // Asignar userData para identificar los botones de bebidas
        botonImagenAgua.setUserData("Agua");
        botonImagenAguaGas.setUserData("Agua con gas");
        botonImagenChampagne.setUserData("Champagne");

        // Asignar eventos onAction a los botones de bebidas
        botonImagenAgua.setOnAction(this::agregarPlato);
        botonImagenAguaGas.setOnAction(this::agregarPlato);
        botonImagenChampagne.setOnAction(this::agregarPlato);
    }

    private void cargarImagenes() {
        if (aguaImage != null) {
            aguaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Agua.png"))));
        }
        if (aguaGasImage != null) {
            aguaGasImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/AguaConGas.png"))));
        }
        if (champagneImage != null) {
            champagneImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Champagne.png"))));
        }
        if (gaseosaImage != null) {
            gaseosaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Gaseosas.png"))));
        }
        if (vinosImage != null) {
            vinosImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/vinos.png"))));
        }
    }

    private void cargarPrecios() {
        textoPrecioAgua.setText(bebidaService.obtenerPrecioPlato("Agua") + " COP");
        textoPrecioAguaGas.setText(bebidaService.obtenerPrecioPlato("Agua con gas") + " COP");
        textoPrecioChampagne.setText(bebidaService.obtenerPrecioPlato("Champagne") + " COP");
    }

    private void cargarDisponibilidad() {
        actualizarTextoDisponibilidad(dispoAgua, "Agua");
        actualizarTextoDisponibilidad(dispoAguaGas, "Agua con gas");
        actualizarTextoDisponibilidad(dispoCham, "Champagne");
    }

    private void actualizarTextoDisponibilidad(Text textElement, String nombrePlato) {
        if (bebidaService.estaDisponible(nombrePlato)) {
            textElement.setText("Disponible");
            textElement.setFill(Color.GREEN);
        } else {
            textElement.setText("No disponible");
            textElement.setFill(Color.RED);
        }
    }

    @FXML
    public void agregarPlato(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String nombrePlato = (String) clickedButton.getUserData();

        if (!bebidaService.estaDisponible(nombrePlato)) {
            mostrarAlerta("No disponible", "La bebida \"" + nombrePlato + "\" no está disponible para servir.");
            return;
        }

        Plato plato = bebidaService.obtenerPlatoPorNombre(nombrePlato);
        if (plato != null) {
            boolean platoEncontrado = false;
            for (PlatoCarrito platoCarrito : carrito.obtenerPlatosEnCarrito()) {
                if (platoCarrito.getPlato().equals(plato)) {
                    platoCarrito.incrementarCantidad();
                    platoEncontrado = true;
                    break;
                }
            }

            if (!platoEncontrado) {
                carrito.agregarPlato(plato);
            }

            mostrarAlerta("Bebida agregada", "La bebida \"" + nombrePlato + "\" ha sido agregada al carrito.");
        } else {
            mostrarAlerta("Error", "No se pudo agregar la bebida al carrito.");
        }
    }

    @FXML
    public void revisarCarrito(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CarritoCompras.fxml"));
            Parent root = loader.load();
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

    public void irAPantallaVinos(ActionEvent event) {
        cargarPantalla("/vista/vinos.fxml", event);
    }

    public void irAPantallaGaseosas(ActionEvent event) {
        cargarPantalla("/vista/gaseosas.fxml", event);
    }

    private void cargarPantalla(String rutaFXML, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rutaFXML));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista.");
        }
    }

    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(botonHome);
    }

    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(botonMenu);
    }

    public void IrReserva(ActionEvent actionEvent) {
        Ira.IrReserva(botonReservar);
    }
}
