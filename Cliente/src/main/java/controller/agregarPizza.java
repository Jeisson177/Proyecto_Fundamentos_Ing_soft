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
import repository.agregarPizzaRepositorio;

import java.util.Objects;

public class agregarPizza {

    @FXML
    public ImageView pizzaSelec;
    @FXML
    public Button selecVolver;
    @FXML
    public Button pizza1;
    @FXML
    public Button pizza2;
    @FXML
    public Button agregarP;
    @FXML
    public Text precioPersonal;
    @FXML
    public Text precioMediana;
    @FXML
    public Text precioFamiliar;

    private String tipoPizza;

    private agregarPizzaRepositorio pizza=new agregarPizzaRepositorio();

    public void setTipoPizza(String tipoPizza) {
        this.tipoPizza = tipoPizza;
        initializePizzaDetails(); // Llama a este metodo para inicializar la vista
    }

    // Metodo para inicializar los componentes según el tipo de pizza
    public void initializePizzaDetails() {
        if (tipoPizza.equals("4quesos")) {
            Image quesos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Quesos.png")));
            pizzaSelec.setImage(quesos);
            pizza1.setText("Pepperoni");
            pizza2.setText("Margarita");
            precioPersonal.setText(String.valueOf(pizza.getPrecios("Pizza Cuatro Quesos Personal"))+"COP");
            precioMediana.setText(String.valueOf(pizza.getPrecios("Pizza Cuatro Quesos Mediana"))+"COP");
            precioFamiliar.setText(String.valueOf(pizza.getPrecios("Pizza Cuatro Quesos Familiar"))+"COP");
        } else if (tipoPizza.equals("pepperoni")) {
            Image quesos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Pep.png")));
            pizzaSelec.setImage(quesos);
            pizza1.setText("4 quesos");
            pizza2.setText("Margarita");
            precioPersonal.setText(String.valueOf(pizza.getPrecios("Pizza Pepperoni Personal"))+"COP");
            precioMediana.setText(String.valueOf(pizza.getPrecios("Pizza Pepperoni Mediana"))+"COP");
            precioFamiliar.setText(String.valueOf(pizza.getPrecios("Pizza Pepperoni Familiar"))+"COP");
        } else if (tipoPizza.equals("margarita")) {
            Image quesos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Marga.png")));
            pizzaSelec.setImage(quesos);
            pizza1.setText("Pepperoni");
            pizza2.setText("4 quesos");
            precioPersonal.setText(String.valueOf(pizza.getPrecios("Pizza Margarita Personal"))+"COP");
            precioMediana.setText(String.valueOf(pizza.getPrecios("Pizza Margarita Mediana"))+"COP");
            precioFamiliar.setText(String.valueOf(pizza.getPrecios("Pizza Margarita Familiar"))+"COP");
        }
    }

    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        try {
            // Carga la nueva ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PizzaMenu.fxml"));
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
    public void onClickAgregarP(ActionEvent actionEvent) {
    }
}
