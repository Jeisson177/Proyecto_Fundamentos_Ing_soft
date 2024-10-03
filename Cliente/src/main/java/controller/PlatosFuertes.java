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
    @FXML
    public ImageView Pizza;
    @FXML
    public ImageView Lasagna;
    @FXML
    public ImageView Pasta;
    @FXML
    public ImageView Risoto;
    @FXML
    public ImageView pmargarita;
    @FXML
    public ImageView p4quesos;
    @FXML
    public ImageView pperoni;


    public void initialize(){
        Image pizza = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/pizza.png")));
        Image pasta= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Pasta.png")));
        Image lasagna= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/L1.png")));
        Image risoto= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/risoto.png")));
        Image margarita= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Margarita.png")));
        Image quesos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/4quesos.png")));
        Image peperoni = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/peper.png")));

        Pizza.setImage(pizza);
        Pasta.setImage(pasta);
        Lasagna.setImage(lasagna);
        Risoto.setImage(risoto);
        pmargarita.setImage(margarita);
        p4quesos.setImage(quesos);
        pperoni.setImage(peperoni);

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
    @FXML
    public void seleccionarpizza(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource(); // Obtener el botón que se presionó
        String buttonText = clickedButton.getText(); // Obtener el texto del botón

        // Llamar a una función con el botón presionado como parámetro
        processPizzaSelection(buttonText);
    }

    private void processPizzaSelection(String pizzaType) {

        if(pizzaType=="Pizza Pepperoni"){

        }
    }
}

   
