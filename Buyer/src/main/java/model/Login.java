package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
						return "Invalid Logins";
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//ForgetPassword
	public String forgotPassword(String userName, String password)
	{
		String query1 = new String("SELECT * FROM buyers WHERE userName =? AND password=?");
		String query2 = new String("SELECT * FROM researchers WHERE userName=? AND password=?");
		String query3 = new String("SELECT * FROM funding WHERE userName=? AND password=?");
		
		try
		{
			
			Connection con = connect();
			
			if(con == null)
			{
				return "Unable to Connect Database for Retreive Data!";
			}
			
			PreparedStatement stmt = con.prepareStatement(query1);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				String query4 = new String("UPDATE buyers SET password=? WHERE userName=?");
				PreparedStatement stmt1 = con.prepareStatement(query4);
				
				stmt1.setString(1, password);
				stmt1.setString(2, userName);
				
				stmt1.executeUpdate();
				return "Buyer Password Changed!";
			
			}else if(!rs.next())
			{
				
				PreparedStatement stmt2 = con.prepareStatement(query2);
				stmt2.setString(1, password);
				ResultSet rs1 = stmt2.executeQuery();
				
				if(!rs1.next())
				{
					String query5 = new String("UPDATE researchers SET password=? WHERE userName=?");
					PreparedStatement stmt3 = con.prepareStatement(query5);
					
					stmt3.setString(1, password);
					stmt3.setString(2, userName);
					
					stmt3.executeUpdate();
					return "Researcher Password Changed!";
					
				}else if(!rs1.next())
				{
					PreparedStatement stmt3 = con.prepareStatement(query3);
					stmt3.setString(1, userName);
					ResultSet rs2 = stmt3.executeQuery();
					
					if(rs1.next())
					{
						
						String query6 = new String("UPDATE funding SET password=? WHERE userName=?");
						PreparedStatement stmt4 = con.prepareStatement(query6);
						
						stmt4.setString(1, password);
						stmt4.setString(2, userName);
						
						stmt4.executeUpdate();
						return "Funding Password Changed!";
					}
					else {
						return "User Name you entered doesn't exist";
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

