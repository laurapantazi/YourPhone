package mainpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
	// JDBC driver name και database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/yourphone?useSSL=false";

	// Στοιχεία εισόδου στην βάση
	static final String USER = "root";
	static final String PASS = "12345";

	private Connection conn = null;
	private static DBHandler instance=null;
	
	private DBHandler() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		conn = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3306/yourphone?useSSL=false", USER, PASS);

	}
	
	//method openConnection() that returns the connection with the database
	//if there is no connection or the connection is closed, it creates a new one
	public static Connection openConnection() throws ClassNotFoundException, SQLException{
		if(instance==null||instance.conn.isClosed()){
			instance=new DBHandler();
		}
		return instance.conn;
	}
	
}

