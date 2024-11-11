package services;

import repository.menu.agregarPizzaRepositorio;

public class AgregarPizzaService {

    private agregarPizzaRepositorio pizzas = new agregarPizzaRepositorio();

    // Método para obtener el precio de la pizza según el nombre y el tamaño
    public double getPrecioPizza(String nombre, String tamaño) {
        return pizzas.getPrecioPizza(nombre, tamaño);
    }

    // Método para verificar la disponibilidad de la pizza según el nombre y el tamaño
    public boolean estaDisponible(String nombre, String tamaño) {
        return pizzas.estaDisponible(nombre, tamaño);
    }
}
