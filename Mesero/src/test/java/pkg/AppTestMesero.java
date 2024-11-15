package pkg;

import entities.ItemFactura;
import entities.Plato;
import entities.PlatoCarrito;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Carrito;
import controller.FacturaControlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class AppTestMesero {
    private Carrito carrito;
    private FacturaControlador facturaControlador;

    @BeforeAll
    public static void initJavaFX() {

        Platform.startup(() -> {});
    }

    @BeforeEach
    public void setUp() {
        carrito = Carrito.getInstance();
        carrito.vaciarCarrito();
        facturaControlador = new FacturaControlador();

        facturaControlador.fechaFactura = new Text("Fecha: 2023-10-10");
        facturaControlador.horaFactura = new Text("Hora: 12:00");
        facturaControlador.mesaFactura = new Text("Mesa: 5");
        facturaControlador.totalBase = new Text("35000 COP");
        facturaControlador.servicioAdicional = new Text("2800 COP");
        facturaControlador.totalConServicio = new Text("37800 COP");

        facturaControlador.tablaFactura = new TableView<>();
        facturaControlador.tablaFactura.setItems(FXCollections.observableArrayList(
                new ItemFactura(2, "Spaghetti Carbonara", 15000.0),
                new ItemFactura(1, "Pizza Margarita", 20000.0)
        ));
    }

    @Test
    public void testAgregarPlatoNuevo() {
        Plato plato = new Plato(1, "Spaghetti Carbonara", 15000.0);
        carrito.agregarPlato(plato);

        assertEquals(1, carrito.obtenerPlatosEnCarrito().size());
        assertEquals(plato.getNombre(), carrito.obtenerPlatosEnCarrito().get(0).getPlato().getNombre());
        assertEquals(1, carrito.obtenerPlatosEnCarrito().get(0).getCantidad());
    }

    @Test
    public void testAgregarPlatoExistente() {
        Plato plato = new Plato(1, "Spaghetti Carbonara", 15000.0);
        carrito.agregarPlato(plato);
        carrito.agregarPlato(plato);

        assertEquals(1, carrito.obtenerPlatosEnCarrito().size());
        assertEquals(2, carrito.obtenerPlatosEnCarrito().get(0).getCantidad());
    }

    @Test
    public void testEliminarPlato() {
        Plato plato = new Plato(1, "Spaghetti Carbonara", 15000.0);
        carrito.agregarPlato(plato);

        carrito.eliminarPlato(plato);

        assertTrue(carrito.obtenerPlatosEnCarrito().isEmpty());
    }

    @Test
    public void testCalcularTotalCarrito() {
        Plato plato1 = new Plato(1, "Spaghetti Carbonara", 15000.0);
        Plato plato2 = new Plato(2, "Pizza Margarita", 20000.0);

        carrito.agregarPlato(plato1);
        carrito.agregarPlato(plato2);

        double totalEsperado = plato1.getPrecio() + plato2.getPrecio();
        assertEquals(totalEsperado, carrito.calcularTotalCarrito());
    }

    @Test
    public void testActualizarCantidadPlato() {
        Plato plato = new Plato(1, "Spaghetti Carbonara", 15000.0);
        carrito.agregarPlato(plato);
        carrito.agregarPlato(plato);

        PlatoCarrito platoEnCarrito = carrito.obtenerPlatosEnCarrito().get(0);
        platoEnCarrito.setCantidad(5);

        assertEquals(5, platoEnCarrito.getCantidad());
        assertEquals(5 * plato.getPrecio(), platoEnCarrito.getCostoTotal());
    }

    @Test
    public void testCrearArchivoFactura() throws IOException, InterruptedException {
        Plato plato1 = new Plato(1, "Spaghetti Carbonara", 15000.0);
        Plato plato2 = new Plato(2, "Pizza Margarita", 20000.0);
        carrito.agregarPlato(plato1);
        carrito.agregarPlato(plato2);

        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            facturaControlador.imprimirFactura();
            latch.countDown();
        });

        latch.await();

        Path facturaDir = Paths.get("facturas");
        assertTrue(Files.exists(facturaDir), "El directorio de facturas no existe");

        File[] archivos = facturaDir.toFile().listFiles((dir, name) -> name.startsWith("Factura_") && name.endsWith(".txt"));
        assertNotNull(archivos, "No se encontró ningún archivo de factura");
        assertTrue(archivos.length > 0, "No se encontró ningún archivo de factura");

        File archivoFactura = archivos[archivos.length - 1];
        assertTrue(archivoFactura.exists(), "El archivo de factura no fue creado");

        List<String> contenidoFactura = Files.readAllLines(archivoFactura.toPath());
        assertTrue(contenidoFactura.stream().anyMatch(line -> line.contains("Spaghetti Carbonara")), "No se encontró el plato Spaghetti Carbonara en la factura");
        assertTrue(contenidoFactura.stream().anyMatch(line -> line.contains("Pizza Margarita")), "No se encontró el plato Pizza Margarita en la factura");
        assertTrue(contenidoFactura.stream().anyMatch(line -> line.contains("TOTAL: 37800 COP")), "El total calculado no coincide en la factura");

        assertTrue(archivoFactura.delete(), "No se pudo eliminar el archivo de prueba de factura");
    }
}
