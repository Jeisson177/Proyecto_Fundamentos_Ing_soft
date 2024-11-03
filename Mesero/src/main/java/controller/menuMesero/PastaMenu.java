package controller.menuMesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import repository.menu.RedireccionGeneral;
import services.AgregarPizzaService;

import java.util.Objects;

public class PastaMenu {
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecReservar;
    @FXML
    public Button PasCarbonara;
    @FXML
    public Button PasFetu;
    @FXML
    public Button Pastrene;
    @FXML
    public Button PasBolonesa;
    @FXML
    public Button selecVolver;
    @FXML
    public ImageView pastaCarbonara;
    @FXML
    public ImageView pastaFetu;
    @FXML
    public ImageView pastaTrene;
    @FXML
    public ImageView pastaBolo;
    @FXML
    public Text precioPastaCar;
    @FXML
    public Text precioPastaFetu;
    @FXML
    public Text precioPastaTrene;
    @FXML
    public Text precioPastaSpa;

    private RedireccionGeneral Ira=new RedireccionGeneral();
    private AgregarPizzaService pasta=new AgregarPizzaService();

    public void initialize(){
        Image carbonara = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Carbonara.png")));
        Image fetu= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Fetu.png")));
        Image trene= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/trene.png")));
        Image bolo= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/spa.png")));

        pastaCarbonara.setImage(carbonara);
        pastaFetu.setImage(fetu);
        pastaTrene.setImage(trene);
        pastaBolo.setImage(bolo);

        precioPastaFetu.setText(String.valueOf(pasta.getPrecios("Fettuccine alla Puttanesca"))+" COP");
        precioPastaSpa.setText(String.valueOf(pasta.getPrecios("Pasta alla Carbonara"))+" COP");
        precioPastaTrene.setText(String.valueOf(pasta.getPrecios("Trenette al Pesto"))+" COP");
        precioPastaCar.setText(String.valueOf(pasta.getPrecios("Pasta alla Carbonara"))+" COP");
    }

    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(selecHome);
    }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(selecMenu);
    }
    public void IrReserva(ActionEvent actionEvent) {Ira.IrReserva(selecReservar);
    }

    @FXML
    public void onClickCarbonara(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickFetu(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickTrene(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickBolonesa(ActionEvent actionEvent) {
    }

    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        Ira.IrPlatosFuertes(selecVolver);
    }
}
