package com.example.Mesero;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import java.util.Map;

public class ControladorCarrito {
    @FXML
    private ListView<String> listaPlatos;

    @FXML
    private Button botonFinalizar;

    @FXML
    private Button botonVaciar;

    private com.example.Mesero.Carrito carrito;

    public ControladorCarrito() {
        carrito = new com.example.Mesero.Carrito();
    }

    @FXML
    public void initialize() {
        actualizarLista();

        botonFinalizar.setOnAction(event -> {
            // Lógica para finalizar el pedido
        });

        botonVaciar.setOnAction(event -> {
            carrito.limpiarCarrito();
            actualizarLista();
        });
    }

    public void agregarPlatoAlCarrito(String nombrePlato) {
        carrito.agregarPlato(nombrePlato);
        actualizarLista();
    }

    private void actualizarLista() {
        listaPlatos.getItems().clear();
        Map<String, Integer> platos = carrito.obtenerPlatos();
        for (Map.Entry<String, Integer> entry : platos.entrySet()) {
            listaPlatos.getItems().add(entry.getKey() + " x" + entry.getValue());
        }
    }
}
