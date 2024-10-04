package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

    public void initialize(){
        Image vegetariano = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/vegetariano.png")));
        Image hongos= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Hongos.png")));
        Image imgSalmon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Salmon.png")));

        RisotoVeg.setImage(vegetariano);
        RisotoHon.setImage(hongos);
        RisotoSal.setImage(imgSalmon);
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
    public void onClickVolver(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PlatosFuertes.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Platos Fuertes"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) selecVolver.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
