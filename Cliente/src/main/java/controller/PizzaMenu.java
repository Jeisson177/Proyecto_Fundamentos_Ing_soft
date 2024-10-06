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
    public void seleccionarpizza(ActionEvent actionEvent) {
        Button sourceButton = (Button) actionEvent.getSource(); // Detecta qué botón fue presionado
        // Variable para almacenar el tipo de pizza
        String tipoPizza = "";

        // Comparar el texto del botón o usar el ID del botón para determinar cuál fue seleccionado
        if (sourceButton.equals(P4quesos)) {
            tipoPizza  = "4quesos";
            try {
                // Carga la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/agregarPizza.fxml"));
                Parent root = loader.load();

                // Obtén el controlador de la nueva vista
                agregarPizza AgregarPizza = loader.getController();
                // Envía el tipo de pizza al nuevo controlador
                AgregarPizza.setTipoPizza(tipoPizza);

                // Crea una nueva escena
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Agregar Pizza 4 quesos"); // Título de la nueva ventana
                stage.show();

                //Opcionalmente, cierra la ventana actual
                ((Stage) P4quesos.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (sourceButton.equals(Ppepperoni)) {
            tipoPizza  = "pepperoni";
            try {
                // Carga la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/agregarPizza.fxml"));
                Parent root = loader.load();

                // Obtén el controlador de la nueva vista
                agregarPizza AgregarPizza = loader.getController();
                // Envía el tipo de pizza al nuevo controlador
                AgregarPizza.setTipoPizza(tipoPizza);

                // Crea una nueva escena
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Agregar Pizza Pepperoni"); // Título de la nueva ventana
                stage.show();

                //Opcionalmente, cierra la ventana actual
                ((Stage) Ppepperoni.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (sourceButton.equals(Pmargarita)) {
            tipoPizza  = "margarita";
            try {
                // Carga la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/agregarPizza.fxml"));
                Parent root = loader.load();

                // Obtén el controlador de la nueva vista
                agregarPizza AgregarPizza = loader.getController();
                // Envía el tipo de pizza al nuevo controlador
                AgregarPizza.setTipoPizza(tipoPizza);

                // Crea una nueva escena
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Agregar Pizza Margarita"); // Título de la nueva ventana
                stage.show();

                //Opcionalmente, cierra la ventana actual
                ((Stage) Pmargarita.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
