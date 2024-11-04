package model;

import java.time.LocalDate;

public class Reserva {
    private int idReserva;
    private int idCliente;
    private int idMesa;
    private LocalDate fechaHora;
    private String estado;

    public Reserva(int idReserva, int idCliente, int idMesa, LocalDate fechaHora, String estado) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    // Getters
    public int getIdReserva() {
        return idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
