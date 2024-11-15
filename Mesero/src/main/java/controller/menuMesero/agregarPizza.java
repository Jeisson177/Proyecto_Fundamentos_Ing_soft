package controller.menuMesero;

import entities.Plato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.AgregarPizzaService;
import services.Carrito;

import java.io.IOException;
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

    private String tipoPizza;
    private String tamanoSeleccionado;
    private String adicionalSeleccionado;

    private AgregarPizzaService pizzaService = new AgregarPizzaService();
    private Carrito carrito = Carrito.getInstance();
    private RedireccionGeneral Ira = new RedireccionGeneral();

    // Método para configurar el tipo de pizza
    public void setTipoPizza(String tipoPizza) {
        this.tipoPizza = tipoPizza;
        initializePizzaDetails();
    }

    // Inicializa los detalles de la pizza según el tipo
    public void initializePizzaDetails() {
        if (tipoPizza.equals("4quesos")) {
            configurarPizza("Cuatro Quesos", "/imagenes/PlatosFuertes/Quesos.png", "Pepperoni", "Margarita");
        } else if (tipoPizza.equals("pepperoni")) {
            configurarPizza("Pepperoni", "/imagenes/PlatosFuertes/Pep.png", "4 quesos", "Margarita");
        } else if (tipoPizza.equals("margarita")) {
            configurarPizza("Margarita", "/imagenes/PlatosFuertes/Marga.png", "Pepperoni", "4 quesos");
        }
    }

    // Configura la imagen, los adicionales y los precios de cada tamaño de la pizza
    private void configurarPizza(String nombrePizza, String rutaImagen, String adicional1, String adicional2) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaImagen)));
        pizzaSelec.setImage(image);
        pizza1.setText(adicional1);
        pizza2.setText(adicional2);

        precioPersonal.setText(pizzaService.getPrecioPizza("Pizza " + nombrePizza, "Personal") + " COP");
        precioMediana.setText(pizzaService.getPrecioPizza("Pizza " + nombrePizza, "Mediana") + " COP");
        precioFamiliar.setText(pizzaService.getPrecioPizza("Pizza " + nombrePizza, "Familiar") + " COP");
    }

    @FXML
    public void onClickAgregarP(ActionEvent actionEvent) {
        if (tamanoSeleccionado == null || adicionalSeleccionado == null) {
            mostrarAlerta("Selección incompleta", "Debe seleccionar un tamaño y un adicional.");
            return;
        }

        // Asegurarse de pasar el nombre correcto de la pizza y el tamaño
        int precio = pizzaService.getPrecioPizza(tipoPizza, tamanoSeleccionado);
        if (precio <= 0) {
            mostrarAlerta("Error", "Precio no disponible.");
            return;
        }

        // Crear el objeto Plato con el nombre completo incluyendo tipo y tamaño
        Plato pizza = new Plato(0, "Pizza " + tipoPizza + " " + tamanoSeleccionado + " (" + adicionalSeleccionado + ")", precio);
        carrito.agregarPlato(pizza);

        mostrarAlerta("Pizza agregada", "La pizza ha sido agregada al carrito.");
    }

// Añadir breakpoint y ver variables
    // (reserva sin cita) select base de datos y ver las reservas de hoy, comparar las horas para ver si la mesa X estan disponibles. Si si hay se crea una reserva

    // Muestra una alerta informativa
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Maneja la selección de tamaño de pizza
    @FXML
    public void seleccionarTamano(ActionEvent event) {
        tamanoSeleccionado = ((Button) event.getSource()).getText();
        tamanoSeleccionado = tamanoSeleccionado.split(" ")[0]; // Obtener solo "Personal", "Mediana", o "Familiar"
    }

    // Maneja la selección de adicional de pizza
    @FXML
    public void seleccionarAdicional(ActionEvent event) {
        adicionalSeleccionado = ((Button) event.getSource()).getText();
    }

    // Evento para revisar el carrito
    @FXML
    public void revisarCarrito(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CarritoCompras.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Carrito");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la vista del carrito.");
        }
    }

    // Evento para volver al menú de pizzas
    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/menu/PizzaMenu.fxml"));
            Stage stage = (Stage) selecVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la vista de PizzaMenu.");
        }
    }

    // Redirección a la pantalla principal
    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(botonHome);
    }

    // Redirección al menú
    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(botonMenu);
    }
}
