package model.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final BasicDataSource dataSource = new BasicDataSource();

    private static final String URL = "jdbc:mysql://localhost:3306/pizzeria";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "*******"; // <--- Cambiar por la contraseña para poder conectarse.
    private static final int INITIALSIZE = 2;
    private static final int MAXTOTAL = 10;

    static {
        dataSource.setUrl(URL);
        dataSource.setUsername(USUARIO);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(INITIALSIZE);
        dataSource.setMaxTotal(MAXTOTAL);
    }

    private ConnectionPool(){}

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static void close() throws SQLException{
        dataSource.close();
    }

}
