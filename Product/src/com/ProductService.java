package com;

import model.Product; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Products") 
public class ProductService {

	//create the read product
	Product productObj = new Product(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProducts() 
	 { 
	 return  productObj.readProduct(); 
	 }  
	
	//create the insert product
	
@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertProduct(@FormParam("productName") String productName, 
 @FormParam("proCategory") String proCategory, 
 @FormParam("proDesc") String proDesc, 
 @FormParam("proPrice") String proPrice) 
{ 
	String output="";
	
	if(productName.length() == 0 || proCategory.length() == 0 || proDesc.length() == 0 || proPrice.length() == 0)
	{
		return "You Cannot keep Empty Fields..! Please Try Again..!";
	}
	else
  output = productObj.insertProduct(productName, proCategory, proDesc, proPrice); 
return output; 
}
//add method

@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateProduct(String productData) 
{ 
	
//Convert the input string to a JSON object 
JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject(); 

//Read the values from the JSON object
String productID = productObject.get("productID").getAsString(); 
String productName = productObject.get("productName").getAsString(); 
String proCategory = productObject.get("proCategory").getAsString(); 
String proDesc = productObject.get("proDesc").getAsString(); 
String proPrice = productObject.get("proPrice").getAsString(); 

String output = productObj.updateProduct(productID, productName, proCategory, proDesc, proPrice); 
return output; 
}

//delete

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteItem(String productData) 
{ 
//Convert the input string to an XML document
Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 

//Read the value from the element <productID>
String productID = doc.select("productID").text(); 
String output = productObj.deleteProduct(productID); 
return output; 
} 
}





