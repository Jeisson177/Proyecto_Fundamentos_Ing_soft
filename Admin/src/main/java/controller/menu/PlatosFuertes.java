package controller.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    private RedireccionGeneral Ira=new RedireccionGeneral();

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
        Ira.IrPizzaMenu(selecPizza);
    }

    public void onClickPasta(ActionEvent actionEvent) {
        Ira.IrPasta(selecPasta);
    }

    public void onClickLa(ActionEvent actionEvent) {
        Ira.IrLasanaMenu(selecLasagna);
    }

    public void onClickRisoto(ActionEvent actionEvent) {
        Ira.IrRisotoMenu(selecRisoto);
    }


    public void onClickAtras(ActionEvent actionEvent) { Ira.IrMenu(selecAtras);
    }

    public void onClickHome(ActionEvent actionEvent) {
        Ira.IrHome(selecHome);
    }

    public void onClickMenu(ActionEvent actionEvent) {
        Ira.IrMenu(selecMenu);
    }

    public void onClickReserva(ActionEvent actionEvent) {
        Ira.IrReserva(selecReservar);
    }

}

   
