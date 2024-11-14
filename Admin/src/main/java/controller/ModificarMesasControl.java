package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import repository.modifificarRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ModificarMesasControl {

    @FXML
    public Button selecInicio;
    @FXML
    public Button bntm1, bntm2, bntm3, bntm4, bntm5, bntm6, bntm7, bntm8, bntm9, bntm10;
    @FXML
    public Button bntm11, bntm12, bntm13, bntm14, bntm15, bntm16, bntm17, bntm18, bntm19;
    @FXML
    public Label labelIdMesa;
    @FXML
    public TextField fieldXposicion;
    @FXML
    public TextField fieldYposicion;
    public Button btnModificar;
    private int idMesaSeleccionada = -1;
    private final Map<Button, Integer> botonIdMap = new HashMap<>();
    private  modifificarRepository mesaRepository = new  modifificarRepository();
    private RedirijirAdmin Ira = new RedirijirAdmin();
    // Este metodo se ejecuta automáticamente cuando la vista se carga
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        inicializarMapaBotones();
        cargarPosicion(); // Llamar a cargarPosicion() al inicializar la vista
    }
    private void cargarPosicion() {
        try {
            Map<Integer, Point2D> posicionesMesas = mesaRepository.obtenerPosicionesMesas();
            for (Map.Entry<Integer, Point2D> entrada : posicionesMesas.entrySet()) {
                Button mesaButton = getMesaButton(entrada.getKey());
                if (mesaButton != null) {
                    mesaButton.setLayoutX(entrada.getValue().getX());
                    mesaButton.setLayoutY(entrada.getValue().getY());
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Conexión");
            alert.setHeaderText("No se pudo conectar con la base de datos");
            alert.setContentText("No se pudo cargar posición");
            alert.showAndWait();
        }
    }

    private Button getMesaButton(int idMesa) {
        for (Map.Entry<Button, Integer> entry : botonIdMap.entrySet()) {
            if (entry.getValue() == idMesa) {
                return entry.getKey();
            }
        }
        return null;
    }
    private int obtenerIdMesa(Button mesaButton) {
        return botonIdMap.getOrDefault(mesaButton, -1);
    }
    @FXML
    public void SeleccionarMesa(ActionEvent actionEvent) {
        Button mesaSeleccionada = (Button) actionEvent.getSource();  // Obtener el botón clicado
        idMesaSeleccionada = obtenerIdMesa(mesaSeleccionada);        // Obtener el ID de la mesa seleccionada

        if (idMesaSeleccionada != -1) {
            // Cargar las coordenadas actuales de la mesa desde la base de datos
            try {
                Point2D posicionActual = mesaRepository.obtenerPosicionMesa(idMesaSeleccionada);
                if (posicionActual != null) {
                    // Mostrar ID y posición de la mesa en el label
                    labelIdMesa.setText("Mesa ID: " + idMesaSeleccionada +
                            " | Ubicación actual - X: " + posicionActual.getX() +
                            " , Y: " + posicionActual.getY());
                    // Llenar los TextFields con la ubicación actual
                    fieldXposicion.setText(String.valueOf(posicionActual.getX()));
                    fieldYposicion.setText(String.valueOf(posicionActual.getY()));
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de Conexión");
                alert.setHeaderText("No se pudo conectar con la base de datos");
                alert.setContentText("No se pudo cargar la mesa");
                alert.showAndWait();
            }
        }
    }

    private void inicializarMapaBotones() {
        botonIdMap.put(bntm1, 1);
        botonIdMap.put(bntm2, 2);
        botonIdMap.put(bntm3, 3);
        botonIdMap.put(bntm4, 4);
        botonIdMap.put(bntm5, 5);
        botonIdMap.put(bntm6, 6);
        botonIdMap.put(bntm7, 7);
        botonIdMap.put(bntm8, 8);
        botonIdMap.put(bntm9, 9);
        botonIdMap.put(bntm10, 10);
        botonIdMap.put(bntm11, 11);
        botonIdMap.put(bntm12, 12);
        botonIdMap.put(bntm13, 13);
        botonIdMap.put(bntm14, 14);
        botonIdMap.put(bntm15, 15);
        botonIdMap.put(bntm16, 16);
        botonIdMap.put(bntm17, 17);
        botonIdMap.put(bntm18, 18);
        botonIdMap.put(bntm19, 19);
    }


    public void actualizarPosicion(ActionEvent actionEvent) {
        if (idMesaSeleccionada == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Conexión");
            alert.setHeaderText("No se pudo conectar con la base de datos");
            alert.setContentText("No se ha seleccionado mesa");
            alert.showAndWait();
            return;
        }
        try {
            double nuevaX = Double.parseDouble(fieldXposicion.getText());
            double nuevaY = Double.parseDouble(fieldYposicion.getText());

            // Actualizar la posición en la base de datos
            mesaRepository.actualizarPosicionMesa(idMesaSeleccionada, nuevaX, nuevaY);

            // Actualizar la posición en la interfaz
            Button mesaButton = getMesaButton(idMesaSeleccionada);
            if (mesaButton != null) {
                mesaButton.setLayoutX(nuevaX);
                mesaButton.setLayoutY(nuevaY);
            }

            // Mostrar éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Posición actualizada correctamente");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Conexión");
            alert.setHeaderText("No se pudo conectar con la base de datos");
            alert.setContentText("Por favor, ingrese valores numéricos válidos para las coordenadas.");
            alert.showAndWait();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Conexión");
            alert.setHeaderText("No se pudo conectar con la base de datos");
            alert.setContentText("Error al actualizar la base de datos.");
            alert.showAndWait();

        }

    }

    public void IrInicio(ActionEvent actionEvent) {
        Ira.IrInicio(selecInicio);
    }
}
