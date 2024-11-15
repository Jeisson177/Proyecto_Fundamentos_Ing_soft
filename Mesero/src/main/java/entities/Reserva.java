package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private int idReserva;
    private int idCliente;
    private int idMesa;
    private LocalDateTime fechaHora;

    public Reserva(int idReserva, int idCliente, int idMesa, LocalDateTime fechaHora) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.fechaHora = fechaHora;
    }

    public Reserva() {

    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getFecha() {
        return fechaHora.toLocalDate().toString();
    }

    public String getHora() {
        return fechaHora.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
