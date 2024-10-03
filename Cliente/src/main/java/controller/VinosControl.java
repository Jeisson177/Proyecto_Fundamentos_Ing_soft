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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.bebidasRepositorio;
import java.io.IOException;
import java.util.Objects;

public class VinosControl {

    @FXML
    public ImageView rosaImage;
    public ImageView tintoImage;
    public ImageView balncoImage;


    public Button botonHome;
    public Button botonReservar;
    public Button botonMenu;
    public Button botonRegresar;
    public Button botonImagenRosado;
    public Button botonImagenTinto;
    public Button botonImagenBlanco;
    public Text textoPrecioBlanco;
    public Text textoPrecioTinto;
    public Text textoPrecioRosado;

    private bebidasRepositorio bebida=new bebidasRepositorio();

    @FXML
    public void initialize() {
        // Cargar las imágenes
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Vinos/rosa.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Vinos/tinto.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Vinos/white.png")));


        // Crear nuevos ImageView para cada botón (no reutilizar los existentes)
        ImageView imageViewRosa = new ImageView(img1);
        ImageView imageViewTinto = new ImageView(img2);
        ImageView imageViewBlanco= new ImageView(img3);

        // Ajustar el tamaño de los ImageView si es necesario
        imageViewRosa.setFitHeight(100);
        imageViewRosa.setFitWidth(100);
        imageViewTinto.setFitHeight(100);
        imageViewTinto.setFitWidth(100);
        imageViewBlanco.setFitHeight(100);
        imageViewBlanco.setFitWidth(100);

        // Establecer las imágenes en los botones
        botonImagenRosado.setGraphic(imageViewRosa);
        botonImagenTinto.setGraphic(imageViewTinto);
        botonImagenBlanco.setGraphic(imageViewBlanco);

        textoPrecioRosado.setText(String.valueOf(bebida.getPrecios("Vino rosado")));
        textoPrecioTinto.setText(String.valueOf(bebida.getPrecios("Vino tinto")));
        textoPrecioBlanco.setText(String.valueOf(bebida.getPrecios("Vino blanco")));
    }

    public void irAPantallaBebidas(ActionEvent event) {
        try {
            // Cargar la nueva pantalla (vinos.fxml)
            Parent bebidasRoot = FXMLLoader.load(getClass().getResource("/vista/Bebidas.fxml"));
            Scene bebidasScene = new Scene(bebidasRoot);

            // Obtener el Stage actual usando el botón como referencia
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Establecer la nueva escena
            stage.setScene(bebidasScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error si ocurre
        }
    }

    public void irAPantallaMenu(ActionEvent event) {
        try {
            // Cargar la nueva pantalla (vinos.fxml)
            Parent menuRoot = FXMLLoader.load(getClass().getResource("/vista/menu.fxml"));
            Scene menuScene = new Scene(menuRoot);

            // Obtener el Stage actual usando el botón como referencia
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Establecer la nueva escena
            stage.setScene(menuScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error si ocurre
        }
    }

    public void irAPantallaHome(ActionEvent event) {
        try {
            // Cargar la nueva pantalla (vinos.fxml)
            Parent homeRoot = FXMLLoader.load(getClass().getResource("/vista/home.fxml"));
            Scene homeScene = new Scene(homeRoot);

            // Obtener el Stage actual usando el botón como referencia
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Establecer la nueva escena
            stage.setScene(homeScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error si ocurre
        }
    }

}

