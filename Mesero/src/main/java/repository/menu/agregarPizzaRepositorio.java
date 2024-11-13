package repository.menu;

import repository.Credenciales;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class agregarPizzaRepositorio {
    private static final Credenciales c = new Credenciales();
    private static final String URL = c.getURL();
    private static final String USER = c.getUser();
    private static final String PASSWORD = c.getPassword();
    private static final Map<String, String> pizzaNameMap = new HashMap<>();
    static {
        pizzaNameMap.put("4quesos", "Cuatro Quesos");
        pizzaNameMap.put("margarita", "Margarita");
        pizzaNameMap.put("pepperoni", "Pepperoni");
        pizzaNameMap.put("napolitana", "Napolitana");
    }

    public int getPrecios(String nombrePizza) {
        String query = "SELECT PRECIO FROM PLATO WHERE nombre = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombrePizza);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("PRECIO");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public boolean getDisponibilidad(String nombreBebida) {
        String query = """
            SELECT CASE 
                   WHEN MIN(ii.CANTIDAD_INV) >= MAX(ip.Cantidad) THEN 'Disponible' 
                   ELSE 'No disponible' 
                   END AS Dispo 
            FROM PLATO p 
            JOIN INGREDIENTE_PLATO ip ON p.ID_PLATO = ip.ID_PLATO 
            JOIN INGREDIENTE_INV ii ON ip.ID_INGREDIENTE = ii.ID_INGREDIENTE 
            WHERE p.NOMBRE = ? 
            GROUP BY p.NOMBRE;
        """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombreBebida);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return "Disponible".equalsIgnoreCase(rs.getString("Dispo"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar la disponibilidad de la bebida: " + e.getMessage(), e);
        }
        return false;
    }

    public int getPreciosPizza(String nombrePizza, String tamano) {
        // Evitar duplicar la palabra "Pizza" en el nombre completo
        String nombreCompleto = nombrePizza + " " + tamano;

        String query = "SELECT PRECIO FROM PLATO WHERE NOMBRE = ?";
        System.out.println("Consultando precio para: '" + nombreCompleto + "'");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreCompleto.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int precio = rs.getInt("PRECIO");
                    System.out.println("Precio encontrado: " + precio);
                    return precio;
                } else {
                    System.out.println("No se encontró el precio para: '" + nombreCompleto + "'");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el precio de la pizza: " + e.getMessage(), e);
        }
        return -1;
    }

    public boolean getDisponibilidadPizza(String nombrePizza, String tamano) {
        String nombreCompleto = "Pizza " + nombrePizza + " " + tamano;

        String query = """
        SELECT CASE 
               WHEN MIN(ii.CANTIDAD_INV) >= MAX(ip.Cantidad) THEN 'Disponible' 
               ELSE 'No disponible' 
               END AS Dispo 
        FROM PLATO p 
        JOIN INGREDIENTE_PLATO ip ON p.ID_PLATO = ip.ID_PLATO 
        JOIN INGREDIENTE_INV ii ON ip.ID_INGREDIENTE = ii.ID_INGREDIENTE 
        WHERE p.NOMBRE = ? 
        GROUP BY p.NOMBRE;
    """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nombreCompleto);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return "Disponible".equalsIgnoreCase(rs.getString("Dispo"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar la disponibilidad de la pizza: " + e.getMessage(), e);
        }
        return false;
    }

    public int getPrecioHelado(String nombreHelado) {
        String query = "SELECT PRECIO FROM PLATO WHERE nombre = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreHelado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("PRECIO");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el precio del helado: " + e.getMessage(), e);
        }
        return -1;
    }

    public boolean getDisponibilidadHelado(String nombreHelado) {
        String query = """
            SELECT CASE 
                   WHEN MIN(ii.CANTIDAD_INV) >= MAX(ip.Cantidad) THEN 'Disponible' 
                   ELSE 'No disponible' 
                   END AS Dispo 
            FROM PLATO p 
            JOIN INGREDIENTE_PLATO ip ON p.ID_PLATO = ip.ID_PLATO 
            JOIN INGREDIENTE_INV ii ON ip.ID_INGREDIENTE = ii.ID_INGREDIENTE 
            WHERE p.NOMBRE = ? 
            GROUP BY p.NOMBRE;
        """;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreHelado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "Disponible".equalsIgnoreCase(rs.getString("Dispo"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar la disponibilidad del helado: " + e.getMessage(), e);
        }
        return false;
    }
}
