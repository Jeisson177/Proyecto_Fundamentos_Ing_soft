package controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import repository.menu.agregarPizzaRepositorio;
import services.RedireccionGeneral;

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
    @FXML
    public Button botonHome;
    @FXML
    public Button botonMenu;
    @FXML
    public Button botonReservar;
    public Text dispoPersonal;
    public Text dispoMediana;
    public Text dispoFamiliar;


    private String tipoPizza;

    private RedireccionGeneral Ira=new RedireccionGeneral();

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

            if (pizza.getDispo("Pizza Cuatro Quesos Personal")==true){
                dispoPersonal.setText("Disponible");
            }else{
                dispoPersonal.setText("No disponible");
            }

            if (pizza.getDispo("Pizza Cuatro Quesos Mediana")==true){
                dispoMediana.setText("Disponible");
            }else{
                dispoMediana.setText("No disponible");
            }

            if (pizza.getDispo("Pizza Cuatro Quesos Familiar")==true){
                dispoFamiliar.setText("Disponible");
            }else{
                dispoFamiliar.setText("No disponible");
            }

        } else if (tipoPizza.equals("pepperoni")) {
            Image quesos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Pep.png")));
            pizzaSelec.setImage(quesos);
            pizza1.setText("4 quesos");
            pizza2.setText("Margarita");
            precioPersonal.setText(String.valueOf(pizza.getPrecios("Pizza Pepperoni Personal"))+"COP");
            precioMediana.setText(String.valueOf(pizza.getPrecios("Pizza Pepperoni Mediana"))+"COP");
            precioFamiliar.setText(String.valueOf(pizza.getPrecios("Pizza Pepperoni Familiar"))+"COP");

            if (pizza.getDispo("Pizza Pepperoni Personal")==true){
                dispoPersonal.setText("Disponible");
            }else{
                dispoPersonal.setText("No disponible");
            }

            if (pizza.getDispo("Pizza Pepperoni Mediana")==true){
                dispoMediana.setText("Disponible");
            }else{
                dispoMediana.setText("No disponible");
            }

            if (pizza.getDispo("Pizza Pepperoni Familiar")==true){
                dispoFamiliar.setText("Disponible");
            }else{
                dispoFamiliar.setText("No disponible");
            }

        } else if (tipoPizza.equals("margarita")) {
            Image quesos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Marga.png")));
            pizzaSelec.setImage(quesos);
            pizza1.setText("Pepperoni");
            pizza2.setText("4 quesos");
            precioPersonal.setText(String.valueOf(pizza.getPrecios("Pizza Margarita Personal"))+"COP");
            precioMediana.setText(String.valueOf(pizza.getPrecios("Pizza Margarita Mediana"))+"COP");
            precioFamiliar.setText(String.valueOf(pizza.getPrecios("Pizza Margarita Familiar"))+"COP");

            if (pizza.getDispo("Pizza Margarita Personal")==true){
                dispoPersonal.setText("Disponible");
            }else{
                dispoPersonal.setText("No disponible");
            }

            if (pizza.getDispo("Pizza Margarita Mediana")==true){
                dispoMediana.setText("Disponible");
            }else{
                dispoMediana.setText("No disponible");
            }

            if (pizza.getDispo("Pizza Margarita Familiar")==true){
                dispoFamiliar.setText("Disponible");
            }else{
                dispoFamiliar.setText("No disponible");
            }

        }
    }

    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        Ira.IrPizzaMenu(selecVolver);
    }

    @FXML
    public void onClickAgregarP(ActionEvent actionEvent) {
    }

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(botonHome);
    }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(botonMenu);
    }
    public void IrReserva(ActionEvent actionEvent) {Ira.IrReserva(botonReservar);
    }
}
