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

public class Antipasti {

    @FXML
    public ImageView carpaccioResImage;
    public ImageView carpaccioSalmonImage;
    public ImageView focacciaGenoveseImage;
    public ImageView focacciaDolceImage;
    public ImageView focacciaVenetaImage;
    public ImageView focacciaPuglieseImage;
    public ImageView ensaladaCesarRomanaImage;
    public ImageView ensaldaPortobelloImage;
    public ImageView ensaladaVentrescaImage;
    public ImageView tablaQuesosImage;

    public Button menuBoton;
    public Button reservarBoton;
    public Button homeBoton;

    public  Button volverMenu;

    @FXML
    public void initialize() {
        // Cargar la imagen al inicializar la vista
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/carpaccio_res.png")));
        Image img2= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/carpaccio_salmon.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_genovese.png")));
        Image img4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_dolce.png")));
        Image img5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_veneta.png")));
        Image img6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_pugliese.png")));
        Image img7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_cesar_romana.png")));
        Image img8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_portobello.png")));
        Image img9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_ventresca.png")));
        Image img10 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/tabal_quesos.png")));

        carpaccioResImage.setImage(img1); // Establecer la imagen en el ImageView
        carpaccioSalmonImage.setImage(img2);
        focacciaGenoveseImage.setImage(img3);
        focacciaDolceImage.setImage(img4);
        focacciaVenetaImage.setImage(img5);
        focacciaPuglieseImage.setImage(img6);
        ensaladaCesarRomanaImage.setImage(img7);
        ensaldaPortobelloImage.setImage(img8);
        ensaladaVentrescaImage.setImage(img9);
        tablaQuesosImage.setImage(img10);
    }

    @FXML
    public void onHelloButtonClick() {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Menu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Antipasti"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) volverMenu.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuButtonClick(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Menu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) menuBoton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reservarButtonClick(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ReservaFecha.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reservar"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) reservarBoton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeButtonClick(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ReservaFecha.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reserva"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) homeBoton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

