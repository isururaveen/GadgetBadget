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

	//implement the read product
	Product productObj = new Product(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readProducts() 
	 { 
	 return  productObj.readProduct(); 
	 } 
	
	//implememnt the insert product
@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertProduct(@FormParam("productName") String productName, 
 @FormParam("proCategory") String proCategory, 
 @FormParam("proDesc") String proDesc, 
 @FormParam("proPrice") String proPrice) 
{ 
 String output = productObj.insertProduct(productName, proCategory, proDesc, proPrice); 
return output; 
}

}





