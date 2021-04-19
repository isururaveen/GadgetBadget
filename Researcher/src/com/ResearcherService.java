package com;

import model.Researcher;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Researchers")	
public class ResearcherService {
	
	Researcher researcherObj = new Researcher();
	
	//<-----Creating the read researcher Operation----->
	@GET 
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readResearchers()  
	{
		return researcherObj.readResearchers();
	}

	//<-----Creating the insert researcher Operation----->
	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearcher(@FormParam("FirstName") String FirstName, 
							 @FormParam("LastName") String LastName, 
							 @FormParam("Address") String Address, 
							 @FormParam("Email") String Email,
							 @FormParam("Phone") String Phone, 
							 @FormParam("UserName") String UserName, 
							 @FormParam("Password") String Password)
	{
		String output = researcherObj.insertResearcher(FirstName, LastName, Address, Email, Phone, UserName, Password); 
		
		return output;
	}
	

}


