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
import repository.RisotoRepositorio;
import services.RedireccionGeneral;

import java.util.Objects;

public class RisotoMenu {
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecReservar;
    @FXML
    public ImageView RisotoVeg;
    @FXML
    public ImageView RisotoSal;
    @FXML
    public ImageView RisotoHon;
    @FXML
    public Button risotoVegetariano;
    @FXML
    public Button risotoSalmon;
    @FXML
    public Button risotoHongos;
    @FXML
    public Button selecVolver;
    @FXML
    public Text precioRisotoVeg;
    @FXML
    public Text precioRisotoSal;
    @FXML
    public Text precioRisotoHon;

    private RedireccionGeneral Ira=new RedireccionGeneral();
    private RisotoRepositorio risoto=new RisotoRepositorio();

    public void initialize(){
        Image vegetariano = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/vegetariano.png")));
        Image hongos= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Hongos.png")));
        Image imgSalmon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Salmon.png")));

        RisotoVeg.setImage(vegetariano);
        RisotoHon.setImage(hongos);
        RisotoSal.setImage(imgSalmon);

        precioRisotoHon.setText(String.valueOf(risoto.getPrecios("Risotto de Hongos"))+" COP");
        precioRisotoSal.setText(String.valueOf(risoto.getPrecios("Risotto de Salmon"))+" COP");
        precioRisotoVeg.setText(String.valueOf(risoto.getPrecios("Risotto Vegetariano"))+" COP");
    }

    @FXML
    public void onClickHome(ActionEvent actionEvent) {
        Ira.IrHome(selecHome);
    }

    @FXML
    public void onClickMenu(ActionEvent actionEvent) {
        Ira.IrHome(selecHome);
    }

    @FXML
    public void onClickReservar(ActionEvent actionEvent) {
        Ira.IrReserva(selecReservar);
    }

    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        Ira.IrPlatosFuertes(selecVolver);
    }

    @FXML
    public void onClickVegetariano(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickSalmon(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickHongos(ActionEvent actionEvent) {
    }
}
