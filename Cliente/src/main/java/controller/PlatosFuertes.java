package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class PlatosFuertes {

    @FXML
    public Button selecPizza;
    @FXML
    public Button selecPasta;
    @FXML
    public Button selecLasagna;
    @FXML
    public Button selecRisoto;
    @FXML
    public Button selecAtras;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecHome;
    @FXML
    public Button selecReservar;
    @FXML
    public ImageView Pizza;
    @FXML
    public ImageView Lasagna;
    @FXML
    public ImageView Pasta;
    @FXML
    public ImageView Risoto;

    public void initialize(){
        Image pizza = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/pizza.png")));
        Image pasta= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Pasta.png")));
        Image lasagna= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/L1.png")));
        Image risoto= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/risoto.png")));

        Pizza.setImage(pizza);
        Pasta.setImage(pasta);
        Lasagna.setImage(lasagna);
        Risoto.setImage(risoto);


    }

    public void onClickPizza(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PizzaMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Pizza"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) selecPizza.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickPasta(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PastaMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Pasta"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) selecPasta.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickLa(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/LasanaMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Lasaña"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) selecLasagna.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickRisoto(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RisotoMenu.fxml"));
            Parent root = loader.load();

            // Crea una nueva escena
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Risoto"); // Título de la nueva ventana
            stage.show();

            //Opcionalmente, cierra la ventana actual
            ((Stage) selecRisoto.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onClick(ActionEvent actionEvent) {
    }

    public void onClickHome(ActionEvent actionEvent) {
    }

    public void onClickMenu(ActionEvent actionEvent) {
    }

    public void onClickReservar(ActionEvent actionEvent) {
    }

}

   
