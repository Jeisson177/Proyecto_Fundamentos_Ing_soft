package controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import repository.menu.RedireccionGeneral;
import services.AgregarPizzaService;

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

    private AgregarPizzaService bebida=new AgregarPizzaService();

    private RedireccionGeneral Ira=new RedireccionGeneral();
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
        Ira.IrBebidas(botonRegresar);
    }

    public void irAPantallaMenu(ActionEvent event) {
        Ira.IrMenu(botonMenu);
    }

    public void irAPantallaHome(ActionEvent event) {
        Ira.IrHome(botonHome);
    }

}

