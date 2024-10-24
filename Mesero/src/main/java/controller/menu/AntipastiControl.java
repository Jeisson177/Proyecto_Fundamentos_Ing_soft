package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import repository.menu.bebidasRepositorio;
import services.RedireccionGeneral;
import repository.menu.antipastiRepositorio;

import java.util.Objects;

import java.sql.*;

public class AntipastiControl {

    private antipastiRepositorio rep=new antipastiRepositorio();
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
    public Text dispoCarRes;
    public Text dispoCarSal;
    public Text dispoFocGen;
    public Text dispoFocDolce;
    public Text dispoFocVe;
    public Text dispoFocPu;
    public Text dispoCesar;
    public Text dispoPorto;
    public Text dispoVen;
    public Text dispoTabQuesos;


    private RedireccionGeneral Ira=new RedireccionGeneral();
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

        carpaccioResPrecio.setText(String.valueOf(rep.getPrecios("Carpaccio de Res")));
        carpaccioSalmonPrecio.setText(String.valueOf(rep.getPrecios("Carpaccio de Salmon")));
        focacciaGenovesePrecio.setText(String.valueOf(rep.getPrecios("Focaccia Genovese")));
        focacciaVenetaPrecio.setText(String.valueOf(rep.getPrecios("Focaccia Veneta")));
        focacciaDolcePrecio.setText(String.valueOf(rep.getPrecios("Focaccia Dolce")));
        focacciaPugliesePrecio.setText(String.valueOf(rep.getPrecios("Focaccia Pugliese")));
        ensaladaCesarRomanaPrecio.setText(String.valueOf(rep.getPrecios("Ensalada Cesar Romana")));
        ensaldaPortobelloPrecio.setText(String.valueOf(rep.getPrecios("Ensalada de Portobello")));
        ensaladaVentrescaPrecio.setText(String.valueOf(rep.getPrecios("Ensalada de Ventresca")));
        tablaQuesosPrecio.setText(String.valueOf(rep.getPrecios("Tabla de quesos")));


        dispoCarRes.setTextContent(rep.getDispo("Carpaccio de Res"));
        dispoCarSal.setTextContent(rep.getDispo("Carpaccio de Salmon"));
        dispoFocGen.setTextContent(rep.getDispo("Focaccia Genovese"));
        dispoFocDolce.setTextContent(rep.getDispo("Focaccia Dolce"));
        dispoFocVe.setTextContent(rep.getDispo("Focaccia Veneta"));
        dispoFocPu.setTextContent(rep.getDispo("Focaccia Pugliese"));
        dispoCesar.setTextContent(rep.getDispo("Ensalada Cesar Romana"));
        dispoPorto.setTextContent(rep.getDispo("Ensalada de Portobello"));
        dispoVen.setTextContent(rep.getDispo("Ensalada de Ventresca"));
        dispoTabQuesos.setTextContent(rep.getDispo("Tabla de quesos"));

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

