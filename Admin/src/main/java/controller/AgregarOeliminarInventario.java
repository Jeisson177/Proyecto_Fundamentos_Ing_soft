package controller;

import controller.menu.RedireccionGeneral;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    // Campos de entrada
    @FXML
    private TextField nombreField;
    @FXML
    private TextField cantidadField;
    @FXML
    private DatePicker fechaField;
    @FXML
    private TextField unidadField;

    private String modificacionInventario;
    private RedireccionGeneral Ira = new RedireccionGeneral();
    private modificarInventarioRepository repository = new modificarInventarioRepository();

    public void setAgregarOeliminar(String modificacionInventario) {
        this.modificacionInventario = modificacionInventario;
        actualizarVista();
    }

    @FXML
    public void initialize() {
        Image ig = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/PlatosFuertes/ig.png")));

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadInv"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaVen"));
        columnaUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        instaImagen.setImage(ig);

        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        ObservableList<modificarInventarioRepository.InventarioAlimento> datosInventario = FXCollections.observableArrayList(repository.obtenerTodosLosInventarios());
        tablaInventario.setItems(datosInventario);
    }

    private void actualizarVista() {
        if ("Agregar".equals(modificacionInventario)) {
            AgregarOeliminar.setText("Agregar");
            nombreField.setDisable(false);
            cantidadField.setDisable(false);
            fechaField.setDisable(false);
            unidadField.setDisable(false);
        } else if ("Eliminar".equals(modificacionInventario)) {
            AgregarOeliminar.setText("Eliminar");
            cantidadField.setDisable(true);
            fechaField.setDisable(true);
            unidadField.setDisable(true);
        }
    }

    @FXML
    private void accionAgregarOEliminar(ActionEvent event) {
        if ("Agregar".equals(modificacionInventario)) {
            agregarIngrediente();
        } else if ("Eliminar".equals(modificacionInventario)) {
            eliminarIngrediente();
        }
    }

    private void agregarIngrediente() {
        String nombre = nombreField.getText();
        Integer cantidad = Integer.parseInt(cantidadField.getText());
        Date fecha = java.sql.Date.valueOf(fechaField.getValue());
        String unidad = unidadField.getText();

        modificarInventarioRepository.InventarioAlimento nuevoIngrediente = new modificarInventarioRepository.InventarioAlimento(nombre, cantidad, fecha, unidad);
        repository.agregarIngrediente(nuevoIngrediente);
        cargarDatosTabla();
    }

    private void eliminarIngrediente() {
        String nombre = nombreField.getText();
        repository.eliminarIngrediente(nombre);
        cargarDatosTabla();
    }

    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(selecHome);
    }

    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(selecMenu);
    }
}
