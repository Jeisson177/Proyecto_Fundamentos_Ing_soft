package controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.menu.HeladosRepositorio;
import services.RedireccionGeneral;


import java.util.Objects;

public class Heladoscontrol {

    @FXML
    public Label precioframbuesa;
    @FXML
    public Button botonHome;
    @FXML
    public Button botonMenu;
    @FXML
    public Button botonReservar;
    @FXML
    private ImageView heladoavellana;
    @FXML
    private ImageView heladolimon;
    @FXML
    private ImageView heladoframbuesa;
    @FXML
    private ImageView heladofresa;
    @FXML
    private ImageView heladochocolatepicante;
    @FXML
    private ImageView heladogrande;
    @FXML
    private ImageView instaimage;
    @FXML
    private ImageView heladosletra;

    @FXML
    public Button agregaraorden;
    @FXML
    public Button volverPostres;
    @FXML
    public  Button volverMenu;

    @FXML
    public Label precioavellana;
    @FXML
    public Label preciolimon;
    @FXML
    public Label preciofresa;
    @FXML
    public Label preciochocolate;

    private HeladosRepositorio helado =new HeladosRepositorio();

    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        // Cargar la imagen al inicializar la vista
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/AVELLANA_IMAGE.png")));
        Image img2= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/LIMON_IMAGE.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/FRAMBUESA_IMAGE.png")));
        Image img4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/FRESA_IMAGE.png")));
        Image img5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/CHOCOLATE_IMAGE.png")));
        Image img6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/HELADOS_IMAGE.png")));
        Image img7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/INSTA_IMAGE.png")));
        Image img8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/HELADOSLETRA_IMAGE.png")));

        heladoavellana.setImage(img1); // Establecer la imagen en el ImageView
        heladolimon.setImage(img2);
        heladoframbuesa.setImage(img3);
        heladofresa.setImage(img4);
        heladochocolatepicante.setImage(img5);
        heladogrande.setImage(img6);
        instaimage.setImage(img7);
        heladosletra.setImage(img8);

        precioavellana.setText(String.valueOf(helado.getPrecios("Gelato Bacio (Avellana)"))+" COP");
        precioframbuesa.setText(String.valueOf(helado.getPrecios("Gelato Lampone (Frambuesa)"))+" COP");
        preciochocolate.setText(String.valueOf(helado.getPrecios("Gelato Cioccolato con Peperoncino"))+" COP");
        preciofresa.setText(String.valueOf(helado.getPrecios("Gelato Fragola (Fresa)"))+" COP");
        preciolimon.setText(String.valueOf(helado.getPrecios("Gelato Limone (Limon)"))+" COP");

    }


    public void clickHELADO(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/PostreMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("PostreMenu"); // Título de la nueva ventana
            stage.show();

            // Opcionalmente, cierra la ventana actual
            ((Stage) volverMenu.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
    }

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(botonHome);
    }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(botonMenu);
    }
    public void IrReserva(ActionEvent actionEvent) {Ira.IrReserva(botonReservar);
    }
}