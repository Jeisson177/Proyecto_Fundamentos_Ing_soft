package services;

import entities.Plato;
import entities.PlatoCarrito;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Carrito {
    private static Carrito instance;
    private ObservableList<PlatoCarrito> platosEnCarrito;

    private Carrito() {
        this.platosEnCarrito = FXCollections.observableArrayList();
    }

    public static Carrito getInstance() {
        if (instance == null) {
            instance = new Carrito();
        }
        return instance;
    }

    public ObservableList<PlatoCarrito> obtenerPlatosEnCarrito() {
        return platosEnCarrito;
    }

    public void agregarPlato(Plato plato) {
        for (PlatoCarrito platoCarrito : platosEnCarrito) {
            if (platoCarrito.getPlato().equals(plato)) {
                platoCarrito.incrementarCantidad(1);  // Incrementa la cantidad si el plato ya está en el carrito
                platosEnCarrito.set(platosEnCarrito.indexOf(platoCarrito), platoCarrito); // Notifica el cambio
                return;
            }
        }
        // Si no se encuentra, agregar uno nuevo con cantidad inicial de 1
        platosEnCarrito.add(new PlatoCarrito(plato));
    }

    public void eliminarPlato(Plato plato) {
        platosEnCarrito.removeIf(platoCarrito -> platoCarrito.getPlato().equals(plato));
    }

    public double calcularTotalCarrito() {
        return platosEnCarrito.stream()
                .mapToDouble(PlatoCarrito::getCostoTotal)
                .sum();
    }

    public void vaciarCarrito() {
        platosEnCarrito.clear();
    }

}
