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
				
				//Check DB Conenction
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
		
		//Retrieve Single Buyer Information------------------------------------------------------------------------
		public String getBuyer(String buyerID) 
		{
			
			String output = "";
			
			try 
			{
					
				Connection con = connect();
				
				if(con == null)
				{
					return "Unable to Connect Database for Retreive "+ buyerID +"";
				}
				
				//Prepare the HTML table to be displayed Single Buyer
				output = "<table border ='1'><tr><th> Buyer ID </th>"+
						 "<th> First Name </th>"+
						 "<th> Last Name </th>"+
						 "<th> Address </th>"+
						 "<th> Email </th>"+
						 "<th> Phone No </th>"+
						 "<th> User Name </th>"+
						 "<th> Password </th>";
				
				int val = Integer.parseInt(buyerID);
				
				String sqlQuery = "SELECT * FROM buyers WHERE buyerID="+val;
				Statement statement = con.createStatement();
				
				
				ResultSet rs = statement.executeQuery(sqlQuery);
				
				if(rs.next()) 
				{
					String buyID = Integer.toString(rs.getInt("buyerID"));
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String address = rs.getString("address");
					String email = rs.getString("email");
					String phoneNo = rs.getString("phoneNo");
					String userName = rs.getString("userName");
					String password = rs.getString("password");
					
					output += "<tr><td>" + buyID + "</td>";
					output += "<td>" + firstName + "</td>";
					output += "<td>" + lastName + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + phoneNo + "</td>";
					output += "<td>" + userName + "</td>";
					output += "<td>" + password + "</td>";
				}
				con.close();
				output += "</table>";
				
				return output;
				
			}catch(Exception ex) {
				output = "Unable to get "+ buyerID +"";
				ex.printStackTrace();
			}
			
			return output;
		}

		//Insert Buyer (Registration)---------------------------------------------------------------------------------
		public String insertBuyer(String firstName, String lastName, String address, String email, String phoneNo, String userName, String password) 
		{
			
			String output ="";
			
			try {
				Connection con = connect();
				if(con == null)
				{
					return "Unable to Connect Database for Inserting Data";
				}
				
				//Create a Prepared Statement
				String sqlQuery = "INSERT INTO buyers(firstName, lastName, address, email, phoneNo, userName, password)VALUES (?,?,?,?,?,?,?)";
				
				System.out.println(sqlQuery);
				
				//Create a Prepared Statement to Retrieve all UserNames
				String sqlQuery2 = new String("SELECT * FROM buyers WHERE userName=?");
			
				PreparedStatement preparedStatement = con.prepareStatement(sqlQuery2);
				preparedStatement.setString(1, userName);
				
				ResultSet rs = preparedStatement.executeQuery();
				
				if(rs.next())
				{
					return "Sorry there is already a registered user with this Username";
				}
				else 
				{
					
					PreparedStatement stmt = con.prepareStatement(sqlQuery);
					
					//Binding Values
					stmt.setString(1,firstName );
					stmt.setString(2,lastName );
					stmt.setString(3, address);
					stmt.setString(4, email);
					stmt.setString(5, phoneNo);
					stmt.setString(6, userName);
					stmt.setString(7, password);
					
					//Execute the statement
					stmt.execute();
					con.close();
					
					output= "Congratulations register success!!";
				}
				
			}catch(SQLException ex) {
				ex.printStackTrace();
				output= "Registering Failed! Try Again...";
				System.err.println("Connection Value" + ex);
			}
			return output;
		}

		//Update Buyer----------------------------------------------------------------------------------------------------
		public String UpdateBuyer(int buyerID, String firstName, String lastName, String address, String email, String phoneNo, String userName, String password)
		{
			String output ="";
			
			try {
				Connection con = connect();
				
				if(con == null)
				{
					return "Unable to Connect Database for Update Data";
				}
				
				//Create a Prepared Statement
				String sqlQuery = "UPDATE `buyers` SET `firstName`=?,`lastName`=?,`address`=?,`email`=?,`phoneNo`=?,`userName`=?,`password`=? WHERE buyerID =?";
			
				PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
				
				//Binding Values
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, address);
				preparedStatement.setString(4, email);
				preparedStatement.setString(5, phoneNo);
				preparedStatement.setString(6, userName);
				preparedStatement.setString(7, password);
				preparedStatement.setInt(8, buyerID);
				
				//Execute the statement
				preparedStatement.execute();
				con.close();
				
				output= "Buyer Successfully Updated"; 
			}
			
			catch(Exception ex) 
			{
				output = "Update Failed! : " + buyerID+"";
				ex.printStackTrace();
			}	
			return output;
		}		
}
