package com.qa.products.api.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductsAPITests extends BaseTest {
	
	@Test
	public void getProducts() {
		
		Response response=restClient.get(BASE_URL_FAKEPRODUCT,"/product", null, null, AuthType.NO_AUTH, ContentType.JSON);
		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
