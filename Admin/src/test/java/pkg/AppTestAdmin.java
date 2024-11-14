package pkg;

import controller.modificarInventario;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AdminRepository;
import repository.Credenciales;
import repository.modificarInventarioRepository;
import repository.modifificarRepository;
import repository.verReservaRepository;

import javafx.geometry.Point2D;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AppTestAdmin {
    private AdminRepository adm;
    private modifificarRepository mod;
    private modificarInventarioRepository modRepo;
    private verReservaRepository verReser;

    @BeforeEach
    void setUp(){
        adm=new AdminRepository();
        mod=new modifificarRepository();
        modRepo=new modificarInventarioRepository();
        verReser=new verReservaRepository();
    }
    @Test
    void AutentficarAdmin(){
        String Usuario = "hola";
        String Contrsena="adios";
        boolean estado;
        estado=adm.AutentificatAdmin(Usuario,Contrsena);
        assertFalse(estado,"No debe aceptar usuarios nulos o que no correspondan");
        Usuario="estebanxd@email.com";
        Contrsena="admin";
        estado=adm.AutentificatAdmin(Usuario,Contrsena);
        assertTrue(estado,"Ya debe salir bien la consulta");
    }
    @Test
    void testObtenerPosicionMesa() throws SQLException {
        // Simular la posición esperada
        Point2D posicionEsperada = new Point2D(318.0, 137.0);
        int idMesaP = 1;

        // Llamar al metodo que se está probando
        Point2D posicionObtenida = mod.obtenerPosicionMesa(idMesaP);

        // Verificar resultados comparando las coordenadas
        assertEquals(posicionEsperada.getX(), posicionObtenida.getX(), 0.001);
        assertEquals(posicionEsperada.getY(), posicionObtenida.getY(), 0.001);
    }

    @Test
    void testAgregarIngrediente() {
        String agregarI = "Avellana";
        int cant = 100;
        // Crear un ingrediente de prueba
        modificarInventarioRepository.InventarioAlimento ingrediente =
                new modificarInventarioRepository.InventarioAlimento(agregarI, cant, new java.sql.Date(System.currentTimeMillis()), "g");

        // Agregar el ingrediente al inventario
        modRepo.agregarIngrediente(ingrediente);

        // Verificar que el ingrediente ahora se encuentra en la lista
        List<modificarInventarioRepository.InventarioAlimento> inventario = modRepo.obtenerTodosLosInventarios();
        boolean encontrado = inventario.stream().anyMatch(i -> i.getNombre().equals(agregarI) && i.getCantidadInv() == cant);

        assertTrue(encontrado, "El ingrediente debería estar en el inventario después de agregarlo.");
    }

    @Test
    void testEliminarIngrediente() {
        String eliminarI = "Avellana";
        int cant = 100;
        // Primero agregamos el ingrediente para asegurarnos de que esté en la base de datos
        modificarInventarioRepository.InventarioAlimento ingrediente =
                new modificarInventarioRepository.InventarioAlimento(eliminarI, cant, new java.sql.Date(System.currentTimeMillis()), "g");
        modRepo.agregarIngrediente(ingrediente);

        // Luego eliminamos el ingrediente
        modRepo.eliminarIngrediente(eliminarI);

        // Verificamos que el ingrediente ya no esté en la lista de inventario
        List<modificarInventarioRepository.InventarioAlimento> inventario = modRepo.obtenerTodosLosInventarios();
        boolean encontrado = inventario.stream().anyMatch(i -> i.getNombre().equals(eliminarI));

        assertFalse(encontrado, "El ingrediente debería ser eliminado del inventario.");
    }

    @Test
    void testValidarCantidadIngredientesDespuesAgregar() {
        String agregarI = "Avellana";
        int cant = 100;
        int cantidadAntes = modRepo.obtenerTodosLosInventarios().size();
        modificarInventarioRepository.InventarioAlimento ingrediente =
                new modificarInventarioRepository.InventarioAlimento(agregarI, cant, new Date(), "g");
        modRepo.agregarIngrediente(ingrediente);
        int cantidadDespues = modRepo.obtenerTodosLosInventarios().size();
        assertEquals(cantidadAntes + 1, cantidadDespues, "La cantidad debe incrementarse en 1 tras agregar un ingrediente.");
    }

    @Test
    void testEliminarIngredienteConMultiplesRegistros() {
        String agregar1 = "Avellana";
        int cant = 10;
        String agregar2 = "Avellana";
        int cant2 = 5;
        modificarInventarioRepository.InventarioAlimento ingrediente1 =
                new modificarInventarioRepository.InventarioAlimento(agregar1, cant, new Date(), "g");
        modificarInventarioRepository.InventarioAlimento ingrediente2 =
                new modificarInventarioRepository.InventarioAlimento(agregar2, cant2, new Date(), "g");
        modRepo.agregarIngrediente(ingrediente1);
        modRepo.agregarIngrediente(ingrediente2);
        modRepo.eliminarIngrediente("Avellana");
        List<modificarInventarioRepository.InventarioAlimento> ingredientes = modRepo.obtenerTodosLosInventarios();
        assertFalse(ingredientes.stream().anyMatch(i -> i.getNombre().equals("Avellana")),
                "Todos los registros del ingrediente deben ser eliminados.");
    }

    @Test
    void testEliminarReserva(){
        assertEquals(1, verReser.getReservasPorIdCliente("1").size());
        verReser.eliminarReserva(1);
        assertEquals(0, verReser.getReservasPorIdCliente("1").size(),
        "Se elimino correctamente");
    }
}
