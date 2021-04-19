package model;

import java.sql.*;

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
	public String insertResearcher(String fName, String lName, String address, String email, String phone, String uName, String pWord) 
	{
			String output = "";
			
			try 
			{
					Connection con = connect();
					if (con == null) 
					{return "Error while connecting to the database for inserting."; }

					// create a prepared statement 
					String query = " insert into researchers (`ResearcherID`,`FirstName`,`LastName`,`Address`,`Email`,`Phone`,`UserName`,`Password`)" + " values (?, ?, ?, ?, ?, ?, ?, ?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values 
					preparedStmt.setInt(1, 0); 
					preparedStmt.setString(2, fName); 
					preparedStmt.setString(3, lName);
					preparedStmt.setString(4, address);
					preparedStmt.setString(5, email);
					preparedStmt.setString(6, phone);
					preparedStmt.setString(7, uName);
					preparedStmt.setString(8, pWord); 
					
					// execute the statement 
					preparedStmt.execute(); 
					con.close();
					
					output = "Researcher inserted successfully!";
			} 
			catch (Exception e) 
			{
					output = "Error while inserting the researcher."; 
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
						{return "Error while connecting to the database for reading."; }
		
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

			
}	
