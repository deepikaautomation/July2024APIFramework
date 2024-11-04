package com.qa.test.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest  {
	
	protected final static String  BASE_URL_CONTACTS="https://thinking-tester-contact-list.herokuapp.com";
	protected final static String  BASE_URL_REQRES="https://reqres.in";
	protected final static String  BASE_URL_GOREST="https://gorest.co.in/";
	protected final static String  BASE_URL_FAKEPRODUCT="https://fakestoreapi.com";
	protected final static String  BASE_URL_BASICAUTH="https://the-internet.herokuapp.com";
	protected final static String  BASE_URL_AMADEUS="https://test.api.amadeus.com";
	
	
	
	
	
	
	protected RestClient restClient;
	
	//@Parameters({"baseUrl"})
	@BeforeTest
	//public void setUp(@Optional String baseUrl ) //IF WE ARE USING PARAMETER AS BASEURL
	public void setUp( ) {
		
		//if(baseUrl!=null) {
			//ConfigManager.set(baseUrl, baseUrl); //first baseurlfrom properties, second is the parameter
			
		//}
		RestAssured.filters(new AllureRestAssured());
		restClient=new RestClient();
		
	}
	
	

}
