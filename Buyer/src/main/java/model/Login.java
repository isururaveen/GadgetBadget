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
	
	//Login
	public String login(String userName, String password)
	{
		String query1 = new String("SELECT * FROM buyers WHERE userName =? AND password=?");
		String query2 = new String("SELECT * FROM researchers WHERE userName=? AND password=?");
		String query3 = new String("SELECT * FROM funding WHERE userName=? AND password=?");
		
		try {
			Connection con = connect();
			
			if(con == null)
			{
				return "Unable to Connect Database for Retreive Data";
			}
			
			PreparedStatement stmt = con.prepareStatement(query1);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				return "Buyer Logged Successfully!";
			
			}
			else if(!rs.next())
			{
				PreparedStatement stmt1 = con.prepareStatement(query2);
				stmt1.setString(1, userName);
				stmt1.setString(2, password);
				ResultSet rs1 = stmt1.executeQuery();
				
				if(rs1.next())
				{
					return "Researcher Logged Successfully!";
				
				}
				else if(!rs1.next())
				{
					
					PreparedStatement stmt2 = con.prepareStatement(query3);
					stmt2.setString(1, userName);
					stmt2.setString(2, password);
					ResultSet rs2 = stmt2.executeQuery();
					
					if(rs2.next())
					{
						return "Funding (Investor) Logged Successfully";
					
					}
					else 
					{
						return "Invalid Login!";
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

