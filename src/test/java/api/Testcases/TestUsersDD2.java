package api.Testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payloads.Users;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class TestUsersDD2 {
	
Users userpayloaddata;
	
	
	@Test(priority=1,dataProvider = "data",dataProviderClass = Dataproviders.class)
	public void Postusers(String userdata[]) {
		
        userpayloaddata=new Users();
		
		userpayloaddata.setId(Integer.parseInt(userdata[0]));
		userpayloaddata.setFirstName(userdata[2]);
		userpayloaddata.setLastName(userdata[3]);
		userpayloaddata.setUsername(userdata[1]);
		userpayloaddata.setPassword(userdata[5]);
		userpayloaddata.setPhone(userdata[6]);
		userpayloaddata.setEmail(userdata[4]);   
		
		Response Ures=UserEndpoints2.createusers(userpayloaddata);
		Ures.then().log().all();	
		Assert.assertEquals(Ures.getStatusCode(), 200);
	}
	
   @Test(priority=2,dataProvider = "username",dataProviderClass = Dataproviders.class)
   public void Getusers(String uname)
   {
	   Response Ures=UserEndpoints2.Getusers(uname);
		Ures.then().log().all();	
		Assert.assertEquals(Ures.getStatusCode(), 200);
   }
	
	@Test(priority=3 ,dataProvider = "username",dataProviderClass = Dataproviders.class)
	public void Putusers(String uname)
	{
		userpayloaddata.setPassword("Testpassword");
		userpayloaddata.setPhone("9894029726");
		userpayloaddata.setEmail("giri@hotmail.com");
		
		Response Ures=UserEndpoints2.Putusers(userpayloaddata,uname);
		Ures.then().log().all();	
		Assert.assertEquals(Ures.getStatusCode(), 200);
		
		//Check the data
		 Response Cres=UserEndpoints2.Getusers(uname);
		 Cres.then().log().all();	
	}
   
	@Test(priority=4,dataProvider = "username",dataProviderClass = Dataproviders.class )
	   public void Deleteusers(String uname)
	   {
		   Response Ures=UserEndpoints2.Deleteusers(uname);
			Ures.then().log().all();	
			Assert.assertEquals(Ures.getStatusCode(), 200);
	   }
   

}
