package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.util.Objects;

public class BebidaControl {
    @FXML
    public ImageView aguaImage;
    public ImageView aguaGasImage;
    public ImageView champagneImage;
    public ImageView gaseosaImage;
    public ImageView vinosImage;


    //public Button menuBoton;
    //public Button reservarBoton;
   // public Button homeBoton;
    //public  Button volverMenu;

    @FXML
    public void initialize() {
        // Cargar la imagen al inicializar la vista
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Agua.png")));
        Image img2= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Agua con gas.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Champagne.png")));
        Image img4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/Gaseosas.png")));
        Image img5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/vinos.png")));


        aguaImage.setImage(img1); // Establecer la imagen en el ImageView
        aguaGasImage.setImage(img2);
        champagneImage.setImage(img3);
        gaseosaImage.setImage(img4);
        vinosImage.setImage(img5);
    }
}
