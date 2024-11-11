package controller.menuMesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.PlatosServices;

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
    public Button agregaraorden;
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

    private PlatosServices postreService = new PlatosServices();
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
        precioaffogato.setText(postreService.obtenerPrecioPlato("Affogato") + " COP");
        preciotiramisu.setText(postreService.obtenerPrecioPlato("Tiramisu") + " COP");
        preciopannacota.setText(postreService.obtenerPrecioPlato("Panna Cotta") + " COP");
        preciocannolini.setText(postreService.obtenerPrecioPlato("Cannoli") + " COP");
    }

    @FXML
    public void clickPOSTRE(ActionEvent actionEvent) {
        try {
            // Cargar la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Heladomenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Heladomenu");
            stage.show();

            // Cierra la ventana actual
            ((Stage) agregaraorden.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
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
