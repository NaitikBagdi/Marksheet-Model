package Java.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {
		Connection con = null;
		try {
			//Load Driver...
			
			Class.forName("com.mysql.jdbc.Driver");
			//Connection Established 
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlworkspace","root","root");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
