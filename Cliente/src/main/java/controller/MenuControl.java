package controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.Objects;

public class MenuControl {
    @FXML
    public Button antipastiMenu;
    public ImageView antipastiImage;
    public ImageView platoFuerteImage;
    public ImageView postresImage;
    public ImageView bebidasImage;

    @FXML
    public void initialize() {
        // Cargar la imagen al inicializar la vista
        Image imgAntipasti = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_antipasti.png")));
        Image imgPlatoFuerte = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_platos_fuertes.png")));
        Image imgPostres = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_postres.png")));
        Image imgBebidas = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/menuPrincipal/port_bebidas.png")));


        antipastiImage.setImage(imgAntipasti); // Establecer la imagen en el ImageView
        platoFuerteImage.setImage(imgPlatoFuerte);
        postresImage.setImage(imgPostres);
        bebidasImage.setImage(imgBebidas);
    }

    @FXML
    private void onHelloButtonClick() {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Antipasti.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Antipasti"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) antipastiMenu.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
