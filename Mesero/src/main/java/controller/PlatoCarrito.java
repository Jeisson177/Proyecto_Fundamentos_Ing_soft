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
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void incrementarCantidad(int cantidad) {
        cantidad = 1;
        this.cantidad += cantidad;
        if (this.cantidad < 0) {
            this.cantidad = 0;  // Asegura que no baje de 0
        }

    }


    public double getCostoTotal() {
        return plato.getPrecio() * cantidad;
    }
}
