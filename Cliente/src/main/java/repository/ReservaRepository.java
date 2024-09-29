package repository;

import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/`proyecto ingesoft`";

    private static final String USER = "root";
    private static final String PASSWORD = "Basedatos";

    // Método para verificar si una mesa está ocupada en una fecha y hora específicas
    public boolean FechaOcupada(LocalDate fechaConsulta, LocalTime hora, int mesa, Connection conn) {
        String query = "SELECT COUNT(*) AS total FROM reserva WHERE DATE(FECHA_HORA) = ? AND TIME(FECHA_HORA) = ? AND ID_MESA = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDate(1, Date.valueOf(fechaConsulta));  // Establecer la fecha
            stmt.setTime(2, Time.valueOf(hora));  // Establecer la hora
            stmt.setInt(3, mesa);  // Establecer la mesa

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;  // Si el total es mayor a 0, la fecha y hora están ocupadas
                }
            }
        } catch (SQLException e) {  // Especificar el tipo de excepción
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Conexión");
            alert.setHeaderText("No se pudo conectar con la base de datos");
            alert.setContentText(e.getMessage());  // Mostrar el error específico
            alert.showAndWait();
        }
        return false;
    }


}
