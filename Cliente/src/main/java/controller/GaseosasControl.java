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

public class GaseosasControl {

    @FXML
    public ImageView quatroImage;
    public ImageView cocaImage;
    public ImageView fantaImage;


    public Button botonHome;
    public Button botonReservar;
    public Button botonMenu;
    public Button botonRegresar;
    public Button botonImagenQuatro;
    public Button botonImagenCoca;
    public Button botonImagenFanta;

    @FXML
    public void initialize() {
        // Cargar las imágenes
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/quatro.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/cocacola.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/fanta.png")));


        // Crear nuevos ImageView para cada botón (no reutilizar los existentes)
        ImageView imageView4 = new ImageView(img1);
        ImageView imageViewCoca = new ImageView(img2);
        ImageView imageViewFanta= new ImageView(img3);

        // Ajustar el tamaño de los ImageView si es necesario
        imageView4.setFitHeight(100);
        imageView4.setFitWidth(100);
        imageViewCoca.setFitHeight(100);
        imageViewCoca.setFitWidth(100);
        imageViewFanta.setFitHeight(100);
        imageViewFanta.setFitWidth(100);

        // Establecer las imágenes en los botones
        botonImagenQuatro.setGraphic(imageView4);
        botonImagenCoca.setGraphic(imageViewCoca);
        botonImagenFanta.setGraphic(imageViewFanta);
    }
}

