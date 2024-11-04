package com.qa.api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.qa.api.constants.AuthType;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class GetUserTest extends BaseTest {
	
	@Test
	public void getUsersTest() {
		
		
		  Map<String,String> queryparam=new HashMap<String,String>();
		  queryparam.put("status", "inactive");
		  
	//queryparam.put("status", "inactive");
		 
		
		Response response=restClient.get(BASE_URL_GOREST,"/public/v2/users", queryparam, null, AuthType.BEARER_TOKEN, ContentType.JSON); 
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
		
	}
	
	
	@Test
	public void getSingleUserTest() {
		
		
		Response response=restClient.get(BASE_URL_GOREST,"/public/v2/users/7440195", null, null, AuthType.BEARER_TOKEN, ContentType.JSON); 
		
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
