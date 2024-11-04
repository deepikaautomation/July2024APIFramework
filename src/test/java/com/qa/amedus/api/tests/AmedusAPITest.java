package com.qa.amedus.api.tests;


import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmedusAPITest extends BaseTest {
	
	@Test
	public void getFlightdetails() {
		
	Map<String,String> queryParam=Map.of("origin", "PAR", "maxPrice","200");
		
		Response response=restClient.get(BASE_URL_AMADEUS,"/v1/shopping/flight-destinations", queryParam, null, AuthType.OAUTH2, ContentType.JSON);
		response.prettyPrint();
		
	}

}
