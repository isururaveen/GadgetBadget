package com;
import model.Login;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author IsuruRaveen
 *
 */

@Path("login")
//For Login (Buyers/ Researchers/ Funding Bodies)  
public class LoginService {
	
	Login log = new Login();
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String loginGadgetBadget(@FormParam("userName") String userName, @FormParam("password") String password)
	{
		if(userName != null || password != null)
		{
			
			System.out.println("Login success!");
			return log.login(userName, password);
					
		}else {
			
			return "Please Don't leave UserName or Password";
		}
	}
}
