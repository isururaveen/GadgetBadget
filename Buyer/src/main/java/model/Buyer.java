package model;

import java.sql.*;
import java.sql.DriverManager;

/**
 * @author IsuruRaveen
 *
 */

public class Buyer {
		//Database Connection
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
	
		//Read Buyer Information------------------------------------------------------------------------
		public String getAllBuyers() {
			
			String output = "";
			
			try {
				
				Connection con = connect();
				
				if(con == null)
				{
					return "Unable to Connect Database for Retreive Data";
				}
				
				//Prepare the HTML table to be displayed
				output = "<table border = '1'><tr><th> Buyer ID </th>"+
						 "<th> First Name </th>"+
						 "<th> Last Name </th>"+
						 "<th> Address </th>"+
						 "<th> Email </th>"+
						 "<th> Phone No </th>"+
						 "<th> UserName </th>"+
						 "<th> Password </th>"+
						 "</tr>";
				
				String sqlQuery = "SELECT * FROM buyers";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sqlQuery);
				
				//Iterate through the rows in the result set
				while (rs.next()) 
				{
					
					String buyerID = Integer.toString(rs.getInt("buyerID"));
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String address = rs.getString("address");
					String email = rs.getString("email");
					String phoneNo = rs.getString("phoneNo");
					String userName = rs.getString("userName");
					String password = rs.getString("password");
					
					//Add into the HTML table
					output += "<tr><td>" + buyerID + "</td>";
					output += "<td>" + firstName + "</td>";
					output += "<td>" + lastName + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + phoneNo + "</td>";
					output += "<td>" + userName + "</td>";
					output += "<td>" + password + "</td>";
					
				}
				
				con.close();
				
				//Complete the HTML table
				output += "</table>";
				
			}catch(Exception ex) {
				output = "Unable to Retrieve the Buyer Details";
				System.err.println(ex.getMessage());
			}
			
			return output;
		}
}
