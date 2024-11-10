package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.modificarInventarioRepository;


import java.util.Date;

public class modificarInventario {

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

    private modificarInventarioRepository repository = new modificarInventarioRepository();


    @FXML
    public void initialize() {
        // Configurar las columnas para asociarse con los campos de InventarioAlimento
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadInv"));
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaVen"));
        columnaUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));

        // Cargar los datos en la tabla
        cargarDatosTabla();
    }

    private void cargarDatosTabla() {
        // Obtener datos de la base de datos usando el repository
        ObservableList<modificarInventarioRepository.InventarioAlimento> datosInventario = FXCollections.observableArrayList(repository.obtenerTodosLosInventarios());
        // Asignar los datos al TableView
        tablaInventario.setItems(datosInventario);
    }


}
