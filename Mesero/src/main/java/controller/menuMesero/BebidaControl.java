package controller.menuMesero;

import controller.ControladorCarrito;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.AgregarPizzaService;
import services.Carrito;

import java.io.IOException;
import java.util.Objects;

public class BebidaControl {

    @FXML
    public Button botonHome;
    @FXML
    public Button botonReservar;
    @FXML
    public Button botonMenu;
    @FXML
    public Button botonImagenAgua;
    @FXML
    public Button botonImagenAguaGas;
    @FXML
    public Button botonImagenVino;
    @FXML
    public Button botonImagenGaseosa;
    @FXML
    public Button botonImagenChampagne;
    @FXML
    public Button botonRevisarCarrito;

    @FXML
    public Text textoPrecioAguaGas;
    @FXML
    public Text textoPrecioChampagne;
    @FXML
    public Text textoPrecioAgua;
    @FXML
    public Text dispoAgua;
    @FXML
    public Text dispoAguaGas;
    @FXML
    public Text dispoCham;

    private final AgregarPizzaService bebidaService = new AgregarPizzaService();
    private final Carrito carrito = Carrito.getInstance();
    private final RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        // Configurar imágenes en los botones
        setButtonImage(botonImagenAgua, "/Imagenes/Bebidas1/Agua.png");
        setButtonImage(botonImagenAguaGas, "/Imagenes/Bebidas1/AguaConGas.png");
        setButtonImage(botonImagenVino, "/Imagenes/Bebidas1/vinos.png");
        setButtonImage(botonImagenChampagne, "/Imagenes/Bebidas1/Champagne.png");
        setButtonImage(botonImagenGaseosa, "/Imagenes/Bebidas1/Gaseosas.png");

        // Cargar precios
        textoPrecioAgua.setText(bebidaService.getPrecios("Agua") + " COP");
        textoPrecioAguaGas.setText(bebidaService.getPrecios("Agua con gas") + " COP");
        textoPrecioChampagne.setText(bebidaService.getPrecios("Champagne") + " COP");

        // Actualizar disponibilidad
        actualizarDisponibilidad();
    }

    private void setButtonImage(Button button, String imagePath) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
    }

    private void actualizarDisponibilidad() {
        dispoAgua.setText(bebidaService.estaDisponible("Agua") ? "Disponible" : "No disponible");
        dispoAguaGas.setText(bebidaService.estaDisponible("Agua con gas") ? "Disponible" : "No disponible");
        dispoCham.setText(bebidaService.estaDisponible("Champagne") ? "Disponible" : "No disponible");

        // Colorear el texto de disponibilidad
        dispoAgua.setFill(bebidaService.estaDisponible("Agua") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
        dispoAguaGas.setFill(bebidaService.estaDisponible("Agua con gas") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
        dispoCham.setFill(bebidaService.estaDisponible("Champagne") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
    }

    @FXML
    public void agregarBebida(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String nombreBebida = null;

        if (clickedButton == botonImagenAgua) {
            nombreBebida = "Agua";
        } else if (clickedButton == botonImagenAguaGas) {
            nombreBebida = "Agua con gas";
        } else if (clickedButton == botonImagenChampagne) {
            nombreBebida = "Champagne";
        }

        if (nombreBebida == null || !bebidaService.estaDisponible(nombreBebida)) {
            mostrarAlerta("No disponible", "La bebida \"" + nombreBebida + "\" no está disponible.");
            return;
        }

        int precio = bebidaService.getPrecios(nombreBebida);
        if (precio <= 0) {
            mostrarAlerta("Error", "No se pudo obtener el precio de la bebida.");
            return;
        }

        Plato platoBebida = new Plato(0, nombreBebida, precio);
        boolean bebidaEncontrada = false;
        for (PlatoCarrito platoCarrito : carrito.obtenerPlatosEnCarrito()) {
            if (platoCarrito.getPlato().equals(platoBebida)) {
                platoCarrito.incrementarCantidad(1);
                bebidaEncontrada = true;
                break;
            }
        }

        if (!bebidaEncontrada) {
            carrito.agregarPlato(platoBebida);
        }

        mostrarAlerta("Bebida agregada", "La bebida \"" + nombreBebida + "\" ha sido agregada al carrito.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void revisarCarrito(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CarritoCompras.fxml"));
            Parent root = loader.load();

            ControladorCarrito controlador = loader.getController();
            controlador.setCarrito(Carrito.getInstance());

            Stage stage = new Stage();
            stage.setTitle("Carrito");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista del carrito.");
        }
    }

    @FXML
    public void irAPantallaVinos(ActionEvent event) {
        try {
            Parent vinosRoot = FXMLLoader.load(getClass().getResource("/vista/menu/Vinos.fxml"));
            Scene vinosScene = new Scene(vinosRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(vinosScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void irAPantallaGaseosas(ActionEvent event) {
        try {
            Parent gaseosaRoot = FXMLLoader.load(getClass().getResource("/vista/menu/gaseosas.fxml"));
            Scene gaseosaScene = new Scene(gaseosaRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gaseosaScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(botonHome);
    }

    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(botonMenu);
    }
}
