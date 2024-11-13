package com.qa.api.mockser.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MockGetProductsAPITest extends BaseTest {
	
	
	@Test
	public void getDummyProductTestithJson() {
		
		
		APIMocks.getDummyProductWithJson();
		Response response=restClient.get(BASE_URL_FAKEPRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then()
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																					.assertThat()
		       .statusCode(200);
		     response.prettyPrint();
		          
		                 
		
		
		
		
	}
 

}
