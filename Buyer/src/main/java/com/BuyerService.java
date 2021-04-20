package com;

import model.*;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

/**
 * @author IsuruRaveen
 *
 */

@Path("/Buyers")
public class BuyerService {
	
    Buyer buyer = new Buyer();
	
	//Default Buyers API
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String readBuyers() {
		return "Welcome to Buyer API";
	}

	//Get All Buyers - Retreive All Buyer Information
	@GET
	@Path("/all")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAllBuyers()
	{
		return buyer.getAllBuyers();
	}

	//Get Single Buyer Details
	@GET
	@Path("/id")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getBuyerByID(String id)
	{
		JsonObject buyerObject = new JsonParser().parse(id).getAsJsonObject();
		String buyerID = buyerObject.get("buyerID").getAsString();
		System.out.println(buyerID);
		return buyer.getBuyer(buyerID);
	}

	//Buyer Registration Service Access
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String CreateBuyer(@FormParam("firstName") String firstName, 
			@FormParam("lastName") String lastName,
			@FormParam("address") String address, 
			@FormParam("email") String email, 
			@FormParam("phoneNo") String phoneNo, 
			@FormParam("userName") String userName, 
			@FormParam("password")String password)
	{
		
		String output="";
		
		if(firstName.length() == 0 || lastName.length() == 0 || address.length() == 0 || email.length() == 0 || phoneNo.length() == 0 || userName.length() == 0 || password.length() == 0)
		{
			return "You Cannot keep Mendotory Fields Empty..! Please Try Again..!";
		}
		else 
		{
			if(userName.length()>3)
			{
				return "User Name Should Have More Than 3 Digits..!"; 
			}
			else 
			{
				if(password.length()<4)
				{
					return "Please Provide Password Which has more than 4 Digits..!";
				}
				else
				{
					output = buyer.insertBuyer(firstName, lastName, address, email, phoneNo, userName, password);
					return output;
				}
			}
		}
	}
}
