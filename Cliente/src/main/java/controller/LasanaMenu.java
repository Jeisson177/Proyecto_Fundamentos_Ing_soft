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
    public Text precioLasañaBolo;
    @FXML
    public Text precioLasanaMix;
    @FXML
    public Text precioLasanaPollo;

    public void initialize(){
        Image Bolonesa = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/BOLOÑESA.png")));
        Image Mixta= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Mixta.png")));
        Image Pollo= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/pollo.png")));

        LasanaBolo.setImage(Bolonesa);
        LasanaMixta.setImage(Mixta);
        LasanaPollo.setImage(Pollo);

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
    public void onClickLasBolonesa(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickLasMixta(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickLasPollo(ActionEvent actionEvent) {
    }
}
