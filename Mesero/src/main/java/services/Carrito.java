package services;

import controller.Plato;
import controller.PlatoCarrito;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private static Carrito instance;
    private List<PlatoCarrito> platosEnCarrito;

    private Carrito() {
        this.platosEnCarrito = new ArrayList<>();
    }

    public static Carrito getInstance() {
        if (instance == null) {
            instance = new Carrito();
        }
        return instance;
    }

    public List<PlatoCarrito> obtenerPlatosEnCarrito() {
        return platosEnCarrito;
    }

    public void agregarPlato(Plato plato) {
        for (PlatoCarrito platoCarrito : platosEnCarrito) {
            if (platoCarrito.getPlato().equals(plato)) {
                platoCarrito.incrementarCantidad();  // Incrementa la cantidad si el plato ya está en el carrito
                return;
            }
        }
        // Si no se encuentra, agregar uno nuevo con cantidad inicial de 1
        platosEnCarrito.add(new PlatoCarrito(plato));
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
