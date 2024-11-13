package services;

import repository.menu.agregarPizzaRepositorio;

public class AgregarPizzaService {
    private agregarPizzaRepositorio pizzas = new agregarPizzaRepositorio();

    public int getPrecios(String bebida) {
        return pizzas.getPrecios(bebida);
    }

    public boolean estaDisponible(String bebida) {
        return pizzas.getDisponibilidad(bebida);
    }

    public int getPrecioPizza(String nombrePizza, String tamano) {
        return pizzas.getPreciosPizza(nombrePizza, tamano);
    }

    public boolean estaDisponiblePizza(String nombrePizza, String tamano) {
        return pizzas.getDisponibilidadPizza(nombrePizza, tamano);
    }
    public int getPrecioHelado(String nombreHelado) {
        return pizzas.getPrecioHelado(nombreHelado);
    }
    public boolean estaDisponibleHelado(String nombreHelado) {
        return pizzas.getDisponibilidadHelado(nombreHelado);
    }
    
}

