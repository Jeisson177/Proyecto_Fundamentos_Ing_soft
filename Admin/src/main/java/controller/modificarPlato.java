package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import repository.modificarPlatoRepository;
import repository.modificarPlatoRepository.Plato;
import repository.modificarPlatoRepository.Ingrediente;

import java.util.Objects;

public class modificarPlato {

    @FXML
    public Button agregarIngrediente;
    @FXML
    public Button eliminarIngrediente;
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecInicio;
    @FXML
    private TableView<Plato> tablaPlatos;
    @FXML
    private TableColumn<Plato, String> columnaNombre;
    @FXML
    private TableColumn<Plato, String> columnaDescripcion;
    @FXML
    private TableColumn<Plato, Integer> columnaPrecio;
    @FXML
    private TableColumn<Plato, String> columnaCategoria;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField descripcionField;
    @FXML
    private TextField precioField;
    @FXML
    private TextField categoriaField;

    @FXML
    private TableView<Ingrediente> tablaIngredientes;
    @FXML
    private TableColumn<Ingrediente, String> columnaNombreIngrediente;

    @FXML
    private TableView<Ingrediente> tablaIngredientesPlato;
    @FXML
    private TableColumn<Ingrediente, String> columnaIngredientePlato;

    private RedirijirAdmin Ira = new RedirijirAdmin();

    private final modificarPlatoRepository repository = new modificarPlatoRepository();

    @FXML
    public void initialize() {

        // Configuración de columnas de la tabla de platos
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
        columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().precioProperty().asObject());
        columnaCategoria.setCellValueFactory(cellData -> cellData.getValue().categoriaProperty());

        // Configuración de columnas de la tabla de ingredientes disponibles
        columnaNombreIngrediente.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        // Configuración de columnas de la tabla de ingredientes del plato
        columnaIngredientePlato.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        // Cargar datos en las tablas
        cargarDatosTabla();
        cargarIngredientes();

        // Actualizar la lista de ingredientes del plato cuando se selecciona un nuevo plato
        tablaPlatos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Plato plato = newSelection;
                nombreField.setText(plato.getNombre());
                descripcionField.setText(plato.getDescripcion());
                precioField.setText(String.valueOf(plato.getPrecio()));
                categoriaField.setText(plato.getCategoria());

                cargarIngredientesPlato(plato.getId());
            }
        });
    }

    private void cargarDatosTabla() {
        ObservableList<Plato> platos = FXCollections.observableArrayList(repository.obtenerTodosLosPlatos());
        tablaPlatos.setItems(platos);
    }

    private void cargarIngredientes() {
        ObservableList<Ingrediente> ingredientes = FXCollections.observableArrayList(repository.obtenerTodosLosIngredientes());
        tablaIngredientes.setItems(ingredientes);
    }

    private void cargarIngredientesPlato(int platoId) {
        ObservableList<Ingrediente> ingredientesPlato = FXCollections.observableArrayList(repository.obtenerIngredientesDePlato(platoId));
        tablaIngredientesPlato.setItems(ingredientesPlato);
    }

    @FXML
    private void guardarCambios() {
        Plato plato = tablaPlatos.getSelectionModel().getSelectedItem();
        if (plato != null) {
            plato.setNombre(nombreField.getText());
            plato.setDescripcion(descripcionField.getText());
            plato.setPrecio(Integer.parseInt(precioField.getText()));
            plato.setCategoria(categoriaField.getText());

            repository.actualizarPlato(plato);
            cargarDatosTabla();
        }
    }

    @FXML
    private void eliminarIngredienteDelPlato() {
        Plato plato = tablaPlatos.getSelectionModel().getSelectedItem();
        Ingrediente ingrediente = tablaIngredientesPlato.getSelectionModel().getSelectedItem();

        if (plato != null && ingrediente != null) {
            repository.eliminarIngredienteDelPlato(plato.getId(), ingrediente.getId());
            cargarIngredientesPlato(plato.getId());
        }
    }

    @FXML
    private void agregarIngredienteAlPlato() {
        Plato plato = tablaPlatos.getSelectionModel().getSelectedItem();
        Ingrediente ingrediente = tablaIngredientes.getSelectionModel().getSelectedItem();

        if (plato != null && ingrediente != null) {
            repository.agregarIngredienteAlPlato(plato.getId(), ingrediente.getId(), 100); // Cantidad por defecto
            cargarIngredientesPlato(plato.getId());
        }
    }

    public void IrHome(ActionEvent actionEvent) {
        Ira.IrHome(selecHome);
    }

    public void IrMenu(ActionEvent actionEvent) {
        Ira.IrMenu(selecMenu);
    }

    public void IrInicio(ActionEvent actionEvent) {
        Ira.IrInicio(selecInicio);
    }


}
