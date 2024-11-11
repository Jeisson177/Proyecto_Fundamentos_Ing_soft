package controller.menuMesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.PlatosServices;

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

    private PlatosServices bebidaService = new PlatosServices();
    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        cargarImagenes();
        cargarPrecios();
    }

    private void cargarImagenes() {
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/quatro.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/cocacola.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/fanta.png")));

        ImageView imageView4 = new ImageView(img1);
        ImageView imageViewCoca = new ImageView(img2);
        ImageView imageViewFanta = new ImageView(img3);

        imageView4.setFitHeight(100);
        imageView4.setFitWidth(100);
        imageViewCoca.setFitHeight(100);
        imageViewCoca.setFitWidth(100);
        imageViewFanta.setFitHeight(100);
        imageViewFanta.setFitWidth(100);

        botonImagenQuatro.setGraphic(imageView4);
        botonImagenCoca.setGraphic(imageViewCoca);
        botonImagenFanta.setGraphic(imageViewFanta);
    }

    private void cargarPrecios() {
        textoPrecio4.setText(bebidaService.obtenerPrecioPlato("Quatro") + " COP");
        textoPrecioFanta.setText(bebidaService.obtenerPrecioPlato("Fanta") + " COP");
        textoPrecioCoca.setText(bebidaService.obtenerPrecioPlato("Coca Cola") + " COP");
    }

    public void irAPantallaBebidas(ActionEvent event) {
        Ira.IrBebidas(botonRegresar);
    }

    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(botonHome);
    }

    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(botonMenu);
    }

    public void IrReserva(ActionEvent actionEvent) {
        Ira.IrReserva(botonReservar);
    }
}
