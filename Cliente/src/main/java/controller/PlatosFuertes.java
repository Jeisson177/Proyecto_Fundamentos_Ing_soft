package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    public Button selecVolver;
    @FXML
    public Button selecHome;
    @FXML
    public Button selecReservar;
    @FXML
    public Button P4quesos;
    @FXML
    public Button Ppepperoni;
    @FXML
    public Button Pmargarita;
    @FXML
    public Button PasCarbonara;
    @FXML
    public Button PasFetu;
    @FXML
    public Button Pastrene;
    @FXML
    public Button PasBolonesa;
    @FXML
    public Button LasBolonesa;
    @FXML
    public Button LasMixta;
    @FXML
    public Button LasPollo;
    @FXML
    public Button risotoVegetariano;
    @FXML
    public Button risotoSalmon;
    @FXML
    public Button risotoHongos;
    @FXML
    public ImageView pizzaSelec;

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

    public void onClick(ActionEvent actionEvent) {
    }

    public void onClickHome(ActionEvent actionEvent) {
    }

    public void onClickMenu(ActionEvent actionEvent) {
    }

    public void onClickReservar(ActionEvent actionEvent) {
    }

    public void onClickQuesos(ActionEvent actionEvent) {
    }

    public void onClickPepperoni(ActionEvent actionEvent) {
    }

    public void onClickMargarita(ActionEvent actionEvent) {
    }

    public void onClickCarbonara(ActionEvent actionEvent) {
    }

    public void onClickFetu(ActionEvent actionEvent) {
    }

    public void onClickTrene(ActionEvent actionEvent) {
    }

    public void onClickBolonesa(ActionEvent actionEvent) {
    }

    public void onClickLasBolonesa(ActionEvent actionEvent) {
    }

    public void onClickLasMixta(ActionEvent actionEvent) {
    }

    public void onClickLasPollo(ActionEvent actionEvent) {
    }

    public void onClickVegetariano(ActionEvent actionEvent) {
    }

    public void onClickSalmon(ActionEvent actionEvent) {
    }

    public void onClickHongos(ActionEvent actionEvent) {
    }

}

   
