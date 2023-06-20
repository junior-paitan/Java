package pe.com.datos;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class Conexion {
    public static final String CADENA_CONEXION = "jdbc:oracle:thin:@localhost:1521:xe";
    public static final String USER = "ORACLE";
    public static final String PASS = "ORACLE";

    public static DataSource configurarDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(CADENA_CONEXION);
        ds.setUsername(USER);
        ds.setPassword(PASS);
        // Definimos el tamanio inicial del pool de conexiones
        ds.setInitialSize(5);
        return ds;
    }

    public static Connection abrirPoolConexion() throws SQLException {
        return configurarDataSource().getConnection();
    }

    public static Connection abrirConexion() throws SQLException {
        return DriverManager.getConnection(CADENA_CONEXION,USER,PASS);
    }

    public static void cerrar(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void cerrar(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void cerrarConexion(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
