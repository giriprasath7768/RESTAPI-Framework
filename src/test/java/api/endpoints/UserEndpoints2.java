package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.Users;

public class UserEndpoints2 {
	
	
	public static ResourceBundle geturl()
	{
		ResourceBundle resource=ResourceBundle.getBundle("approutes");
		return resource;
	}
	
	
	
	public static Response createusers(Users payload)
	{
		String Url=geturl().getString("user_post_url");
		Response res=given()
				      .contentType(ContentType.JSON)
				      .accept(ContentType.JSON)
				      .body(payload)
				    .when()
				       .post(Url);
				
		
		return res;
		
	}
	
	public static Response Getusers(String uname)
	{
		String Url=geturl().getString("user_get_url");
		
		Response res=given()
				      .contentType(ContentType.JSON)
				      .accept(ContentType.JSON)
				      .pathParam("username", uname)
				    .when()
				       .get(Url);
				
		
		return res;
		
	}
	
	
	public static Response Putusers(Users payload, String uname)
	{
		String Url=geturl().getString("user_update_url");
		Response res=given()
				      .contentType(ContentType.JSON)
				      .accept(ContentType.JSON)
				      .pathParam("username", uname)
				      .body(payload)
				    .when()
				      .put(Url);
				
		
		return res;
		
	}
	
	
	public static Response Deleteusers(String uname)
	{
		String Url=geturl().getString("user_delete_url");
		Response res=given()
				      .contentType(ContentType.JSON)
				      .accept(ContentType.JSON)
				      .pathParam("username", uname)
				    .when()
				       .delete(Url);
				
		
		return res;
		
	}

}
