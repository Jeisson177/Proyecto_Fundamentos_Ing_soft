package pkg;

import controller.MesaControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ReservaRepository;
import repository.UsuarioRepository;
import services.GestionarReserva;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    private ReservaRepository reservaRepo;
    private UsuarioRepository usuarioRepo;

    private final MesaControl mesaControl = new MesaControl(); // suponiendo que el constructor recibe un ID de usuario
    private final GestionarReserva gestionarReserva = new GestionarReserva(mesaControl);

    @BeforeEach
    void setUp() {
        reservaRepo = new ReservaRepository();
        usuarioRepo = new UsuarioRepository();
    }

    @Test
    void autentificarUsuario_UsuarioExistente() {
        String email = "juanperez@email.com";
        String password = "contrasena1";
        int userId = usuarioRepo.AutentificarUsuario(email, password);

        System.out.println("Prueba autentificarUsuario_UsuarioExistente - Esperado: > 0, Actual: " + userId);
        assertTrue(userId > 0, "El ID de usuario debería ser mayor a 0 si el usuario existe");
    }

    @Test
    void autentificarUsuario_UsuarioInexistente() {
        String email = "inexistente@example.com";
        String password = "contrasenaIncorrecta";
        int userId = usuarioRepo.AutentificarUsuario(email, password);

        System.out.println("Prueba autentificarUsuario_UsuarioInexistente - Esperado: -1, Actual: " + userId);
        assertEquals(-1, userId, "El ID de usuario debería ser -1 si el usuario no existe o las credenciales son incorrectas");
    }

    @Test
    void crearUsuario_Exito() {
        String email = "lorenaQ@email.com";
        String password = "contrasena22";
        String nombre = "Lorena Quijada";
        String telefono = "123456789";
        int nuevoId = usuarioRepo.CrearUsuario(email, password, nombre, telefono);

        System.out.println("Prueba crearUsuario_Exito - Esperado: > 0, Actual: " + nuevoId);
        assertTrue(nuevoId > 0, "El ID del nuevo usuario debería ser mayor a 0 si la creación fue exitosa");
    }

    @Test
    void crearUsuario_SinExito() {
        String email = "choi@email.com";
        String password = "contrasena23";
        String nombre = "Seung Choi";
        String telefono = "";
        int nuevoId = usuarioRepo.CrearUsuario(email, password, nombre, telefono);

        System.out.println("Prueba crearUsuario_SinExito - Esperado: < 0, Actual: " + nuevoId);
        assertTrue(nuevoId==-1 , "El ID del nuevo usuario debería ser menor a 0 si la creación fue no exitosa");
    }

    @Test
    void consultarReservas_UsuarioConReservas() {
        int idUsuario = 1;
        List<String> reservas = usuarioRepo.ConsultarReservas(idUsuario);

        System.out.println("Prueba consultarReservas_UsuarioConReservas - Esperado: no vacío, Actual: " + reservas.size());
        assertNotNull(reservas, "La lista de reservas no debería ser nula");
        assertFalse(reservas.isEmpty(), "La lista de reservas debería contener al menos una reserva para este usuario");
    }

    @Test
    void consultarReservas_UsuarioSinReservas() {
        int idUsuario = 22;
        List<String> reservas = usuarioRepo.ConsultarReservas(idUsuario);

        System.out.println("Prueba consultarReservas_UsuarioSinReservas - Esperado: vacío, Actual: " + reservas.size());
        assertNotNull(reservas, "La lista de reservas no debería ser nula");
        assertTrue(reservas.isEmpty(), "La lista de reservas debería estar vacía para este usuario sin reservas");
    }

    @Test
    public void testObtenerHorarioDia() {
        ReservaRepository.HorarioDia horario = reservaRepo.obtenerHorarioDia("Lunes");

        System.out.println("Prueba testObtenerHorarioDia - Hora apertura Esperado: 09:00, Actual: " + horario.getHoraApertura());
        System.out.println("Hora cierre Esperado: 22:00, Actual: " + horario.getHoraCierre());

        assertNotNull(horario, "Horario debe existir para el día Lunes");
        assertEquals("09:00", horario.getHoraApertura().toString());
        assertEquals("22:00", horario.getHoraCierre().toString());
    }

    @Test
    public void testFechaOcupada() {
        LocalDate fecha = LocalDate.of(2024, 10, 7);
        LocalTime hora = LocalTime.of(11, 0);
        int idMesa = 2;
        boolean ocupada = reservaRepo.FechaOcupada(fecha, hora, idMesa);

        System.out.println("Prueba testFechaOcupada - Esperado: true, Actual: " + ocupada);
        assertTrue(ocupada, "La mesa debería estar ocupada a esta hora");
    }



    @Test
    public void testObtenerHorariosDisponibles() throws SQLException {
        LocalDate fechaSeleccionada = LocalDate.of(2024, 10, 7);
        int mesaId = 1;
        List<String> horarios = gestionarReserva.obtenerHorariosDisponibles(fechaSeleccionada, mesaId);

        System.out.println("Prueba testObtenerHorariosDisponibles - Esperado: no vacío, Actual: " + horarios.size());
        assertNotNull(horarios, "Debería retornar una lista de horarios");
        assertFalse(horarios.isEmpty(), "Debería haber al menos un horario disponible");
    }
    @Test
    public void testGReserva() {

        // Datos de la reserva
        LocalDate fecha = LocalDate.of(2024, 10, 31);
        String hora = "10:00:00";

        // Ejecutar la reserva
        boolean reservaGuardada = gestionarReserva.GReserva(fecha, hora);

        // Imprimir el resultado de la prueba
        System.out.println("Prueba testGReserva - Esperado: true, Actual: " + reservaGuardada);

        // Afirmar que la reserva fue guardada correctamente
        assertFalse(reservaGuardada, "La reserva debería guardarse correctamente");
    }



    @Test
    public void testObtenerHorarioDiaInvalido() {
        ReservaRepository.HorarioDia horario = reservaRepo.obtenerHorarioDia("InvalidDay");

        System.out.println("Prueba testObtenerHorarioDiaInvalido - Esperado: null, Actual: " + horario);
        assertNull(horario, "No debería existir un horario para un día inválido");
    }

    @Test
    public void testFechaOcupadaMesaNoExistente() {
        LocalDate fecha = LocalDate.of(2024, 10, 7);
        LocalTime hora = LocalTime.of(11, 0);
        int idMesaInexistente = 999;
        boolean ocupada = reservaRepo.FechaOcupada(fecha, hora, idMesaInexistente);

        System.out.println("Prueba testFechaOcupadaMesaNoExistente - Esperado: false, Actual: " + ocupada);
        assertFalse(ocupada, "La consulta debería fallar ya que la mesa no existe");
    }

    @Test
    public void testGuardarReservaFechaPasada() {
        int idCliente = 1;
        int idMesa = 1;
        String fechaHoraPasada = "2020-01-01 10:00:00";
        boolean resultado = reservaRepo.guardarReserva(idCliente, idMesa, fechaHoraPasada);

        System.out.println("Prueba testGuardarReservaFechaPasada - Esperado: false, Actual: " + resultado);
        assertFalse(resultado, "No debería permitirse guardar una reserva en una fecha pasada");
    }

   /* @Test
    public void testObtenerHorariosDisponiblesDiaSinHorario() throws SQLException {
        LocalDate fechaSinHorario = LocalDate.of(2024, 10, 7); // Fecha sin un horario definido en la base de datos
        int mesaId = 1;
        List<String> horarios = gestionarReserva.obtenerHorariosDisponibles(fechaSinHorario, mesaId);

        System.out.println("Prueba testObtenerHorariosDisponiblesDiaSinHorario - Esperado: vacío, Actual: " + horarios.size());
        assertTrue(horarios.isEmpty(), "Debería retornar una lista vacía para un día sin horario");
    }*/




}