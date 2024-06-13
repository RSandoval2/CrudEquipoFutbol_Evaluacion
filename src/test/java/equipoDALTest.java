import accesoadatos.equipoDAL;
import entidadesdenegocio.Equipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;


public class equipoDALTest {
    @Test
    public void guardarTest() throws SQLException {
        Equipo student = new Equipo();
        student.setNombrejugador("Leonel");
        student.setApellidojugador("Messi");
        student.setNumerocamiseta(10);
        student.setPosicion("Delantero");

        int esperado = 1;
        int actual = equipoDAL.guardar(student);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void modificarTest() throws SQLException{
        Equipo jugador = new Equipo();
        jugador.setId(1);
        jugador.setNombrejugador("Ronaldinho");
        jugador.setApellidojugador("Gaucho");
        jugador.setNumerocamiseta(10);
        jugador.setPosicion("Extremo");

        int esperado = 1;
        int actual = equipoDAL.modificar(jugador);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void eliminarTest() throws SQLException{
        Equipo jugador = new Equipo();
        jugador.setId(2);

        int esperado = 1;
        int actual = equipoDAL.eliminar(jugador);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerTodosTest() throws SQLException{
        int actual = equipoDAL.obtenerTodos().size();
        Assertions.assertNotEquals(0, actual);
    }

    @Test
    public void obtenerDatosFiltradosTest() throws SQLException{
        Equipo jugador = new Equipo();
        jugador.setPosicion("Delantero");
        jugador.setId(0);
        jugador.setApellidojugador("");

        int actual = equipoDAL.obtenerDatosFiltrados(jugador).size();
        Assertions.assertNotEquals(0, actual);
    }
}
