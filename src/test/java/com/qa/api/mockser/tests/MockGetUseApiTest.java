package com.qa.api.mockser.tests;

import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.api.mocking.APIMocks;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.*;
import static org.hamcrest.Matchers.*;

public class MockGetUseApiTest extends BaseTest {
	
	@Test
	public void getDummyUserTest() {
		
		
		APIMocks.getDummyUser();
		Response response=restClient.get(BASE_URL_LOCALHOST_PORT, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then()
		       .assertThat()
		       .statusCode(200)
		      . body("name",equalTo("Deepika"));
		          
		                 
		}
	
	@Test
	public void getDummyUserTestithJson() {
		
		
		APIMocks.getDummyUserWithJson();
		Response response=restClient.get(BASE_URL_LOCALHOST_PORT, "/api/users", null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then()
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																					.assertThat()
		       .statusCode(200);
		     response.prettyPrint();
		          
		  }

	@Test
	public void getDummyProductTestithJson() {
		
		
		APIMocks.getDummyUserWithJson();
		Response response=restClient.get(BASE_URL_FAKEPRODUCT, "/products", null, null, AuthType.NO_AUTH, ContentType.ANY);
		response.then()
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																					.assertThat()
		       .statusCode(200);
		     response.prettyPrint();
		          
		                 
		
		
		
		
	}

}
