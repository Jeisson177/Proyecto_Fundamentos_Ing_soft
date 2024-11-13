package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import repository.modificarUsuarioRepository;

public class modificarMeseroController {

    @FXML
    public Button selecInicio;
    @FXML
    public Button selecHome;
    @FXML
    public Button selecMenu;
    @FXML
    private TableView<modificarUsuarioRepository.Usuario> tablaMeseros;
    @FXML
    private TableColumn<modificarUsuarioRepository.Usuario, String> columnaNombre;
    @FXML
    private TableColumn<modificarUsuarioRepository.Usuario, String> columnaEmail;
    @FXML
    private TableColumn<modificarUsuarioRepository.Usuario, String> columnaTelefono;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField contrasenaField;

    private RedirijirAdmin Ira = new RedirijirAdmin();
    
    private final modificarUsuarioRepository repository = new modificarUsuarioRepository();

    @FXML
    public void initialize() {
        // Configuración de las columnas de la tabla
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        columnaTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());

        // Cargar datos en la tabla
        cargarDatosTabla();

        // Actualizar los campos de entrada cuando se selecciona un mesero
        tablaMeseros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                modificarUsuarioRepository.Usuario mesero = newSelection;
                nombreField.setText(mesero.getNombre());
                emailField.setText(mesero.getEmail());
                telefonoField.setText(mesero.getTelefono());
                contrasenaField.setText(mesero.getContrasena());
            }
        });
    }

    private void cargarDatosTabla() {
        ObservableList<modificarUsuarioRepository.Usuario> meseros = FXCollections.observableArrayList(repository.obtenerTodosLosMeseros());
        tablaMeseros.setItems(meseros);
    }

    @FXML
    private void guardarCambios() {
        modificarUsuarioRepository.Usuario mesero = tablaMeseros.getSelectionModel().getSelectedItem();
        if (mesero != null) {
            mesero.setNombre(nombreField.getText());
            mesero.setEmail(emailField.getText());
            mesero.setTelefono(telefonoField.getText());
            mesero.setContrasena(contrasenaField.getText());

            repository.actualizarMesero(mesero);
            cargarDatosTabla();
        }
    }

    @FXML
    private void eliminarMesero() {
        modificarUsuarioRepository.Usuario mesero = tablaMeseros.getSelectionModel().getSelectedItem();
        if (mesero != null) {
            repository.eliminarMesero(mesero.getId());
            cargarDatosTabla();
        }
    }

    @FXML
    private void agregarMesero() {
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();
        String contrasena = contrasenaField.getText();

        if (!nombre.isEmpty() && !email.isEmpty() && !telefono.isEmpty() && !contrasena.isEmpty()) {
            repository.agregarMesero(nombre, email, telefono, contrasena);
            cargarDatosTabla();
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