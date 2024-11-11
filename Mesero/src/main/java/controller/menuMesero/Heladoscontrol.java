package controller.menuMesero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.AgregarPizzaService;
import services.PlatosServices;

import java.util.Objects;

public class Heladoscontrol {

    @FXML
    public Label precioframbuesa;
    @FXML
    public Button botonHome;
    @FXML
    public Button botonMenu;
    @FXML
    public Button botonReservar;
    @FXML
    private ImageView heladoavellana;
    @FXML
    private ImageView heladolimon;
    @FXML
    private ImageView heladoframbuesa;
    @FXML
    private ImageView heladofresa;
    @FXML
    private ImageView heladochocolatepicante;
    @FXML
    private ImageView heladogrande;
    @FXML
    private ImageView instaimage;
    @FXML
    private ImageView heladosletra;

    @FXML
    public Button agregaraorden;
    @FXML
    public Button volverPostres;
    @FXML
    public Button volverMenu;

    @FXML
    public Label precioavellana;
    @FXML
    public Label preciolimon;
    @FXML
    public Label preciofresa;
    @FXML
    public Label preciochocolate;

    private PlatosServices heladoService = new PlatosServices();
    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        // Cargar las imágenes al inicializar la vista
        cargarImagenes();
        cargarPrecios();
    }

    private void cargarImagenes() {
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/AVELLANA_IMAGE.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/LIMON_IMAGE.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/FRAMBUESA_IMAGE.png")));
        Image img4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/FRESA_IMAGE.png")));
        Image img5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/CHOCOLATE_IMAGE.png")));
        Image img6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/HELADOS_IMAGE.png")));
        Image img7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/INSTA_IMAGE.png")));
        Image img8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/HELADOSLETRA_IMAGE.png")));

        heladoavellana.setImage(img1);
        heladolimon.setImage(img2);
        heladoframbuesa.setImage(img3);
        heladofresa.setImage(img4);
        heladochocolatepicante.setImage(img5);
        heladogrande.setImage(img6);
        instaimage.setImage(img7);
        heladosletra.setImage(img8);
    }

    private void cargarPrecios() {
        precioavellana.setText(heladoService.obtenerPrecioPlato("Gelato Bacio (Avellana)") + " COP");
        precioframbuesa.setText(heladoService.obtenerPrecioPlato("Gelato Lampone (Frambuesa)") + " COP");
        preciochocolate.setText(heladoService.obtenerPrecioPlato("Gelato Cioccolato con Peperoncino") + " COP");
        preciofresa.setText(heladoService.obtenerPrecioPlato("Gelato Fragola (Fresa)") + " COP");
        preciolimon.setText(heladoService.obtenerPrecioPlato("Gelato Limone (Limon)") + " COP");
    }

    public void clickHELADO(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Postremenu.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("PostreMenu");
            stage.show();
            ((Stage) volverMenu.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
        // Acción para un botón específico si se necesita
    }

    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(botonHome);
    }

    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(botonMenu);
    }

    public void IrReserva(ActionEvent actionEvent) {
        Ira.IrReserva(botonReservar);
    }
}