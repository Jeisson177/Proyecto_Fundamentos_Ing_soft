package controller.menuMesero;

import controller.ControladorCarrito;
import entities.Plato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.AgregarPizzaService;
import services.Carrito;
import java.io.IOException;
import java.util.Objects;

public class Heladoscontrol {

    @FXML
    public Label precioframbuesa, precioavellana, preciolimon, preciofresa, preciochocolate;
    @FXML
    public Button botonHome, botonMenu, botonCarrito, volverPostres;
    @FXML
    private ImageView heladoavellana, heladolimon, heladoframbuesa, heladofresa, heladochocolatepicante, heladogrande, instaimage, heladosletra;
    @FXML
    public Text dispoAvellana, dispoLimon, dispoFrambuesa, dispoFresa, dispoChocPicante;

    private AgregarPizzaService heladoService = new AgregarPizzaService();
    private Carrito carrito = Carrito.getInstance();

    @FXML
    public void initialize() {
        // Cargar las imágenes
        heladoavellana.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/AVELLANA_IMAGE.png"))));
        heladolimon.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/LIMON_IMAGE.png"))));
        heladoframbuesa.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/FRAMBUESA_IMAGE.png"))));
        heladofresa.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/FRESA_IMAGE.png"))));
        heladochocolatepicante.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/CHOCOLATE_IMAGE.png"))));
        heladogrande.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/HELADOS_IMAGE.png"))));
        instaimage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/postres/INSTA_IMAGE.png"))));
        heladosletra.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Imagenes/helados/HELADOSLETRA_IMAGE.png"))));

        // Cargar precios
        precioavellana.setText(heladoService.getPrecios("Gelato Bacio (Avellana)") + " COP");
        precioframbuesa.setText(heladoService.getPrecios("Gelato Lampone (Frambuesa)") + " COP");
        preciochocolate.setText(heladoService.getPrecios("Gelato Cioccolato con Peperoncino") + " COP");
        preciofresa.setText(heladoService.getPrecios("Gelato Fragola (Fresa)") + " COP");
        preciolimon.setText(heladoService.getPrecios("Gelato Limone (Limon)") + " COP");

        // Verificar disponibilidad y mostrar en pantalla
        dispoAvellana.setText(heladoService.estaDisponibleHelado("Gelato Bacio (Avellana)") ? "Disponible" : "No disponible");
        dispoLimon.setText(heladoService.estaDisponibleHelado("Gelato Limone (Limon)") ? "Disponible" : "No disponible");
        dispoFrambuesa.setText(heladoService.estaDisponibleHelado("Gelato Lampone (Frambuesa)") ? "Disponible" : "No disponible");
        dispoFresa.setText(heladoService.estaDisponibleHelado("Gelato Fragola (Fresa)") ? "Disponible" : "No disponible");
        dispoChocPicante.setText(heladoService.estaDisponibleHelado("Gelato Cioccolato con Peperoncino") ? "Disponible" : "No disponible");
    }

    @FXML
    public void agregarHelado(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String nombreHelado = (String) clickedButton.getUserData();

        // Verificar disponibilidad
        if (!heladoService.estaDisponibleHelado(nombreHelado)) {
            mostrarAlerta("No disponible", "El helado \"" + nombreHelado + "\" no está disponible.");
            return;
        }

        // Obtener el precio del helado y agregarlo al carrito
        int precioHelado = heladoService.getPrecios(nombreHelado);
        Plato helado = new Plato(-1, nombreHelado, precioHelado);
        carrito.agregarPlato(helado);

        mostrarAlerta("Agregado", "El helado \"" + nombreHelado + "\" fue agregado al carrito.");
    }

    @FXML
    public void revisarCarrito(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CarritoCompras.fxml"));
            Parent root = loader.load();

            ControladorCarrito controlador = loader.getController();
            controlador.setCarrito(carrito);

            Stage stage = new Stage();
            stage.setTitle("Carrito");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista del carrito.");
        }
    }

    @FXML
    public void clickHELADO(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/menu/PostreMenu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("PostreMenu");
            stage.show();

            ((Stage) volverPostres.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void IrHome(ActionEvent actionEvent) {
        new RedireccionGeneral().IrHome(botonHome);
    }

    @FXML
    public void IrMenu(ActionEvent actionEvent) {
        new RedireccionGeneral().IrMenu(botonMenu);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
