package paymentModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 

	public String addPay(String cardNO, String date, String cvv, String name, String address, String city, String postalCode, String phone, String email) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into payment(`id`, `cardNO`,`date`, `cvv`, `name`,`address`,`city`,`postalCode`,`phone`,`email`)"+" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, cardNO); 
	 preparedStmt.setString(3, date); 
	 preparedStmt.setString(4, cvv); 
	 preparedStmt.setString(5, name); 
	 preparedStmt.setString(6, address); 
	 preparedStmt.setString(7, city);
	 preparedStmt.setString(8, postalCode);
	 preparedStmt.setString(9, phone);
	 preparedStmt.setString(10, email);
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	public String getPay() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Payment ID</th><th>Card Number</th><th>Date</th><th>CVV</th><th>Name</th><th>Address</th><th>City</th><th>Postal code</th><th>Phone</th><th>E-mail</th>" +
	
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
     String id = Integer.toString(rs.getInt("id"));
	 String cardNO = rs.getString("cardNO"); 
	 String date = rs.getString("date"); 
	 String cvv = rs.getString("cvv"); 
	 String name = rs.getString("name"); 
	 String address = rs.getString("address"); 
	 String city = rs.getString("city"); 
	 String postalCode = rs.getString("postalCode"); 
	 String phone = rs.getString("phone");
	 String email = rs.getString("email");
	 // Add into the html table
	 output += "<tr><td>" + id + "</td>"; 
	 output += "<td>" + cardNO + "</td>";
	 output += "<td>" + date + "</td>"; 
	 output += "<td>" + cvv + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + address + "</td>"; 
	 output += "<td>" + city + "</td>"; 
	 output += "<td>" + postalCode + "</td>"; 
	 output += "<td>" + phone + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"+"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + id 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	public String updatePay(String id, String cardNO, String date, String cvv, String name, String address, String city, String postalCode, String phone, String email)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment SET cardNO=?, date=?, cvv=?, name=?,address=?,city=?, postalCode=?, phone=?, email=? WHERE id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, cardNO); 
		 preparedStmt.setString(2, date); 
		 preparedStmt.setString(3, cvv); 
		 preparedStmt.setString(4, name); 
		 preparedStmt.setString(5, address); 
		 preparedStmt.setString(6, city); 
		 preparedStmt.setString(7, postalCode); 
		 preparedStmt.setString(8, phone);
		 preparedStmt.setString(9, email);
		 preparedStmt.setInt(10, Integer.parseInt(id)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	public String deletePay(String id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from payment where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
}
