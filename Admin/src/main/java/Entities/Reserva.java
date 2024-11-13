package Entities;

import java.time.LocalDateTime;

public class Reserva {
    private int id_Reserva;
    private int id_Cliente;
    private LocalDateTime fechaReserva;
    private int Id_mesa;

    public Reserva(int idReserva, int idCliente, int idMesa, LocalDateTime fechaHora) {
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public int getId_mesa() {
        return Id_mesa;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }
    public int getId_Reserva() {
        return id_Reserva;
    }
    public void setId_Reserva(int id_Reserva) {
        this.id_Reserva = id_Reserva;
    }
    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }
    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    public void setId_mesa(int Id_mesa) {
        this.Id_mesa = Id_mesa;
    }

}
