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
}
