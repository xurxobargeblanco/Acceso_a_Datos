package Ejercicio_1;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

public class ConexionDB_1 {

    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    // 1. Crear un único pool de conexións
    private static final BasicDataSource ds = new BasicDataSource();

    // 2. Configurar o pool
    static {
        ds.setUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);

        ds.setInitialSize(2);
        ds.setMaxTotal(5);
        ds.setMaxWait(Duration.ofSeconds(3));
    }

    // 3. Obter unha conexión do pool
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    // 4. Pechar o pool ao finalizar a aplicación
    public static void close() throws SQLException {
        ds.close();
    }
}

