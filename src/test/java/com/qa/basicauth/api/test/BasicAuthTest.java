package com.qa.basicauth.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthTest  extends BaseTest {
	
	@Test
	public void basicAuth() {
	
	Response response=restClient.get(BASE_URL_BASICAUTH,"/basic_auth",null,null,AuthType.BASIC_AUTH,ContentType.ANY);
	Assert.assertEquals(response.asString().contains("Congratulations! You must have the proper credentials."), true);
	
	}

}
