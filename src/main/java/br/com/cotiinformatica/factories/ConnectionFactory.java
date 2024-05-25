package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:sqlserver://hackathoncoti.database.windows.net;database=arcaclouddev-26cd5c5d06a3442be89b08db1f72fcf7";
    private static final String USER = "giom";
    private static final String PASSWORD = "hackathon@2024";
    private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";


    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Falha ao carregar o driver do SQL Server", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
