package com.example.Mesero;

import java.util.HashMap;
import java.util.Map;

public class Carrito {
    private Map<String, Integer> platos; // Mapa de nombre del plato y cantidad

    public Carrito() {
        this.platos = new HashMap<>();
    }

    public void agregarPlato(String nombrePlato) {
        platos.put(nombrePlato, platos.getOrDefault(nombrePlato, 0) + 1);
    }

    public void eliminarPlato(String nombrePlato) {
        if (platos.containsKey(nombrePlato)) {
            int cantidad = platos.get(nombrePlato);
            if (cantidad > 1) {
                platos.put(nombrePlato, cantidad - 1);
            } else {
                platos.remove(nombrePlato);
            }
        }
    }

    public Map<String, Integer> obtenerPlatos() {
        return platos;
    }

    public void limpiarCarrito() {
        platos.clear();
    }
}
