package controller.menuMesero;

import controller.Plato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
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
    public ToggleButton personalButton;
    @FXML
    public ToggleButton medianaButton;
    @FXML
    public ToggleButton familiarButton;
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
    @FXML
    public Button revisarCarritoBoton;

    private String tipoPizza;
    private String tamañoSeleccionado = null;
    private String adicionalSeleccionado = null;

    private RedireccionGeneral Ira = new RedireccionGeneral();
    private AgregarPizzaService pizzaService = new AgregarPizzaService();
    private Carrito carrito = Carrito.getInstance();

    public void setTipoPizza(String tipoPizza) {
        this.tipoPizza = tipoPizza;
        initializePizzaDetails();
    }

    public void initializePizzaDetails() {
        if (tipoPizza.equals("4quesos")) {
            Image quesos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Quesos.png")));
            pizzaSelec.setImage(quesos);
            pizza1.setText("Pepperoni");
            pizza2.setText("Margarita");
            precioPersonal.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Cuatro Quesos", "Personal")) + " COP");
            precioMediana.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Cuatro Quesos", "Mediana")) + " COP");
            precioFamiliar.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Cuatro Quesos", "Familiar")) + " COP");
        } else if (tipoPizza.equals("pepperoni")) {
            Image pepperoni = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Pep.png")));
            pizzaSelec.setImage(pepperoni);
            pizza1.setText("4 quesos");
            pizza2.setText("Margarita");
            precioPersonal.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Pepperoni", "Personal")) + " COP");
            precioMediana.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Pepperoni", "Mediana")) + " COP");
            precioFamiliar.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Pepperoni", "Familiar")) + " COP");
        } else if (tipoPizza.equals("margarita")) {
            Image margarita = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/Marga.png")));
            pizzaSelec.setImage(margarita);
            pizza1.setText("Pepperoni");
            pizza2.setText("4 quesos");
            precioPersonal.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Margarita", "Personal")) + " COP");
            precioMediana.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Margarita", "Mediana")) + " COP");
            precioFamiliar.setText(String.valueOf(pizzaService.getPrecioPizza("Pizza Margarita", "Familiar")) + " COP");
        }
    }

    @FXML
    public void onClickAgregarP(ActionEvent actionEvent) {
        if (personalButton.isSelected()) {
            tamañoSeleccionado = "Personal";
        } else if (medianaButton.isSelected()) {
            tamañoSeleccionado = "Mediana";
        } else if (familiarButton.isSelected()) {
            tamañoSeleccionado = "Familiar";
        }

        if (pizza1.isFocused()) {
            adicionalSeleccionado = pizza1.getText();
        } else if (pizza2.isFocused()) {
            adicionalSeleccionado = pizza2.getText();
        }

        if (tamañoSeleccionado == null || adicionalSeleccionado == null) {
            mostrarAlerta("Selección incompleta", "Por favor, selecciona un tamaño y una opción de adicionales antes de agregar al carrito.");
            return;
        }

        if (!pizzaService.estaDisponible(tipoPizza, tamañoSeleccionado)) {
            mostrarAlerta("No disponible", "La pizza de tamaño " + tamañoSeleccionado + " no está disponible.");
            return;
        }

        double precio = pizzaService.getPrecioPizza(tipoPizza, tamañoSeleccionado);
        if (precio < 0) {
            mostrarAlerta("Error", "No se pudo obtener el precio de la pizza.");
            return;
        }

        Plato plato = new Plato(0, tipoPizza + " - " + tamañoSeleccionado, precio);
        carrito.agregarPlato(plato);
        mostrarAlerta("Plato agregado", "La pizza ha sido agregada al carrito con el tamaño " + tamañoSeleccionado + " y adicional " + adicionalSeleccionado + ".");
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
            Stage stage = new Stage();
            stage.setTitle("Carrito");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista del carrito.");
        }
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

    @FXML
    public void onClickVolver(ActionEvent actionEvent) {
        Ira.IrPizzaMenu(selecVolver);
    }
}
