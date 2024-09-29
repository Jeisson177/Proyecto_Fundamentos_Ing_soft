package services;

import repository.ReservaRepository;
import java.util.List;

public class GestionarReserva {

    private ReservaRepository reservaRepo = new ReservaRepository();

    public boolean isFechaOcupada(String fecha) {
        return reservaRepo.FechaOcupada(fecha);
    }

    public List<String> obtenerHorariosDisponibles() {
        return reservaRepo.obtenerFechasDisponibles();
    }
}
