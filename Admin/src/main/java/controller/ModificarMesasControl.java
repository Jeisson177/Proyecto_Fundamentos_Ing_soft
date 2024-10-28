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
import java.util.Map;
import java.util.ResourceBundle;

public class ModificarMesasControl {

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
    private int idMesaSeleccionada = -1;

    private  modifificarRepository mesaRepository = new  modifificarRepository();
    // Este método se ejecuta automáticamente cuando la vista se carga
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
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
        switch (idMesa) {
            case 1: return bntm1;
            case 2: return bntm2;
            case 3: return bntm3;
            case 4: return bntm4;
            case 5: return bntm5;
            case 6: return bntm6;
            case 7: return bntm7;
            case 8: return bntm8;
            case 9: return bntm9;
            case 10: return bntm10;
            case 11: return bntm11;
            case 12: return bntm12;
            case 13: return bntm13;
            case 14: return bntm14;
            case 15: return bntm15;
            case 16: return bntm16;
            case 17: return bntm17;
            case 18: return bntm18;
            case 19: return bntm19;
            default: return null;
        }
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

    private int obtenerIdMesa(Button mesaButton) {
        if (mesaButton == bntm1) return 1;
        if (mesaButton == bntm2) return 2;
        if (mesaButton == bntm3) return 3;
        if (mesaButton == bntm4) return 4;
        if (mesaButton == bntm5) return 5;
        if (mesaButton == bntm6) return 6;
        if (mesaButton == bntm7) return 7;
        if (mesaButton == bntm8) return 8;
        if (mesaButton == bntm9) return 9;
        if (mesaButton == bntm10) return 10;
        if (mesaButton == bntm11) return 11;
        if (mesaButton == bntm12) return 12;
        if (mesaButton == bntm13) return 13;
        if (mesaButton == bntm14) return 14;
        if (mesaButton == bntm15) return 15;
        if (mesaButton == bntm16) return 16;
        if (mesaButton == bntm17) return 17;
        if (mesaButton == bntm18) return 18;
        if (mesaButton == bntm19) return 19;
        return -1;  // Si no es ningún botón válido
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
}
