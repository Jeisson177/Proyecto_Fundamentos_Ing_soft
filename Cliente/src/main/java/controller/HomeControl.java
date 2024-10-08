package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import services.RedireccionGeneral;
public class HomeControl {

    @FXML
    public Button btnReservar;
    @FXML
    public Button btnMenu;
    @FXML
    public Button btnhome;
    @FXML
    public Button btnSaber;
    @FXML
    public Button btnReserva;
    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(btnhome);
    }
    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(btnMenu);
    }
    @FXML
    public void IrReservar(ActionEvent actionEvent) {
        Ira.IrReserva(btnReserva);
    }
    @FXML
    public void IrSaber(ActionEvent actionEvent) {

    }
}
