package controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PortadaControl {

    @FXML
    public Button btnContinuar;
    private RedireccionGeneral Ira = new RedireccionGeneral();

    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(btnContinuar);

    }
}
