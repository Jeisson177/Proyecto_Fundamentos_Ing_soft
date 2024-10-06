package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Postrecontrol {

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

    @FXML
    public void initialize() {
        // Cargar la imagen al inicializar la vista
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/AFFOGATO_IMAGE.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/CANNOLINI_IMAGE.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/GELATO_IMAGE.png")));
        Image img4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/PANNACOTA_IMAGE.png")));
        Image img5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/TIRAMISU_IMAGE.png")));
        Image img6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/INSTA_IMAGE.png")));
        Image img7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/POSTRESLETRA_IMAGE.png")));

        affogatoimage.setImage(img1); // Establecer la imagen en el ImageView
        cannoliimage.setImage(img2);
        gelatoimage.setImage(img3);
        pannacotaimage.setImage(img4);
        tiramisuimage.setImage(img5);
        instaimage.setImage(img6);
        imagenletrapostres.setImage(img7);


    }

    @FXML
    public void onHelloButtonClick() {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Postremenu"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) volverMenu.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void clickPOSTRE(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Heladomenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Heladomenu"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) agregaraorden.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
