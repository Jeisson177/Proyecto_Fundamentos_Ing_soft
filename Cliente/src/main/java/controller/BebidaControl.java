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

    public Button botonHome;
    public Button botonReservar;
    public Button botonMenu;
    public Button botonImagenAgua;
    public Button botonImagenAguaGas;
    public Button botonImagenVino;
    public Button botonImagenGaseosa;
    public Button botonImagenChampagne;

    @FXML
    public void initialize() {
        // Cargar las imágenes
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Agua.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/AguaConGas.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Champagne.png")));
        Image img4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/Gaseosas.png")));
        Image img5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Bebidas1/vinos.png")));

        // Crear nuevos ImageView para cada botón (no reutilizar los existentes)
        ImageView imageViewAgua = new ImageView(img1);
        ImageView imageViewAguaGas = new ImageView(img2);
        ImageView imageViewChampagne = new ImageView(img3);
        ImageView imageViewGaseosa = new ImageView(img4);
        ImageView imageViewVino = new ImageView(img5);

        // Ajustar el tamaño de los ImageView si es necesario
        imageViewAgua.setFitHeight(100);
        imageViewAgua.setFitWidth(100);
        imageViewAguaGas.setFitHeight(100);
        imageViewAguaGas.setFitWidth(100);
        imageViewChampagne.setFitHeight(100);
        imageViewChampagne.setFitWidth(100);
        imageViewGaseosa.setFitHeight(100);
        imageViewGaseosa.setFitWidth(100);
        imageViewVino.setFitHeight(100);
        imageViewVino.setFitWidth(100);

        // Establecer las imágenes en los botones
        botonImagenAgua.setGraphic(imageViewAgua);
        botonImagenAguaGas.setGraphic(imageViewAguaGas);
        botonImagenChampagne.setGraphic(imageViewChampagne);
        botonImagenGaseosa.setGraphic(imageViewGaseosa);
        botonImagenVino.setGraphic(imageViewVino);
    }
}
