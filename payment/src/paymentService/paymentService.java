package paymentService;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

import paymentModel.Payment; 
//For JSON

@Path("/Payment") 

public class paymentService {
	
	Payment p = new Payment(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
	 return p.getPay(); 
	 }

	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("cardNO") String cardNO, 
			@FormParam("date") String date, 
			@FormParam("cvv") String cvv, 
	 @FormParam("name") String name, 
	 @FormParam("address") String address, 
	 @FormParam("city") String city,
	@FormParam("postalCode") String postalCode,
	@FormParam("phone") String phone,
	@FormParam("email") String email
	)
	{ 
	 String output = p.addPay(cardNO, date, cvv, name, address, city, postalCode, phone, email);
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String id = itemObject.get("id").getAsString();
	 String cardNO = itemObject.get("cardNO").getAsString(); 
	 String date = itemObject.get("date").getAsString();
	 String cvv = itemObject.get("cvv").getAsString();
	 String name = itemObject.get("name").getAsString(); 
	 String address = itemObject.get("address").getAsString(); 
	 String city = itemObject.get("city").getAsString(); 
	 String postalCode = itemObject.get("postalCode").getAsString(); 
	 String phone = itemObject.get("phone").getAsString(); 
	 String email = itemObject.get("email").getAsString(); 
	 String output = p.updatePay(id, cardNO, date, cvv, name, address, city, postalCode, phone, email);
	return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String pay) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(pay, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String id = doc.select("id").text();  
	 String output = p.deletePay(id); 
	return output; 
	}
}
