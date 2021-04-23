package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Login;
/**
 * @author IsuruRaveen
 *
 */

@Path("forgetPassword")
//For Reset Password(Buyer/Researchers/Funding)
public class forgetPasswordService {
	Login log = new Login();
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String changePass(@FormParam("userName") String userName, @FormParam("password") String password)
	{
		
		if(userName != null || password != null)
		{
			
			return log.forgotPassword(userName, password);
					
		}else {
			
			return "Please Don't leave User Name or Password Empty!";
		}
		
	}
}
