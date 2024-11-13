package services;
import Entities.Reserva;
import repository.verReservaRepository;

import java.time.LocalDate;
import java.util.List;

public class verReservaServicio {
    private verReservaRepository rep =new verReservaRepository();

    public String verReserva(String id_reserva) {
        Reserva reservaAux=rep.getReservaCompletaPorId(id_reserva);
        if(reservaAux!=null) {
            String detalles = reservaAux.toString();
        }
        return null;
    }

    public String verReservaCliente(String id_cliente) {
        List<Reserva> reservas = rep.getReservasPorIdCliente(id_cliente);
        StringBuilder detalles = new StringBuilder(); // Usamos StringBuilder para eficiencia

        for (Reserva res : reservas) {
            detalles.append(res.toString()).append("\n");
        }

        return detalles.toString();
    }

    public String verReservaFecha(LocalDate fecha) {
        List<Reserva> reservas = rep.getReservasPorFecha(fecha);
        StringBuilder detalles = new StringBuilder(); // Usamos StringBuilder para eficiencia
        if (reservas.isEmpty()){
            return null;
        }else{
            for (Reserva res : reservas) {
                detalles.append(res.toString()).append("\n");
            }
        }
        return detalles.toString();
    }
}
