package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PastaMenu {
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecReservar;
    @FXML
    public Button PasCarbonara;
    @FXML
    public Button PasFetu;
    @FXML
    public Button Pastrene;
    @FXML
    public Button PasBolonesa;
    @FXML
    public Button selecVolver;
    @FXML
    public ImageView pastaCarbonara;
    @FXML
    public ImageView pastaFetu;
    @FXML
    public ImageView pastaTrene;
    @FXML
    public ImageView pastaBolo;

    public void initialize(){
        Image carbonara = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Carbonara.png")));
        Image fetu= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Fetu.png")));
        Image trene= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/trene.png")));
        Image bolo= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/spa.png")));

        pastaCarbonara.setImage(carbonara);
        pastaFetu.setImage(fetu);
        pastaTrene.setImage(trene);
        pastaBolo.setImage(bolo);
    }

    @FXML
    public void onClickHome(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickMenu(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickReservar(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickCarbonara(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickFetu(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickTrene(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickBolonesa(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
    }
}
