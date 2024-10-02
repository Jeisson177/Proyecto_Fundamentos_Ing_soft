package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class MesaControl {

    @FXML
    public ImageView fondoiz;
    @FXML
    public ImageView fondodr;
    @FXML
    public Button btnMenu;
    public Button btnReservar;
    public Button btnhome;

    @FXML
    public void initialize(){

        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/mesa/fondomesa.png")));
        Image img2= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/mesa/fondomesa.png")));
        
        fondodr.setImage(img1);
        fondoiz.setImage(img2);

    }

    @FXML
    public void Irhome(ActionEvent actionEvent) {
    }
    @FXML
    public void irReserva(ActionEvent actionEvent) {
    }
    @FXML
    public void IrMenu(ActionEvent actionEvent) {
    }
}
