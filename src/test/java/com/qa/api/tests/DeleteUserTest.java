package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest {

	@Test
	public void deleteUserTest() {
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
		System.out.println("userid--->"+userid);

		//Get-Fetch the same user with the userid

		Response responseGet=restClient.get(BASE_URL_GOREST,"/public/v2/users/"+ userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responseGet.getStatusCode(), 200);
		Assert.assertEquals(responseGet.jsonPath().getString("id"),userid);
		responseGet.prettyPrint();

		//Delete the same user

		

		Response responseDelete = restClient.delete(BASE_URL_GOREST,"/public/v2/users/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);

		responseDelete.prettyPrint();
		System.out.println(responseDelete.getStatusCode());
		Assert.assertEquals(responseDelete.getStatusCode(), 204);


		//Fetch the deleted user
		
		Response responseGetAfterDelete=restClient.get(BASE_URL_GOREST,"/public/v2/users/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		responseGetAfterDelete.prettyPrint();
		Assert.assertEquals(responseGetAfterDelete.getStatusCode(), 404);

	}
}


