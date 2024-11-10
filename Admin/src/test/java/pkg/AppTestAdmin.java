package pkg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.AdminRepository;
import repository.modifificarRepository;

import javafx.geometry.Point2D;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AppTestAdmin {
    private AdminRepository adm;
    private modifificarRepository mod;
    @BeforeEach
    void setUp(){
        adm=new AdminRepository();
        mod=new modifificarRepository();
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



}
