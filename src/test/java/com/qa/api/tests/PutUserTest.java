package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutUserTest extends BaseTest {
	
	
	@DataProvider
	public Object[][] getUserdata() {
		return new Object[][] {
			{"Anjelina","female","inactive","Anjelyjonhy","active"},
			{"Freddy","male","active","FredJacob","inactive"},
		};
	}
	
	@Test(dataProvider ="getUserdata" )
	
	public void putUserTest(String name,String gender,String status,String updatedName,String updatedStatus) {
		
		
		
		
			//create User
			//directly with POJO class
			User user = User.builder()
					.name(name)
					.gender(gender)
					.email(StringUtility.getrandomEmailid())
					.status(status)
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
		
		

		
		//Update the same user using setter
			
			//user.setGender("female");
			//user.setEmail(StringUtility.getrandomEmailid());
			
			user.setName(updatedName);
			user.setStatus(updatedStatus);
			
		//Update the same userid
			Response responsePUT=restClient.put(BASE_URL_GOREST,"/public/v2/users/" +userid, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		
		
			responsePUT.prettyPrint();
		Assert.assertEquals(responsePUT.getStatusCode(), 200);
		Assert.assertEquals(responsePUT.jsonPath().getString("id"),userid);
		Assert.assertEquals(responsePUT.jsonPath().getString("status"),user.getStatus());
		Assert.assertEquals(responsePUT.jsonPath().getString("name"),user.getName());
	}

}
