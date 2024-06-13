package accesoadatos;

import entidadesdenegocio.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class equipoDAL {

    // método que permite guardar un nuevo registro
    public static int guardar(Equipo equipo) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO equipo(NombreJugador, ApellidoJugador, NumeroCamiseta, Posicion) VALUES(?, ?, ?, ?)";
            Connection conexion = comunDB.obtenerConexion();
            PreparedStatement ps = comunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, equipo.getNombrejugador());
            ps.setString(2, equipo.getApellidojugador());
            ps.setInt(3, equipo.getNumerocamiseta());
            ps.setString(4, equipo.getPosicion());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    // método que permite modificar un registro existente
    public static int modificar(Equipo equipo) throws SQLException{
        int result = 0;
        try {
            String sql = "UPDATE equipo SET  NombreJugador = ?, ApellidoJugador = ?, NumeroCamiseta = ?, Posicion = ? WHERE Id = ?";
            Connection conexion = comunDB.obtenerConexion();
            PreparedStatement ps = comunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, equipo.getNombrejugador());
            ps.setString(2, equipo.getApellidojugador());
            ps.setInt(3, equipo.getNumerocamiseta());
            ps.setString(4, equipo.getPosicion());
            ps.setInt(5, equipo.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite eliminar un registro existente
    public static int eliminar(Equipo equipo) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Equipo WHERE Id = ?";
            Connection conexion = comunDB.obtenerConexion();
            PreparedStatement ps = comunDB.crearPreparedStatement(conexion, sql);
            ps.setInt(1, equipo.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que muestra todos los registros de la tabla
    public static ArrayList<Equipo> obtenerTodos() throws SQLException {
        ArrayList<Equipo> lista = new ArrayList<>();
        Equipo equipo;
        try{
            String sql = "SELECT Id, NombreJugador, ApellidoJugador, NumeroCamiseta, Posicion FROM Equipo";
            Connection conexion = comunDB.obtenerConexion();
            PreparedStatement ps = comunDB.crearPreparedStatement(conexion, sql);
            ResultSet rs = comunDB.obtenerResultSet(ps);
            while (rs.next()){
                equipo = new Equipo(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5));
                lista.add(equipo);
            }
            conexion.close();
            ps.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }

    // método para consultar mediante criterios
    public static ArrayList<Equipo> obtenerDatosFiltrados(Equipo equipo) throws SQLException{
        ArrayList<Equipo> lista = new ArrayList<>();
        Equipo est;
        try{
            String sql = "SELECT id, NombreJugador, ApellidoJugador, NumeroCamiseta, Posicion FROM Equipo WHERE id = ? or ApellidoJugador like'%" + equipo.getApellidojugador() + "%' or Posicion like'%" + equipo.getPosicion() + "%'";
            Connection connection = comunDB.obtenerConexion();
            PreparedStatement ps = comunDB.crearPreparedStatement(connection, sql);
            ps.setInt(1, equipo.getId());
            ResultSet rs = comunDB.obtenerResultSet(ps);
            while (rs.next()){
                est = new Equipo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                lista.add(est);
            }
            connection.close();
            ps.close();
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
