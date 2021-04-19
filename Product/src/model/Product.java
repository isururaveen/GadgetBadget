package model;

import java.sql.*;

public class Product
{		//A common method to connect to the DB
	private Connection connect()
	{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/productdb", "root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return con;
	}
	
	//insert part
	public String insertProduct(String name , String category, String desc, String price)
	{
			String output = "";
			
			try
			{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
					
					// create a prepared statement
					String query = " insert into product (`productID`,`productName`,`proCategory`,`proDesc`,`proPrice`)"
							+ " values (?, ?, ?, ?, ?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, category);
					preparedStmt.setString(4,  desc);
					preparedStmt.setDouble(5, Double.parseDouble(price));
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					output = "Inserted successfully";
			}
			catch (Exception e)
			{
					output = "Error while inserting the product.";
					System.err.println(e.getMessage());
			}
			return output;
	}
	
	//read part
	public String readProduct()
	{
			String output = "";
			try
			{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for reading."; }
					
					// Prepare the html table to be displayed
					output = "<table border='1'><tr><th>Product Name</th><th>Product Category</th>" +
							"<th>Product Description</th>" +
							"<th>Product Price</th>" +
							"</tr>";
					
					String query = "select * from product";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
							String productID = Integer.toString(rs.getInt("productID"));
							String productName = rs.getString("productName");
							String proCategory = rs.getString("proCategory");
							String proDesc = rs.getString("proDesc");
							String proPrice = Double.toString(rs.getDouble("ProPrice"));
							
							// Add into the html table
							output += "<tr><td>" + productName + "</td>";
							output += "<td>" + proCategory + "</td>";
							output += "<td>" + proDesc + "</td>";
							output += "<td>" + proPrice + "</td>";
							
							
					}
					
					con.close();
					
					// Complete the html table
					output += "</table>";
					}
					
			catch (Exception e)
			{
					output = "Error while reading the product.";
					System.err.println(e.getMessage());
			}
			
			return output;
		}

	//update part
	public String updateProduct(String ID, String name, String category, String desc, String price)
	{
		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
					
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
				String query = "UPDATE product SET productName=?,procategory=?,proDesc=?,proPrice=? WHERE productID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, category );
				preparedStmt.setString(3, desc);
				preparedStmt.setDouble(4, Double.parseDouble(price));
				preparedStmt.setInt(5, Integer.parseInt(ID));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Updated successfully";
		}
		catch (Exception e)
		{
				output = "Error while updating the product.";
				System.err.println(e.getMessage());
		}
		return output;
	}
	
	//delete part
	public String deleteProduct(String productID)
	{
		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				String query = "delete from product where productID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(productID));

				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Deleted successfully";
		}
		catch (Exception e)
		{
				output = "Error while deleting the product.";
				System.err.println(e.getMessage());
		}
		return output;
	}

	}

	