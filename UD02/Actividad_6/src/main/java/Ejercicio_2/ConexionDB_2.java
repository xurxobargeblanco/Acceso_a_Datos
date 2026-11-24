
package Ejercicio_2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionDB_2 {

    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    private static final HikariConfig c = new HikariConfig();

    private static final HikariDataSource ds;

    static {
        // Configuración do pool
        c.setJdbcUrl(URL);
        c.setUsername(USER);
        c.setPassword(PASSWORD);

        c.setMaximumPoolSize(5);
        c.setConnectionTimeout(3000);

        // Creación do pool
        ds = new HikariDataSource(c);
    }

    // Obter conexión
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    // Pechar pool
    public static void close() {
        ds.close();
    }
}

