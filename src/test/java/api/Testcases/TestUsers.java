package api.Testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.Users;
import io.restassured.response.Response;

public class TestUsers {
	
	Faker fk;
	Users userpayloaddata;
	
	@BeforeClass
	public void setupdata()
	{
		fk=new Faker();
		userpayloaddata=new Users();
		
		userpayloaddata.setId(fk.idNumber().hashCode());
		userpayloaddata.setFirstName(fk.name().firstName());
		userpayloaddata.setLastName(fk.name().lastName());
		userpayloaddata.setUsername(fk.name().username());
		userpayloaddata.setPassword(fk.internet().password(5, 10));
		userpayloaddata.setPhone(fk.phoneNumber().cellPhone());
		userpayloaddata.setEmail(fk.internet().emailAddress());
		
		
		
	}
	
	@Test(priority=1)
	public void Postusers() {
		
		Response Ures=UserEndpoints.createusers(userpayloaddata);
		Ures.then().log().all();	
		Assert.assertEquals(Ures.getStatusCode(), 200);
	}
	
   @Test(priority=2)
   public void Getusers()
   {
	   Response Ures=UserEndpoints.Getusers(userpayloaddata.getUsername());
		Ures.then().log().all();	
		Assert.assertEquals(Ures.getStatusCode(), 200);
   }
	
	@Test(priority=3)
	public void Putusers()
	{
		userpayloaddata.setPassword(fk.internet().password(5, 10));
		userpayloaddata.setPhone(fk.phoneNumber().cellPhone());
		userpayloaddata.setEmail(fk.internet().emailAddress());
		
		Response Ures=UserEndpoints.Putusers(userpayloaddata, userpayloaddata.getUsername());
		Ures.then().log().all();	
		Assert.assertEquals(Ures.getStatusCode(), 200);
		
		//Check the data
		 Response Cres=UserEndpoints.Getusers(userpayloaddata.getUsername());
		 Cres.then().log().all();	
	}
   
	@Test(priority=4)
	   public void Deleteusers()
	   {
		   Response Ures=UserEndpoints.Deleteusers(userpayloaddata.getUsername());
			Ures.then().log().all();	
			Assert.assertEquals(Ures.getStatusCode(), 200);
	   }
   
}
