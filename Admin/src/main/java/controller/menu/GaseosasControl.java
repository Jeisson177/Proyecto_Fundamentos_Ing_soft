package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import repository.menu.bebidasRepositorio;
import services.RedireccionGeneral;

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
    public Text textoPrecio4;
    public Text textoPrecioFanta;
    public Text textoPrecioCoca;

    private bebidasRepositorio bebida=new bebidasRepositorio();
    private RedireccionGeneral Ira=new RedireccionGeneral();
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

        textoPrecio4.setText(String.valueOf(bebida.getPrecios("Quatro")));
        textoPrecioFanta.setText(String.valueOf(bebida.getPrecios("Fanta")));
        textoPrecioCoca.setText(String.valueOf(bebida.getPrecios("Coca cola")));
    }

    public void irAPantallaBebidas(ActionEvent event) {
        Ira.IrBebidas(botonRegresar);
    }

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(botonHome);
    }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(botonMenu);
    }
    public void IrReserva(ActionEvent actionEvent) {Ira.IrReserva(botonReservar);
    }

}

