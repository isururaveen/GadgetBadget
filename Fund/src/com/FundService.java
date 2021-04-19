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

	//<-----Creating the Read Funds Operation----->
	@GET 
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFunds()  
	{
		return fundobj.readFunds();
	}

	//<------Creating the Insert Funds Operation------>
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
	
	//<-------Creating the Update Funds Operation------->
	@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateFund(String fundData) 
	{
		//Convert the input string to a JSON object 
		JsonObject fundObje = new JsonParser().parse(fundData).getAsJsonObject();
		
		//Read the values from the JSON object 
		String FundID = fundObje.get("FundID").getAsString(); 
		String FundRecipient = fundObje.get("FundRecipient").getAsString(); 
		String CompanyName = fundObje.get("CompanyName").getAsString(); 
		String TimeDuration = fundObje.get("TimeDuration").getAsString(); 
		String Purpose = fundObje.get("Purpose").getAsString();
		String DonationAmount = fundObje.get("DonationAmount").getAsString();
		
		String output = fundobj.updateFund(FundID, FundRecipient, CompanyName, TimeDuration, Purpose, DonationAmount); 
		
		return output;
	}
	
	//<-------Creating the delete Funds Operation------->
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) public 
	String deleteFund(String fundData) 
	{
		//Convert the input string to an XML document 
		Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());
		
		//Read the value from the element <FundID> 
		String FundID = doc.select("FundID").text();
		String output = fundobj.deleteFund(FundID); 
		
		return output;
	}
	
}
