package model;

import java.sql.*;

public class Fund {

		//method to connect to the DB 
		private Connection connect() 
		{
				Connection con = null;
				
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					//Provide the correct details: DBServer/DBName, username, password 
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund", "root", "");
				} 			
				catch (Exception e) 
				{
					e.printStackTrace();
				}
					
				return con; 
		}
		

		//Creating insert part
		public String insertFund(String fRecipient, String comName, String timeDuration, String purpose, String donaAmount) 
		{
				String output = "";
				
				try 
				{
						Connection con = connect();
						if (con == null) 
						{return "Error while connecting to the database for inserting."; }

						// create a prepared statement 
						String query = " insert into funds (`FundID`,`FundRecipient`,`CompanyName`,`TimeDuration`,`Purpose`,`DonationAmount`)" + " values (?, ?, ?, ?, ?, ?)";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values 
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, fRecipient); 
						preparedStmt.setString(3, comName);
						preparedStmt.setString(4, timeDuration);
						preparedStmt.setString(5, purpose);
						preparedStmt.setDouble(6, Double.parseDouble(donaAmount)); 
						
						// execute the statement 
						preparedStmt.execute(); 
						con.close();
						
						output = "Fund inserted successfully!";
				} 
				catch (Exception e) 
				{
						output = "Error while inserting the fund."; 
						System.err.println(e.getMessage());
				} 
				
				return output;
		
		}
		
		
		//Creating read part
		public String readFunds() 
		{
				String output = "";
		
				try 
				{
						Connection con = connect();
		
						if (con == null) 
						{return "Error while connecting to the database for reading."; }
		
						// Prepare the html table to be displayed 
						output = "<table border='1'><tr><th>Fund Recipient Name</th><th>Company Name</th>" + 
								 "<th>Time Duration(Years)</th>" + 
								 "<th>Purpose</th>" + 
								 "<th>Donation Amount</th></tr>";
		
						String query = "select * from funds"; 
						Statement stmt = con.createStatement(); 
						ResultSet rs = stmt.executeQuery(query);
		
						// iterate through the rows in the result set 
						while (rs.next()) 
						{
								String FundID  = Integer.toString(rs.getInt("FundID")); 
								String FundRecipient = rs.getString("FundRecipient"); 
								String CompanyName = rs.getString("CompanyName"); 
								String TimeDuration = rs.getString("TimeDuration");  
								String Purpose = rs.getString("Purpose");
								String DonationAmount = Double.toString(rs.getDouble("DonationAmount"));
								
								// Add into the html table 
								output += "<tr><td>" + FundRecipient + "</td>"; 
								output += "<td>" + CompanyName + "</td>"; 
								output += "<td>" + TimeDuration + "</td>"; 
								output += "<td>" + Purpose + "</td>";
								output += "<td>" + DonationAmount + "</td>";
						 } 
						 
						 con.close();
						 
						 // Complete the html table 
						 output += "</table>";
				} 
				catch (Exception e) 
				{
						output = "Error while reading the funds."; 
						System.err.println(e.getMessage());
				} 
				
				return output;
		}
		
		
		//Creating update Part
		public String updateFund(String FID, String fRecipient, String comName, String timeDuration, String purpose, String donaAmount)
		{
			String output = "";
			
			try 
			{
					Connection con = connect();
			
					if (con == null) 
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement 
					String query = "UPDATE funds SET FundRecipient=?,CompanyName=?,TimeDuration=?,Purpose=?,DonationAmount=? WHERE FundID =?";
			
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values 
					preparedStmt.setString(1, fRecipient); 
					preparedStmt.setString(2, comName); 
					preparedStmt.setString(3, timeDuration); 
					preparedStmt.setString(4, purpose);
					preparedStmt.setDouble(5, Double.parseDouble(donaAmount));
					preparedStmt.setInt(6, Integer.parseInt(FID));
			
					// execute the statement 
					preparedStmt.execute(); 
					con.close();
					
					output = "Fund details, updated successfully!";
			} 
			catch (Exception e) 
			{
					output = "Error while updating the fund."; 
					System.err.println(e.getMessage());
			} 
			
			return output;
			
		}	
		
		
}
