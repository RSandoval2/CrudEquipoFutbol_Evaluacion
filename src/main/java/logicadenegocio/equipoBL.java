package logicadenegocio;

import accesoadatos.equipoDAL;
import entidadesdenegocio.Equipo;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class equipoBL {
    public int guardar(Equipo equipo) throws SQLException {
        return equipoDAL.guardar(equipo);
    }

    public int modificar(Equipo equipo) throws SQLException{
        return equipoDAL.modificar(equipo);
    }

    public int eliminar(Equipo equipo) throws SQLException{
        return equipoDAL.eliminar(equipo);
    }

    public ArrayList<Equipo> obtenerTodos() throws SQLException{
        return equipoDAL.obtenerTodos();
    }

    public ArrayList<Equipo> obtenerDatosFiltrados(Equipo equipo) throws SQLException{
        return equipoDAL.obtenerDatosFiltrados(equipo);
    }
}
