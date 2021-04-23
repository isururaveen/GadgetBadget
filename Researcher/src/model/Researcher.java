package model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Researcher {
	//<------method to connect to the DB------> 
	private Connection connect() 
	{
			Connection con = null;
			
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher", "root", "");
			} 			
			catch (Exception e) 
			{
				e.printStackTrace();
			}	
			return con; 
	}
	
	
	//<-----Creating insert part----->
	public String insertResearcher(String FirstName, String LastName, String Address, String Email, String Phone, String UserName, String Password) 
	{
		String output ="";
		
		try {
				Connection con = connect();
				
				if(con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
			
				//Create a Prepared Statement
				String query = "insert into researchers(`ResearcherID`,`FirstName`,`LastName`,`Address`,`Email`,`Phone`,`UserName`,`Password`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			
				//Prepared statement to retrieve all userNames
				String query1 = new String("select * from researchers where UserName=?");
				
				PreparedStatement preparedStatement = con.prepareStatement(query1);
				preparedStatement.setString(1, UserName);
			
				ResultSet rs = preparedStatement.executeQuery();
			
				if(rs.next())
				{
					return "Sorry there is already a registered researcher with this username?";
				}
				else 
				{
					PreparedStatement preparedStmt = con.prepareStatement(query);
				
					//binding Values
					preparedStmt.setInt(1, 0); 
					preparedStmt.setString(2,FirstName );
					preparedStmt.setString(3,LastName );
					preparedStmt.setString(4, Address);
					preparedStmt.setString(5, Email);
					preparedStmt.setString(6, Phone);
					preparedStmt.setString(7, UserName);
					preparedStmt.setString(8, Password);
				
					//execute the statement
					preparedStmt.execute();
					con.close();
				
					output= "Registered successfully!";
				}
			}	
			catch(Exception e) 
			{
				output= "Error while registering the researcher!";
				System.err.println(e.getMessage());
			}
			return output;
	}
	
	
		//<-----Creating read part----->
		public String readResearchers() 
		{
				String output = "";
		
				try 
				{
						Connection con = connect();
		
						if (con == null) 
						{
							return "Error while connecting to the database for reading."; 
						}
		
						// Prepare the html table to be displayed 
						output = "<table border='1'><tr><th>First Name</th><th>Last Name</th>" + 
								 "<th>Address</th>" + 
								 "<th>Email</th>" + 
								 "<th>Phone Number</th><th>User Name</th>" + 
								 "<th>Password</th></tr>";
		
						String query = "select * from researchers"; 
						Statement stmt = con.createStatement(); 
						ResultSet rs = stmt.executeQuery(query);
		
						// iterate through the rows in the result set 
						while (rs.next()) 
						{
								String ResearcherID = Integer.toString(rs.getInt("ResearcherID")); 
								String FirstName = rs.getString("FirstName"); 
								String LastName = rs.getString("LastName"); 
								String Address = rs.getString("Address");  
								String Email = rs.getString("Email");
								String Phone = rs.getString("Phone");
								String UserName = rs.getString("UserName");
								String Password = rs.getString("Password");
								
								// Add into the html table 
								output += "<tr><td>" + FirstName + "</td>"; 
								output += "<td>" + LastName + "</td>"; 
								output += "<td>" + Address + "</td>"; 
								output += "<td>" + Email + "</td>";
								output += "<td>" + Phone + "</td>";
								output += "<td>" + UserName + "</td>";
								output += "<td>" + Password + "</td>";
						 } 
						 
						 con.close();
						 
						 // Complete the html table 
						 output += "</table>";
				} 
				catch (Exception e) 
				{
						output = "Error while reading the researchers."; 
						System.err.println(e.getMessage());
				} 
				return output;
		}
		
		
		//<-----Creating update part----->
		public String updateResearcher(String RID, String fName, String lName, String address, String email, String phone, String uName, String pWord)
		{
			String output = "";
			
			try 
			{
					Connection con = connect();
			
					if (con == null) 
					{
						return "Error while connecting to the database for updating."; 
					}
			
					// create a prepared statement 
					String query = "UPDATE researchers SET FirstName=?,LastName=?,Address=?,Email=?,Phone=?,UserName=?,Password=? WHERE ResearcherID=?";
			
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values 
					preparedStmt.setString(1, fName); 
					preparedStmt.setString(2, lName); 
					preparedStmt.setString(3, address); 
					preparedStmt.setString(4, email);
					preparedStmt.setString(5, phone);
					preparedStmt.setString(6, uName);
					preparedStmt.setString(7, pWord);
					preparedStmt.setInt(8, Integer.parseInt(RID));
			
					// execute the statement 
					preparedStmt.execute(); 
					con.close();
					
					output = "Updated successfully!";
			} 
			catch (Exception e) 
			{
					output = "Error while updating the researcher."; 
					System.err.println(e.getMessage());
			} 
			return output;
		}

		
		//<-----Creating delete part----->
		public String deleteResearcher(String ResearcherID)
		{
			String output = "";
			
				try {
						Connection con = connect();
					
					if (con == null) 
					{
						return "Error while connecting to the database for deleting."; 
					}
					
					// create a prepared statement 
					String query = "delete from researchers where ResearcherID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values 
					preparedStmt.setInt(1, Integer.parseInt(ResearcherID));
			
					// execute the statement 
					preparedStmt.execute(); 
					con.close();
					
					output = "Deleted successfully!";
				} 
				catch (Exception e) 
				{
					output = "Error while deleting the researcher."; 
					System.err.println(e.getMessage());
				} 
				return output;
		}		
}	
