package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PizzaMenu {
    @FXML
    public Button selecHome;
    @FXML
    public Button selecReservar;
    @FXML
    public Button selecMenu;
    @FXML
    public ImageView p4quesos;
    @FXML
    public ImageView pperoni;
    @FXML
    public ImageView pmargarita;
    @FXML
    public Button P4quesos;
    @FXML
    public Button Ppepperoni;
    @FXML
    public Button Pmargarita;
    @FXML
    public Button selecVolver;

    public void initialize(){
        Image margarita= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Margarita.png")));
        Image quesos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/4quesos.png")));
        Image peperoni = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/peper.png")));

        pmargarita.setImage(margarita);
        pperoni.setImage(peperoni);
        p4quesos.setImage(quesos);
    }

    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
    }

    @FXML
    public void seleccionarpizza(ActionEvent actionEvent) {
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
}
