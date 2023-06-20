package pe.com.datos;

import pe.com.dominio.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Consulta {
    private static final String SELECT = "SELECT * FROM PERSONA";
    private static final String INSERT = "INSERT INTO PERSONA VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE PERSONA SET NOMBRE = ?, APELLIDO = ? WHERE ID = ?";
    private static final String DELETE = "DELETE FROM PERSONA WHERE ID = ?";

    public List<Persona> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> listaPersonas = new ArrayList<>();

        try {
            // DriverManager
            //conn = Conexion.abrirConexion();

            // Pool de conexiones
            conn = Conexion.abrirPoolConexion();
            stmt = conn.prepareStatement(SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                persona = new Persona();
                persona.setId(rs.getInt(1));
                persona.setNombre(rs.getString(2));
                persona.setApellido(rs.getString(3));
                listaPersonas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.cerrar(rs);
            Conexion.cerrar(stmt);
            Conexion.cerrarConexion(conn);
        }
        return listaPersonas;
    }

    public int insert(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // DriverManager
            //conn = Conexion.abrirConexion();

            // Pool de conexiones
            conn = Conexion.abrirPoolConexion();

            stmt = conn.prepareStatement(INSERT);
            stmt.setInt(1, persona.getId());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());

            rows = stmt.executeUpdate();
            System.out.println("Filas afectadas = " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.cerrar(stmt);
            Conexion.cerrarConexion(conn);
        }
        return rows;
    }

    public int update(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // DriverManager
            //conn = Conexion.abrirConexion();

            // Pool de conexiones
            conn = Conexion.abrirPoolConexion();

            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setInt(3, persona.getId());

            rows = stmt.executeUpdate();
            System.out.println("Registros afectadas = " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.cerrar(stmt);
            Conexion.cerrarConexion(conn);
        }
        return rows;
    }

    public int delete(Persona persona) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // DriverManager
            //conn = Conexion.abrirConexion();

            // Pool de conexiones
            conn = Conexion.abrirPoolConexion();

            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, persona.getId());

            rows = stmt.executeUpdate();
            System.out.println("Filas afectadas = " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.cerrar(stmt);
            Conexion.cerrarConexion(conn);
        }
        return rows;
    }
}
