package com.qa.reqres.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResTest extends BaseTest {

	@Test
	public void getSingleUserTest() {


		Response response=restClient.get(BASE_URL_REQRES,"/api/users?page=2", null, null, AuthType.NO_AUTH, ContentType.JSON); 

		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();

	}

}
