package services;

import controller.Plato;
import controller.PlatoCarrito;
import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<PlatoCarrito> platosEnCarrito;

    public Carrito() {
        this.platosEnCarrito = new ArrayList<>();
    }

    public List<PlatoCarrito> obtenerPlatosEnCarrito() {
        return platosEnCarrito;
    }

    public void agregarPlato(Plato plato) {
        // Buscar si el plato ya está en el carrito
        for (PlatoCarrito platoCarrito : platosEnCarrito) {
            if (platoCarrito.getPlato().equals(plato)) {
                platoCarrito.incrementarCantidad();
                return; // Salir después de incrementar la cantidad
            }
        }
        // Si no se encontró, agregar uno nuevo
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
