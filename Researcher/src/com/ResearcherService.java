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
			@FormParam("Password")String Password)
	{	
		if(FirstName.length() == 0 || LastName.length() == 0 || Address.length() == 0 || Email.length() == 0 || Phone.length() == 0 || UserName.length() == 0 || Password.length() == 0)
		{
			return "It is essential that you fill in all the fields. please try again!";
		}
		else 
		{
			if(Password.length()<4)
			{
				return "Password should have more than 4 digits!"; 
			}
			else 
			{
				if(Phone.length()<10)
				{
					return "Please provide a valid phone number!";
				}
				else
				{
					String output = researcherObj.insertResearcher(FirstName, LastName, Address, Email, Phone, UserName, Password);
					return output;
				}
			}
		}
	}
	
	//<-----Creating the update researcher Operation----->
	@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearcher(String researcherData) 
	{
		//Convert the input string to a JSON object 
		JsonObject researcherObje = new JsonParser().parse(researcherData).getAsJsonObject();
		
		//Read the values from the JSON object 
		String ResearcherID = researcherObje.get("ResearcherID").getAsString(); 
		String FirstName = researcherObje.get("FirstName").getAsString(); 
		String LastName = researcherObje.get("LastName").getAsString(); 
		String Address = researcherObje.get("Address").getAsString(); 
		String Email = researcherObje.get("Email").getAsString();
		String Phone = researcherObje.get("Phone").getAsString(); 
		String UserName = researcherObje.get("UserName").getAsString(); 
		String Password = researcherObje.get("Password").getAsString();
		
		String output = researcherObj.updateResearcher(ResearcherID, FirstName, LastName, Address, Email, Phone, UserName, Password); 
		return output;
	}
	
	//<-----Creating the delete researcher Operation----->
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) public 
	String deleteResearcher(String researcherData) 
	{
		//Convert the input string to an XML document 
		Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser());
		
		//Read the value from the element <ResearcherID> 
		String ResearcherID = doc.select("ResearcherID").text();
		String output = researcherObj.deleteResearcher(ResearcherID); 
		return output;
	}
}


