package pkg;

import com.example.Cliente.usuarioC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ReservaRepository;
import repository.UsuarioRepository;
import services.GestionarReserva;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
    void obtenerHorarioDia_DiaExistente() {
        ReservaRepository.HorarioDia horario = reservaRepo.obtenerHorarioDia("Lunes");
        assertNotNull(horario, "El horario para el día especificado debería existir");
        assertEquals(30, horario.getIntervalo());
        assertEquals(LocalTime.of(9, 0), horario.getHoraApertura());
        assertEquals(LocalTime.of(18, 0), horario.getHoraCierre());
    }
}
