package pkg;

import controller.AgregarOeliminarInventario;
import controller.modificarInventario;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AdminRepository;
import repository.Credenciales;
import repository.modificarInventarioRepository;
import repository.modifificarRepository;

import javafx.geometry.Point2D;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppTestAdmin {
    private AdminRepository adm;
    private modifificarRepository mod;
    private modificarInventarioRepository modRepo;
    @BeforeEach
    void setUp(){
        adm=new AdminRepository();
        mod=new modifificarRepository();
        modRepo=new modificarInventarioRepository();
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
    void testActualizarVistaAgregar() {
        // Crear instancia del controlador y llamar a setAgregarOeliminar con "Agregar"
        AgregarOeliminarInventario agregarEliminarCtrl = new AgregarOeliminarInventario();
        agregarEliminarCtrl.setAgregarOeliminar("Agregar");

        // Verificar que el texto del botón cambió a "Agregar" y los campos están habilitados
        assertEquals("Agregar", agregarEliminarCtrl.AgregarOeliminar.getText());
        assertFalse(agregarEliminarCtrl.cantidadField.isDisable(), "El campo de cantidad debería estar habilitado.");
    }

    @Test
    void testActualizarVistaEliminar() {
        // Crear instancia del controlador y llamar a setAgregarOeliminar con "Eliminar"
        AgregarOeliminarInventario agregarEliminarCtrl = new AgregarOeliminarInventario();
        agregarEliminarCtrl.setAgregarOeliminar("Eliminar");

        // Verificar que el texto del botón cambió a "Eliminar" y los campos están deshabilitados
        assertEquals("Eliminar", agregarEliminarCtrl.AgregarOeliminar.getText());
        assertTrue(agregarEliminarCtrl.cantidadField.isDisable(), "El campo de cantidad debería estar deshabilitado.");
    }






}
