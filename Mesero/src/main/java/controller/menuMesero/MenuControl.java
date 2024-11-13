package controller.menuMesero;

import controller.ControladorCarrito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.Carrito;

import java.io.IOException;
import java.util.Objects;

public class MenuControl {

    @FXML
    private Button antipastiMenu;
    @FXML
    private Button botonbebidasImage;
    @FXML
    private Button botonMenu;
    @FXML
    private Button botonPlatosFuertes;
    @FXML
    private Button botonPostre;
    @FXML
    private Button botonReservar;
    @FXML
    private Button botonHome;
    @FXML
    private ImageView antipastiImage;
    @FXML
    private ImageView platoFuerteImage;
    @FXML
    private ImageView postresImage;
    @FXML
    private ImageView bebidasImage;

    private final RedireccionGeneral Ira = new RedireccionGeneral();
    private final Carrito carrito = Carrito.getInstance();

    @FXML
    public void initialize() {
        cargarImagenes();
    }

    private void cargarImagenes() {
        try {
            Image imgAntipasti = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_antipasti.png")));
            Image imgPlatoFuerte = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_platos_fuertes.png")));
            Image imgPostres = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_postres.png")));
            Image imgBebidas = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_bebidas.png")));

            antipastiImage.setImage(imgAntipasti);
            platoFuerteImage.setImage(imgPlatoFuerte);
            postresImage.setImage(imgPostres);
            bebidasImage.setImage(imgBebidas);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.err.println("Error al cargar una o más imágenes. Verifica las rutas.");
        }
    }

    @FXML
    private void abrirAntipasti() {
        Ira.IrAntipasti(antipastiMenu);
    }

    @FXML
    private void abrirBebidas() {
        try {
            Ira.IrBebidas(botonbebidasImage);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al abrir Bebidas", "Ocurrió un problema al intentar abrir la vista de bebidas: " + e.getMessage());
        }
    }

    @FXML
    private void IrPlatosFuertes() {
        Ira.IrPlatosFuertes(botonPlatosFuertes);
    }

    @FXML
    private void IrPostre() {
        Ira.IrPostre(botonPostre);
    }

    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(botonMenu);
    }

    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(botonHome);
    }

    public void IrReserva(ActionEvent actionEvent) {
        Ira.IrReserva(botonReservar);
    }

    public void revisarCarrito() {
        abrirControladorCarrito();
    }

    private void abrirControladorCarrito() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/carrito/CarritoCompras.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Carrito de Compras");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la pantalla del carrito.");
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
