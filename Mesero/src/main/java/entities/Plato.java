package entities;

import java.util.Objects;

public class Plato {
    private final int idPlato;
    private String nombre;
    private double precio;
    private int cantidad;

    public Plato(int idPlato, String nombre, double precio) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = 1; // iniciar en 1
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Implementar equals() y hashCode() para comparar platos por nombre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return Objects.equals(nombre, plato.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
