package controller;

public class PlatoCarrito {
    private Plato plato;
    private int cantidad;

    public PlatoCarrito(Plato plato) {
        this.plato = plato;
        this.cantidad = 1; // inicial cantidad es 1
    }

    public Plato getPlato() {
        return plato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void incrementarCantidad() {
        this.cantidad++;
    }

    public double getCostoTotal() {
        return plato.getPrecio() * cantidad;
    }
}
