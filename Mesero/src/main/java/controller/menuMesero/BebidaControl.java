package controller.menuMesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.menu.RedireccionGeneral;
import services.AgregarPizzaService;

import java.io.IOException;
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
    public Text textoPrecioAguaGas;
    public Text textoPrecioChampagne;
    public Text textoPrecioAgua;

    private AgregarPizzaService bebida=new AgregarPizzaService();
    private RedireccionGeneral Ira=new RedireccionGeneral();
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

        textoPrecioAgua.setText(String.valueOf(bebida.getPrecios("Agua")));
        textoPrecioAguaGas.setText(String.valueOf(bebida.getPrecios("Agua con gas")));
        textoPrecioChampagne.setText(String.valueOf(bebida.getPrecios("Champagne")));

    }

    public void irAPantallaVinos(ActionEvent event) {
        try {
            // Cargar la nueva pantalla (vinos.fxml)
            Parent vinosRoot = FXMLLoader.load(getClass().getResource("/vista/Vinos.fxml"));
            Scene vinosScene = new Scene(vinosRoot);

            // Obtener el Stage actual usando el botón como referencia
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Establecer la nueva escena
            stage.setScene(vinosScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error si ocurre
        }
    }

    public void irAPantallaGaseosas(ActionEvent event) {
        try {
            // Cargar la nueva pantalla (vinos.fxml)
            Parent gaseosaRoot = FXMLLoader.load(getClass().getResource("/vista/gaseosas.fxml"));
            Scene gaseosaScene = new Scene(gaseosaRoot);

            // Obtener el Stage actual usando el botón como referencia
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Establecer la nueva escena
            stage.setScene(gaseosaScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error si ocurre
        }
    }

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(botonHome);
    }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(botonMenu);
    }
    public void IrReserva(ActionEvent actionEvent) {Ira.IrReserva(botonReservar);
    }
}

