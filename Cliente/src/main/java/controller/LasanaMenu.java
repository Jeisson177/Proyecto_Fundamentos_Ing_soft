package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.AgregarPizzaService;
import services.RedireccionGeneral;

import java.util.Objects;

public class LasanaMenu {
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecReservar;
    @FXML
    public ImageView LasanaBolo;
    @FXML
    public ImageView LasanaMixta;
    @FXML
    public ImageView LasanaPollo;
    @FXML
    public Button LasBolonesa;
    @FXML
    public Button LasMixta;
    @FXML
    public Button LasPollo;
    @FXML
    public Button selecVolver;
    @FXML
    public Text precioLasanaBolo;
    @FXML
    public Text precioLasanaMix;
    @FXML
    public Text precioLasanaPollo;

    private RedireccionGeneral Ira=new RedireccionGeneral();
    private AgregarPizzaService lasana=new AgregarPizzaService();

    public void initialize(){
        Image Bolonesa = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/BOLOÑESA.png")));
        Image Mixta= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Mixta.png")));
        Image Pollo= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/pollo.png")));

        LasanaBolo.setImage(Bolonesa);
        LasanaMixta.setImage(Mixta);
        LasanaPollo.setImage(Pollo);

        precioLasanaMix.setText(String.valueOf(lasana.getPrecios("Lasagna Mixta"))+" COP");
        precioLasanaBolo.setText(String.valueOf(lasana.getPrecios("Lasagna Bolognese"))+" COP");
        precioLasanaPollo.setText(String.valueOf(lasana.getPrecios("Lasagna de Pollo"))+" COP");

    }

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(selecHome);
    }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(selecMenu);
    }
    public void IrReserva(ActionEvent actionEvent) {Ira.IrReserva(selecReservar);
    }

    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        Ira.IrPlatosFuertes(selecVolver);
    }

    @FXML
    public void onClickLasBolonesa(ActionEvent actionEvent) {

    }

    @FXML
    public void onClickLasMixta(ActionEvent actionEvent) {

    }

    @FXML
    public void onClickLasPollo(ActionEvent actionEvent) {
    }
}
