package services;

import controller.Reserva;

public class ReservaService {
    private static ReservaService instance;
    private Reserva reservaSeleccionada;

    // Constructor privado para el patrón Singleton
    private ReservaService() {}

    // Método para obtener la instancia única
    public static ReservaService getInstance() {
        if (instance == null) {
            instance = new ReservaService();
        }
        return instance;
    }

    // Método para establecer la reserva seleccionada
    public void setReservaSeleccionada(Reserva reserva) {
        this.reservaSeleccionada = reserva;
    }

    // Método para obtener la reserva seleccionada
    public Reserva getReservaSeleccionada() {
        return reservaSeleccionada;
    }
}
