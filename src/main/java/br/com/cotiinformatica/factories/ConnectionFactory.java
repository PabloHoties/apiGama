package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";	
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=hackathoncoti.database.windows.net";
	private static String user = "giom";
	private static String password = "hackathon@2024";

	public static Connection getConnection() throws Exception {
		Class.forName(driver);
		
		return DriverManager.getConnection(url, user, password);
	}
}