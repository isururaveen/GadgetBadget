package com;

import model.Fund;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Funds")
public class FundService {
	
	Fund fundobj = new Fund();

	//Implement the Read Funds Operation
	@GET 
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFunds()  
	{
		return fundobj.readFunds();
	}

	//Implement the Insert Funds Operation
	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertFund(@FormParam("FundRecipient") String FundRecipient, 
							 @FormParam("CompanyName") String CompanyName, 
							 @FormParam("TimeDuration") String TimeDuration, 
							 @FormParam("Purpose") String Purpose,
							 @FormParam("DonationAmount") String DonationAmount)
	{
		String output = fundobj.insertFund(FundRecipient, CompanyName, TimeDuration, Purpose, DonationAmount); 
		
		return output;
	}
	
}
