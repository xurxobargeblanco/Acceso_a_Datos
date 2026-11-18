package model.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectioPool {

    private static final BasicDataSource dataSource = new BasicDataSource();

    private static final String URL = "jdbc:mysql://localhost:3306/pizzeria";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "abc123.";
    private static final int INITIALSIZE = 2;
    private static final int MAXTOTAL = 10;

    private ConnectioPool(){

    }

    private static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static void close() throws SQLException{
        dataSource.close();
    }

}
