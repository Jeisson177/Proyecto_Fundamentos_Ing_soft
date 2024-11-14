package controller;

import javafx.beans.property.*;

public class ItemFactura {

    private final IntegerProperty cantidad;
    private final StringProperty nombre;
    private final DoubleProperty precio;
    private final DoubleProperty total;

    public ItemFactura(int cantidad, String nombre, double precio) {
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleDoubleProperty(precio);
        this.total = new SimpleDoubleProperty(cantidad * precio);
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public double getPrecio() {
        return precio.get();
    }

    public DoubleProperty precioProperty() {
        return precio;
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }
}
