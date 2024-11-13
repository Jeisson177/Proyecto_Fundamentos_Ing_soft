package controller;

import controller.menu.RedireccionGeneral;
import controller.menu.agregarPizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import repository.modificarInventarioRepository;


import java.util.Date;
import java.util.Objects;

public class modificarInventario {

    @FXML
    public Button selecHome;
    @FXML
    public ImageView instaImagen;
    @FXML
    public Button selecMenu;
    @FXML
    public Button selecAgregar;
    @FXML
    public Button selecEliminar;
    @FXML
    public Button selecInicio;
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

    private RedirijirAdmin Ira=new RedirijirAdmin();

    private modificarInventarioRepository repository = new modificarInventarioRepository();


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


    public void IrHome(ActionEvent actionEvent) { Ira.IrHome(selecHome);
    }

    public void IrMenu(ActionEvent actionEvent) { Ira.IrMenu(selecMenu);
    }

    public void IrInicio(ActionEvent actionEvent) { Ira.IrInicio(selecInicio);
    }

    public void IrModificarInventario(ActionEvent actionEvent) {

        Button sourceButton = (Button) actionEvent.getSource(); // Detecta qué botón fue presionado
        // Variable para almacenar el cambio del inventario
        String modificacionInventario = "";

        // Comparar el texto del botón o usar el ID del botón para determinar cuál fue seleccionado
        if (sourceButton.equals(selecAgregar)) {
            modificacionInventario  = "Agregar";
            try {
                // Carga la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarOeliminarInventario.fxml"));
                Parent root = loader.load();

                // Obtén el controlador de la nueva vista
                AgregarOeliminarInventario AgregarAlInventario = loader.getController();

                AgregarAlInventario.setAgregarOeliminar(modificacionInventario);

                // Crea una nueva escena
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Agregar al inventario"); // Título de la nueva ventana
                stage.show();

                //Opcionalmente, cierra la ventana actual
                ((Stage) selecAgregar.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(sourceButton.equals(selecEliminar)){
            modificacionInventario  = "Eliminar";
            try {
                // Carga la nueva ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarOeliminarInventario.fxml"));
                Parent root = loader.load();

                // Obtén el controlador de la nueva vista
                AgregarOeliminarInventario AgregarAlInventario = loader.getController();

                AgregarAlInventario.setAgregarOeliminar(modificacionInventario);

                // Crea una nueva escena
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Eliminar del inventario"); // Título de la nueva ventana
                stage.show();

                //Opcionalmente, cierra la ventana actual
                ((Stage) selecEliminar.getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
