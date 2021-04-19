package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author IsuruRaveen
 *
 */

public class Login {
	
	//DB Connection
	private Connection connect() {
		
		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/paf_lab";
		String userName ="root";
		String password ="";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url,userName,password);
			
		}catch(Exception ex){
			
			System.out.println("unable to Connect to the Database");
			ex.printStackTrace();
		}
		
		return con;
	}
	
}

