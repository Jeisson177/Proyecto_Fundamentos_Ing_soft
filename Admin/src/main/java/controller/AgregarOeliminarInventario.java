package controller;

import controller.menu.RedireccionGeneral;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import repository.modificarInventarioRepository;

import java.util.Date;
import java.util.Objects;

public class AgregarOeliminarInventario {
    @FXML
    public Button selecHome;
    @FXML
    public ImageView instaImagen;
    @FXML
    public Button selecMenu;
    @FXML
    public Button AgregarOeliminar;
    @FXML
    private TableView<modificarInventarioRepository.InventarioAlimento> tablaInventario;
    @FXML
    private TableColumn<modificarInventarioRepository.InventarioAlimento, String> columnaNombre;
    @FXML
    private TableColumn<modificarInventarioRepository.InventarioAlimento, Integer> columnaCantidad;
    @FXML
    private TableColumn<modificarInventarioRepository.InventarioAlimento, Date> columnaFecha;
    @FXML
    private TableColumn<modificarInventarioRepository.InventarioAlimento, String> columnaUnidad;

    private String modificacionInventario;

    private RedireccionGeneral Ira = new RedireccionGeneral();

    private modificarInventarioRepository repository = new modificarInventarioRepository();

    public void setAgregarOeliminar(String modificacionInventario) {
        this.modificacionInventario = modificacionInventario;
        actualizarVista(); // Llama al método que actualiza la vista
    }

    @FXML
    public void initialize() {
        Image ig = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/ig.png")));

        // Configurar las columnas para asociarse con los campos de InventarioAlimento
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadInv"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaVen"));
        columnaUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        instaImagen.setImage(ig);

        // Cargar los datos en la tabla
        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        // Obtener datos de la base de datos usando el repository
        ObservableList<modificarInventarioRepository.InventarioAlimento> datosInventario = FXCollections.observableArrayList(repository.obtenerTodosLosInventarios());
        // Asignar los datos al TableView
        tablaInventario.setItems(datosInventario);
    }

    private void actualizarVista() {
        if ("Agregar".equals(modificacionInventario)) {
            AgregarOeliminar.setText("Agregar");
        } else if ("Eliminar".equals(modificacionInventario)) {
            AgregarOeliminar.setText("Eliminar");
        }
    }

    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(selecHome);
    }

    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(selecMenu);
    }
}
