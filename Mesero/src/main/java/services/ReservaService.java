// ReservaService.java
package services;

import entities.Reserva;

public class ReservaService {

    private static ReservaService instance;
    private Reserva reservaSeleccionada;

    private ReservaService() {}

    public static ReservaService getInstance() {
        if (instance == null) {
            instance = new ReservaService();
        }
        return instance;
    }

    public void setReservaSeleccionada(Reserva reserva) {
        this.reservaSeleccionada = reserva;
    }

    public Reserva getReservaSeleccionada() {
        return reservaSeleccionada;
    }

    public void clearReservaSeleccionada() {
        this.reservaSeleccionada = null;
    }
}
