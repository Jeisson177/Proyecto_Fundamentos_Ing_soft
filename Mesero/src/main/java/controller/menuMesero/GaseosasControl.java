package controller.menuMesero;

import entities.Plato;
import entities.PlatoCarrito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.PlatosServices;
import services.Carrito;

import java.util.Objects;

public class GaseosasControl {

    @FXML
    public ImageView quatroImage;
    public ImageView cocaImage;
    public ImageView fantaImage;

    public Button botonHome;
    public Button botonReservar;
    public Button botonMenu;
    public Button botonRegresar;
    public Button botonImagenQuatro;
    public Button botonImagenCoca;
    public Button botonImagenFanta;
    public Text textoPrecio4;
    public Text textoPrecioFanta;
    public Text textoPrecioCoca;
    public Text dispo4;
    public Text dispoCoca;
    public Text dispoFanta;

    private PlatosServices bebidaService = new PlatosServices();
    private Carrito carrito = Carrito.getInstance();
    private RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        cargarImagenes();
        cargarPrecios();
        actualizarDisponibilidad();
    }

    private void cargarImagenes() {
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/quatro.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/cocacola.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Gaseosas/fanta.png")));

        ImageView imageView4 = new ImageView(img1);
        ImageView imageViewCoca = new ImageView(img2);
        ImageView imageViewFanta = new ImageView(img3);

        imageView4.setFitHeight(100);
        imageView4.setFitWidth(100);
        imageViewCoca.setFitHeight(100);
        imageViewCoca.setFitWidth(100);
        imageViewFanta.setFitHeight(100);
        imageViewFanta.setFitWidth(100);

        botonImagenQuatro.setGraphic(imageView4);
        botonImagenCoca.setGraphic(imageViewCoca);
        botonImagenFanta.setGraphic(imageViewFanta);
    }

    private void cargarPrecios() {
        textoPrecio4.setText(bebidaService.obtenerPrecioPlato("Quatro") + " COP");
        textoPrecioFanta.setText(bebidaService.obtenerPrecioPlato("Fanta") + " COP");
        textoPrecioCoca.setText(bebidaService.obtenerPrecioPlato("Coca Cola") + " COP");
    }

    private void actualizarDisponibilidad() {
        dispo4.setText(bebidaService.estaDisponible("Quatro") ? "Disponible" : "No disponible");
        dispoCoca.setText(bebidaService.estaDisponible("Coca Cola") ? "Disponible" : "No disponible");
        dispoFanta.setText(bebidaService.estaDisponible("Fanta") ? "Disponible" : "No disponible");

        dispo4.setFill(bebidaService.estaDisponible("Quatro") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
        dispoCoca.setFill(bebidaService.estaDisponible("Coca Cola") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
        dispoFanta.setFill(bebidaService.estaDisponible("Fanta") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
    }

    @FXML
    public void agregarGaseosa(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String nombreGaseosa = null;

        if (clickedButton == botonImagenQuatro) {
            nombreGaseosa = "Quatro";
        } else if (clickedButton == botonImagenCoca) {
            nombreGaseosa = "Coca Cola";
        } else if (clickedButton == botonImagenFanta) {
            nombreGaseosa = "Fanta";
        }

        if (nombreGaseosa == null || !bebidaService.estaDisponible(nombreGaseosa)) {
            mostrarAlerta("No disponible", "La bebida \"" + nombreGaseosa + "\" no está disponible.");
            return;
        }

        double precio = bebidaService.obtenerPrecioPlato(nombreGaseosa);
        if (precio <= 0) {
            mostrarAlerta("Error", "No se pudo obtener el precio de la bebida.");
            return;
        }

        Plato platoGaseosa = new Plato(0, nombreGaseosa, precio);
        boolean bebidaEncontrada = false;

        for (PlatoCarrito platoCarrito : carrito.obtenerPlatosEnCarrito()) {
            if (platoCarrito.getPlato().equals(platoGaseosa)) {
                platoCarrito.incrementarCantidad(1);
                bebidaEncontrada = true;
                break;
            }
        }

        if (!bebidaEncontrada) {
            carrito.agregarPlato(platoGaseosa);
        }

        mostrarAlerta("Bebida agregada", "La bebida \"" + nombreGaseosa + "\" ha sido agregada al carrito.");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void irAPantallaBebidas(ActionEvent event) {
        Ira.IrBebidas(botonRegresar);
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
