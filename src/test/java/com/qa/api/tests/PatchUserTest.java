package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchUserTest extends BaseTest {

	@Test
	public void patchUsetTest(){
	
	//create User
	//directly with POJO class
	User user = User.builder()
			.name("abc bbb")
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




//Update the same user using setter
	user.setName("updtaename");
	user.setGender("female");
	user.setStatus("inactve");
	
//Update the same userid
	Response responsePatch=restClient.patch(BASE_URL_GOREST,"/public/v2/users/" +userid, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);



response.prettyPrint();
Assert.assertEquals(responsePatch.getStatusCode(), 200);
Assert.assertEquals(responsePatch.jsonPath().getString("id"),userid);
Assert.assertEquals(responsePatch.jsonPath().getString("status"),user.getStatus());
Assert.assertEquals(responsePatch.jsonPath().getString("gender"),user.getGender());

}
	
}



