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

public class VinosControl {

    @FXML
    public ImageView rosaImage;
    public ImageView tintoImage;
    public ImageView balncoImage;

    public Button botonHome;
    public Button botonReservar;
    public Button botonMenu;
    public Button botonRegresar;
    public Button botonImagenRosado;
    public Button botonImagenTinto;
    public Button botonImagenBlanco;

    public Text textoPrecioBlanco;
    public Text textoPrecioTinto;
    public Text textoPrecioRosado;

    public Text dispoRosa;
    public Text dispoTinto;
    public Text dispoBlanco;

    private final PlatosServices vinosService = new PlatosServices();
    private final Carrito carrito = Carrito.getInstance();
    private final RedireccionGeneral Ira = new RedireccionGeneral();

    @FXML
    public void initialize() {
        // Cargar las imágenes
        Image img1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Vinos/rosa.png")));
        Image img2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Vinos/tinto.png")));
        Image img3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/Vinos/white.png")));

        // Crear nuevos ImageView para cada botón (no reutilizar los existentes)
        ImageView imageViewRosa = new ImageView(img1);
        ImageView imageViewTinto = new ImageView(img2);
        ImageView imageViewBlanco = new ImageView(img3);

        // Ajustar el tamaño de los ImageView si es necesario
        imageViewRosa.setFitHeight(100);
        imageViewRosa.setFitWidth(100);
        imageViewTinto.setFitHeight(100);
        imageViewTinto.setFitWidth(100);
        imageViewBlanco.setFitHeight(100);
        imageViewBlanco.setFitWidth(100);

        // Establecer las imágenes en los botones
        botonImagenRosado.setGraphic(imageViewRosa);
        botonImagenTinto.setGraphic(imageViewTinto);
        botonImagenBlanco.setGraphic(imageViewBlanco);

        // Cargar precios desde el servicio de Platos
        textoPrecioRosado.setText(vinosService.obtenerPrecioPlato("Vino rosado") + " COP");
        textoPrecioTinto.setText(vinosService.obtenerPrecioPlato("Vino tinto") + " COP");
        textoPrecioBlanco.setText(vinosService.obtenerPrecioPlato("Vino blanco") + " COP");

        // Actualizar disponibilidad
        actualizarDisponibilidad();
    }

    private void actualizarDisponibilidad() {
        dispoRosa.setText(vinosService.estaDisponible("Vino rosado") ? "Disponible" : "No disponible");
        dispoTinto.setText(vinosService.estaDisponible("Vino tinto") ? "Disponible" : "No disponible");
        dispoBlanco.setText(vinosService.estaDisponible("Vino blanco") ? "Disponible" : "No disponible");

        dispoRosa.setFill(vinosService.estaDisponible("Vino rosado") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
        dispoTinto.setFill(vinosService.estaDisponible("Vino tinto") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
        dispoBlanco.setFill(vinosService.estaDisponible("Vino blanco") ? javafx.scene.paint.Color.GREEN : javafx.scene.paint.Color.RED);
    }

    @FXML
    public void agregarVino(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String nombreVino = null;

        if (clickedButton == botonImagenRosado) {
            nombreVino = "Vino rosado";
        } else if (clickedButton == botonImagenTinto) {
            nombreVino = "Vino tinto";
        } else if (clickedButton == botonImagenBlanco) {
            nombreVino = "Vino blanco";
        }

        if (!vinosService.estaDisponible(nombreVino)) {
            mostrarAlerta("No disponible", "El vino \"" + nombreVino + "\" no está disponible.");
            return;
        }

        double precio = vinosService.obtenerPrecioPlato(nombreVino);
        if (precio <= 0) {
            mostrarAlerta("Error", "No se pudo obtener el precio del vino.");
            return;
        }

        Plato platoVino = new Plato(0, nombreVino, precio);
        boolean vinoEncontrado = false;
        for (PlatoCarrito platoCarrito : carrito.obtenerPlatosEnCarrito()) {
            if (platoCarrito.getPlato().equals(platoVino)) {
                platoCarrito.incrementarCantidad(1);
                vinoEncontrado = true;
                break;
            }
        }

        if (!vinoEncontrado) {
            carrito.agregarPlato(platoVino);
        }

        mostrarAlerta("Vino agregado", "El vino \"" + nombreVino + "\" ha sido agregado al carrito.");
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

    public void irAPantallaMenu(ActionEvent event) {
        Ira.IrMenu(botonMenu);
    }

    public void irAPantallaHome(ActionEvent event) {
        Ira.IrHome(botonHome);
    }
}
