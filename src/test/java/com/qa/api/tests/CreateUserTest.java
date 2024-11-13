package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.pojo.User.UserBuilder;
import com.qa.api.utils.StringUtility;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {


	@Test

	public void createUserTest() {

		//directly with POJO class
		User user=new User("Deeps",StringUtility.getrandomEmailid(),"female","active");


		Response response=restClient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}


	/**
	 * 
	 */
	@Test

	public void createUserWithBuilderTest() {

		
		//create User
		//directly with POJO class
		User user = User.builder()
				.name("Timothy cook")
				.gender("male")
				.email(StringUtility.getrandomEmailid())
				.status("active")
				.build();


		Response response=restClient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
		
		//fetch userid
		String userid=response.jsonPath().getString("id");
		System.out.println(userid);
		
		//Get-Fetch the same user with the userid
		
		Response responseGet=restClient.get(BASE_URL_GOREST,"/public/v2/users/"+ userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.getStatusCode(), 200);
		
		Assert.assertEquals(responseGet.jsonPath().getString("id"),userid);
		Assert.assertEquals(responseGet.jsonPath().getString("name"),user.getName());
		Assert.assertEquals(responseGet.jsonPath().getString("gender"),user.getGender());
		Assert.assertEquals(responseGet.jsonPath().getString("email"),user.getEmail());
		Assert.assertEquals(responseGet.jsonPath().getString("status"),user.getStatus());
		
	}

	
	
	


	/**
	 * 
	 */
	@Test(enabled=false)

	public void createUserWithJsonFileTest() {

		
		File filewithjson=new File("./src/test/resources/jsons/user.json");

		Response response=restClient.post("BASE_URL_GOREST","/public/v2/users", filewithjson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 201);
	}

}
