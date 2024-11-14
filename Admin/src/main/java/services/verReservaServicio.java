package services;
import Entities.Reserva;
import repository.verReservaRepository;

import java.time.LocalDate;
import java.util.List;

public class verReservaServicio {
    private verReservaRepository rep =new verReservaRepository();

    public Reserva verReserva(String id_reserva) {
        Reserva reservaAux=rep.getReservaCompletaPorId(id_reserva);
        if(reservaAux!=null) {
            return reservaAux;
        }
        return null;
    }

    public List <Reserva> verReservaCliente(String id_cliente) {
        List<Reserva> reservas = rep.getReservasPorIdCliente(id_cliente);

        if (reservas.isEmpty()) {
            return null;
        }else{
            return reservas;
        }
    }

    public List<Reserva> verReservaFecha(LocalDate fecha) {
        List<Reserva> reservas = rep.getReservasPorFecha(fecha);
        if (reservas.isEmpty()) {
            return null;
        }else{
            return reservas;
        }
    }

    public boolean eliminarReserva(int id_reserva){
        if(rep.eliminarReserva(id_reserva)){
            return true;
        }
        return false;
    }

}
