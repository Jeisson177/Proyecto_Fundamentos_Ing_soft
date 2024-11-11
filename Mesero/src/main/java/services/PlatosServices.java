package services;

import controller.Plato;
import repository.menu.antipastiRepositorio;
import repository.consultarPlatosRepository;

public class PlatosServices {
    private final antipastiRepositorio antipastiRepo;
    private final consultarPlatosRepository consultarRepo;

    public PlatosServices() {
        this.antipastiRepo = new antipastiRepositorio();
        this.consultarRepo = new consultarPlatosRepository();
    }

    // Método para obtener el precio de un plato por su nombre desde el repositorio de antipasti
    public double obtenerPrecioPlatoAntipasti(String nombrePlato) {
        int precio = antipastiRepo.getPrecios(nombrePlato);
        return precio > 0 ? precio : -1;  // Devuelve -1 si no se encuentra el precio
    }

    // Método para obtener el precio de un plato por su nombre desde el repositorio general
    public double obtenerPrecioPlato(String nombrePlato) {
        double precio = consultarRepo.getPrecio(nombrePlato);
        return precio > 0 ? precio : -1;  // Devuelve -1 si no se encuentra el precio
    }

    // Método para verificar la disponibilidad de un plato por su nombre desde el repositorio de antipasti
    public boolean estaDisponibleAntipasti(String nombrePlato) {
        String disponibilidad = antipastiRepo.getDispo(nombrePlato);
        return disponibilidad != null && disponibilidad.equalsIgnoreCase("disponible");
    }

    // Método para verificar la disponibilidad de un plato por su nombre desde el repositorio general
    public boolean estaDisponible(String nombrePlato) {
        String disponibilidad = consultarRepo.getDisponibilidad(nombrePlato);
        return disponibilidad != null && disponibilidad.equalsIgnoreCase("disponible");
    }

    // Método para obtener un objeto Plato por su nombre
    public Plato obtenerPlatoPorNombre(String nombrePlato) {
        double precio = obtenerPrecioPlato(nombrePlato);
        if (precio > 0) {
            return new Plato(0, nombrePlato, precio);  // Asignamos 0 como id temporal
        }
        return null;  // Retorna null si no se encuentra el plato
    }
}