package controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.AgregarPizzaService;
import services.RedireccionGeneral;

import java.util.Objects;

public class AntipastiControl {

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

    public Label carpaccioResPrecio;
    public Label carpaccioSalmonPrecio;
    public Label focacciaGenovesePrecio;
    public Label focacciaDolcePrecio;
    public Label focacciaVenetaPrecio;
    public Label focacciaPugliesePrecio;
    public Label ensaladaCesarRomanaPrecio;
    public Label ensaldaPortobelloPrecio;
    public Label ensaladaVentrescaPrecio;
    public Label tablaQuesosPrecio;

    private RedireccionGeneral Ira=new RedireccionGeneral();
    private AgregarPizzaService rep=new AgregarPizzaService();

    @FXML
    public void initialize() {

        // Cargar la imagen al inicializar la vista
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/carpaccio_res.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/carpaccio_salmon.png")));
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

        cargarPreciosEntrada();

    }
    @FXML
    private void cargarPreciosEntrada() {
            // Obtener los precios desde el repositorio y asignarlos a los Labels correspondientes
            carpaccioResPrecio.setText(rep.getPrecios("Carpaccio de Res") + " COP");
            carpaccioSalmonPrecio.setText(rep.getPrecios("Carpaccio de Salmon") + " COP");
            focacciaGenovesePrecio.setText(rep.getPrecios("Focaccia Genovese") + " COP");
            focacciaDolcePrecio.setText(rep.getPrecios("Focaccia Dolce") + " COP");
            focacciaVenetaPrecio.setText(rep.getPrecios("Focaccia Veneta") + " COP");
            focacciaPugliesePrecio.setText(rep.getPrecios("Focaccia Pugliese") + " COP");
            ensaladaCesarRomanaPrecio.setText(rep.getPrecios("Ensalada Cesar Romana") + " COP");
            ensaldaPortobelloPrecio.setText(rep.getPrecios("Ensalada de Portobello") + " COP");
            ensaladaVentrescaPrecio.setText(rep.getPrecios("Ensalada de Ventresca") + " COP");
            tablaQuesosPrecio.setText(rep.getPrecios("Tabla de Quesos") + " COP");

    }

    @FXML

    public void menuButtonClick(ActionEvent actionEvent) { Ira.IrMenu(menuBoton); }

    public void reservarButtonClick(ActionEvent actionEvent) {
       Ira.IrReserva(reservarBoton);
    }

    public void homeButtonClick(ActionEvent actionEvent) {
        Ira.IrHome(homeBoton);
    }

}

