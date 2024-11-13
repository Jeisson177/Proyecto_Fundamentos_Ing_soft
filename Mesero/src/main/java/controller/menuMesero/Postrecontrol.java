package controller.menuMesero;

import controller.ControladorCarrito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controller.Plato;
import services.Carrito;
import services.PlatosServices;
import java.io.IOException;
import java.util.Objects;

public class Postrecontrol {

    @FXML
    public Button botonHome;
    @FXML
    public Button botonMenu;
    @FXML
    public Button botonReservar;
    @FXML
    private ImageView gelatoimage;
    @FXML
    private ImageView tiramisuimage;
    @FXML
    private ImageView pannacotaimage;
    @FXML
    private ImageView affogatoimage;
    @FXML
    private ImageView cannoliimage;
    @FXML
    private ImageView imagenletrapostres;
    @FXML
    private ImageView instaimage;

    @FXML
    public Button volverMenu;

    @FXML
    public Label preciotiramisu;
    @FXML
    public Label preciopannacota;
    @FXML
    public Label precioaffogato;
    @FXML
    public Label preciocannolini;

    // Elementos de disponibilidad
    @FXML
    private Text dispoTiramisu;
    @FXML
    private Text dispoPannaCota;
    @FXML
    private Text dispoAffogato;
    @FXML
    private Text dispoCannolini;

    private PlatosServices platoService = new PlatosServices();
    private Carrito carrito = Carrito.getInstance();
    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        // Cargar las imágenes al inicializar la vista
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/AFFOGATO_IMAGE.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/CANNOLINI_IMAGE.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/GELATO_IMAGE.png")));
        Image img4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/PANNACOTA_IMAGE.png")));
        Image img5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/TIRAMISU_IMAGE.png")));
        Image img6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/INSTA_IMAGE.png")));
        Image img7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/POSTRESLETRA_IMAGE.png")));

        affogatoimage.setImage(img1);
        cannoliimage.setImage(img2);
        gelatoimage.setImage(img3);
        pannacotaimage.setImage(img4);
        tiramisuimage.setImage(img5);
        instaimage.setImage(img6);
        imagenletrapostres.setImage(img7);

        // Cargar precios usando PlatosServices
        precioaffogato.setText(platoService.obtenerPrecioPlato("Affogato") + " COP");
        preciotiramisu.setText(platoService.obtenerPrecioPlato("Tiramisu") + " COP");
        preciopannacota.setText(platoService.obtenerPrecioPlato("Panna Cotta") + " COP");
        preciocannolini.setText(platoService.obtenerPrecioPlato("Cannoli") + " COP");

        // Configurar disponibilidad de cada postre
        setDisponibilidad(dispoTiramisu, "Tiramisu");
        setDisponibilidad(dispoPannaCota, "Panna Cotta");
        setDisponibilidad(dispoAffogato, "Affogato");
        setDisponibilidad(dispoCannolini, "Cannoli");
    }

    private void setDisponibilidad(Text disponibilidadLabel, String nombrePlato) {
        if (platoService.estaDisponible(nombrePlato)) {
            disponibilidadLabel.setText("Disponible");
            disponibilidadLabel.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            disponibilidadLabel.setText("No disponible");
            disponibilidadLabel.setFill(javafx.scene.paint.Color.RED);
        }
    }

    @FXML
    public void agregarPlato(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String nombrePlato = (String) clickedButton.getUserData();

        // Verificar disponibilidad antes de agregar al carrito
        if (!platoService.estaDisponible(nombrePlato)) {
            mostrarAlerta("No disponible", "El plato \"" + nombrePlato + "\" no está disponible para servir.");
            return;
        }

        Plato plato = platoService.obtenerPlatoPorNombre(nombrePlato);
        if (plato != null) {
            carrito.agregarPlato(plato);
            mostrarAlerta("Plato agregado", "El plato \"" + nombrePlato + "\" ha sido agregado al carrito.");
        } else {
            mostrarAlerta("Error", "No se pudo agregar el plato al carrito.");
        }
    }

    @FXML
    public void clickPOSTRE(ActionEvent actionEvent) {
        try {
            // Cargar la nueva ventana de submenú para Gelato
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/HeladoMenu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("HeladoMenu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista del submenú de helado.");
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
