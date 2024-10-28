package pkg;

import com.example.Cliente.usuarioC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ReservaRepository;
import repository.UsuarioRepository;
import services.GestionarReserva;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    private ReservaRepository reservaRepo;
    private UsuarioRepository usuarioRepo;
    private GestionarReserva gestionarReserva;

    @BeforeEach
    void setUp() {
        reservaRepo = new ReservaRepository();
        usuarioRepo = new UsuarioRepository();
        //gestionarReserva = new GestionarReserva(new com.example.Cliente.MesaControl());
    }

    @Test
    void autentificarUsuario_UsuarioExistente() {
        // Configuración de los datos de prueba
        String email = "juanperez@email.com";
        String password = "contrasena1";

        // Ejecución de la prueba
        int userId = usuarioRepo.AutentificarUsuario(email, password);

        // Verificación del resultado
        assertTrue(userId > 0, "El ID de usuario debería ser mayor a 0 si el usuario existe");
    }

    @Test
    void autentificarUsuario_UsuarioInexistente() {
        // Datos de prueba que no existen en la base de datos
        String email = "inexistente@example.com";
        String password = "contrasenaIncorrecta";

        // Ejecución de la prueba
        int userId = usuarioRepo.AutentificarUsuario(email, password);

        // Verificación de que el usuario no fue encontrado
        assertEquals(-1, userId, "El ID de usuario debería ser -1 si el usuario no existe o las credenciales son incorrectas");
    }

    @Test
    void crearUsuario_Exito() {
        // Configuración de los datos para crear un usuario
        String email = "lorenaQ@email.com";
        String password = "contrasena22";
        String nombre = "Lorena Quijada";
        String telefono = "123456789";

        // Ejecución de la prueba
        int nuevoId = usuarioRepo.CrearUsuario(email, password, nombre, telefono);

        // Verificación del resultado
        assertTrue(nuevoId > 0, "El ID del nuevo usuario debería ser mayor a 0 si la creación fue exitosa");
    }

    @Test
    void crearUsuario_SinExito() {
        // Configuración de los datos para crear un usuario
        String email = "choi@email.com";
        String password = "contrasena23";
        String nombre = " Seung Choi";
        String telefono = "";

        // Ejecución de la prueba
        int nuevoId = usuarioRepo.CrearUsuario(email, password, nombre, telefono);

        // Verificación del resultado
        assertTrue(nuevoId < 0, "El ID del nuevo usuario debería ser menor a 0 si la creación fue no exitosa");
    }

    @Test
    void consultarReservas_UsuarioConReservas() {
        // Suponiendo que el ID 1 pertenece a un usuario con reservas en la base de datos
        int idUsuario = 1;  //Juan Perez

        // Ejecución de la prueba
        List<String> reservas = usuarioRepo.ConsultarReservas(idUsuario);

        // Verificación del resultado
        assertNotNull(reservas, "La lista de reservas no debería ser nula");
        assertFalse(reservas.isEmpty(), "La lista de reservas debería contener al menos una reserva para este usuario");
    }

    @Test
    void consultarReservas_UsuarioSinReservas() {
        //ID 22 pertenece a un usuario sin reservas en la base de datos
        int idUsuario = 22;  //Lorena Q

        // Ejecución de la prueba
        List<String> reservas = usuarioRepo.ConsultarReservas(idUsuario);

        // Verificación del resultado
        assertNotNull(reservas, "La lista de reservas no debería ser nula");
        assertTrue(reservas.isEmpty(), "La lista de reservas debería estar vacía para este usuario sin reservas");
    }
}
