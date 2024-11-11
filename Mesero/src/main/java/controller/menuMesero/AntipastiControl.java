package controller.menuMesero;

import controller.ControladorCarrito;
import controller.Plato;
import controller.PlatoCarrito;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.PlatosServices;
import services.Carrito;

import java.io.IOException;
import java.util.Objects;

public class AntipastiControl {

    @FXML
    public ImageView carpaccioResImage;
    public ImageView carpaccioSalmonImage;
    public ImageView focacciaGenoveseImage;
    public ImageView focacciaDolceImage;
    public ImageView focacciaVenetaImage;
    public ImageView focacciaPuglieseImage;
    public ImageView ensaladaCesarRomanaImage;
    public ImageView ensaldaPortobelloImage;
    public ImageView ensaladaVentrescaImage;
    public ImageView tablaQuesosImage;

    public Button menuBoton;
    public Button reservarBoton;
    public Button homeBoton;

    public Label carpaccioResPrecio;
    public Label carpaccioSalmonPrecio;
    public Label focacciaGenovesePrecio;
    public Label focacciaDolcePrecio;
    public Label focacciaVenetaPrecio;
    public Label focacciaPugliesePrecio;
    public Label ensaladaCesarRomanaPrecio;
    public Label ensaldaPortobelloPrecio;
    public Label ensaladaVentrescaPrecio;
    public Label tablaQuesosPrecio;

    public Text dispoCarRes;
    public Text dispoCarSal;
    public Text dispoFocGen;
    public Text dispoFocDol;
    public Text dispoFocVe;
    public Text dispoFocPu;
    public Text dispoCesar;
    public Text dispoPorto;
    public Text dispoVen;
    public Text dispoTabQuesos;

    private RedireccionGeneral Ira = new RedireccionGeneral();
    private PlatosServices platoService = new PlatosServices();
    private Carrito carrito = Carrito.getInstance();


    @FXML
    public void initialize() {
        cargarImagenes();
        cargarPreciosEntrada();
        actualizarDisponibilidadPlatos();
    }

    private void cargarImagenes() {
        carpaccioResImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/carpaccio_res.png"))));
        carpaccioSalmonImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/carpaccio_salmon.png"))));
        focacciaGenoveseImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_genovese.png"))));
        focacciaDolceImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_dolce.png"))));
        focacciaVenetaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_veneta.png"))));
        focacciaPuglieseImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/focaccia_pugliese.png"))));
        ensaladaCesarRomanaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_cesar_romana.png"))));
        ensaldaPortobelloImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_portobello.png"))));
        ensaladaVentrescaImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/ensalada_ventresca.png"))));
        tablaQuesosImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Imagenes/antipasti/tabal_quesos.png"))));
    }

    @FXML
    private void cargarPreciosEntrada() {
        carpaccioResPrecio.setText(platoService.obtenerPrecioPlato("Carpaccio de Res") + " COP");
        carpaccioSalmonPrecio.setText(platoService.obtenerPrecioPlato("Carpaccio de Salmon") + " COP");
        focacciaGenovesePrecio.setText(platoService.obtenerPrecioPlato("Focaccia Genovese") + " COP");
        focacciaDolcePrecio.setText(platoService.obtenerPrecioPlato("Focaccia Dolce") + " COP");
        focacciaVenetaPrecio.setText(platoService.obtenerPrecioPlato("Focaccia Veneta") + " COP");
        focacciaPugliesePrecio.setText(platoService.obtenerPrecioPlato("Focaccia Pugliese") + " COP");
        ensaladaCesarRomanaPrecio.setText(platoService.obtenerPrecioPlato("Ensalada Cesar Romana") + " COP");
        ensaldaPortobelloPrecio.setText(platoService.obtenerPrecioPlato("Ensalada de Portobello") + " COP");
        ensaladaVentrescaPrecio.setText(platoService.obtenerPrecioPlato("Ensalada de Ventresca") + " COP");
        tablaQuesosPrecio.setText(platoService.obtenerPrecioPlato("Tabla de Quesos") + " COP");
    }

    private void actualizarDisponibilidadPlatos() {
        actualizarTextoDisponibilidad(dispoCarRes, "Carpaccio de Res");
        actualizarTextoDisponibilidad(dispoCarSal, "Carpaccio de Salmon");
        actualizarTextoDisponibilidad(dispoFocGen, "Focaccia Genovese");
        actualizarTextoDisponibilidad(dispoFocDol, "Focaccia Dolce");
        actualizarTextoDisponibilidad(dispoFocVe, "Focaccia Veneta");
        actualizarTextoDisponibilidad(dispoFocPu, "Focaccia Pugliese");
        actualizarTextoDisponibilidad(dispoCesar, "Ensalada Cesar Romana");
        actualizarTextoDisponibilidad(dispoPorto, "Ensalada de Portobello");
        actualizarTextoDisponibilidad(dispoVen, "Ensalada de Ventresca");
        actualizarTextoDisponibilidad(dispoTabQuesos, "Tabla de Quesos");
    }

    private void actualizarTextoDisponibilidad(Text textElement, String nombrePlato) {
        if (platoService.estaDisponible(nombrePlato)) {
            textElement.setText("Disponible");
            textElement.setFill(Color.GREEN);
        } else {
            textElement.setText("No disponible");
            textElement.setFill(Color.RED);
        }
    }

    @FXML
    public void agregarPlato(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String nombrePlato = (String) clickedButton.getUserData();

        // Verificar la disponibilidad antes de agregar al carrito
        if (!platoService.estaDisponible(nombrePlato)) {
            mostrarAlerta("No disponible", "El plato \"" + nombrePlato + "\" no está disponible para servir.");
            return;
        }

        Plato plato = platoService.obtenerPlatoPorNombre(nombrePlato);
        if (plato != null) {
            boolean platoEncontrado = false;
            for (PlatoCarrito platoCarrito : carrito.obtenerPlatosEnCarrito()) {
                if (platoCarrito.getPlato().equals(plato)) {
                    platoCarrito.incrementarCantidad(); // Incrementa la cantidad si el plato ya está en el carrito
                    platoEncontrado = true;
                    break; // Salir del bucle después de actualizar la cantidad
                }
            }

            if (!platoEncontrado) {
                carrito.agregarPlato(plato); // Agregar el plato si no se encuentra en el carrito
            }

            mostrarAlerta("Plato agregado", "El plato \"" + nombrePlato + "\" ha sido agregado al carrito.");
        } else {
            mostrarAlerta("Error", "No se pudo agregar el plato al carrito.");
        }
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void revisarCarrito(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CarritoCompras.fxml"));
            Parent root = loader.load();

            ControladorCarrito controlador = loader.getController();
            controlador.setCarrito(Carrito.getInstance());  // Pasa la instancia del carrito

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
    public void menuButtonClick(ActionEvent actionEvent) {
        Ira.IrMenu(menuBoton);
    }

    public void reservarButtonClick(ActionEvent actionEvent) {
        Ira.IrReserva(reservarBoton);
    }

    public void homeButtonClick(ActionEvent actionEvent) {
        Ira.IrHome(homeBoton);
    }
}
