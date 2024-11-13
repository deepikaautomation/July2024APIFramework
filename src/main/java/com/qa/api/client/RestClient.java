package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FramweorkExceptions;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
/**
 * @author DeepikaDR
 *
 */
/**
 * @author DeepikaDR
 *
 */
public class RestClient {
	
	private  ResponseSpecification responseSpec200=expect().statusCode(200);
	private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200), equalTo(201)));
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200), equalTo(404)));
	private  ResponseSpecification responseSpec201=expect().statusCode(201);
	private  ResponseSpecification responseSpec204=expect().statusCode(204);
	private  ResponseSpecification responseSpec400=expect().statusCode(400);
	private  ResponseSpecification responseSpec401=expect().statusCode(401);
	private  ResponseSpecification responseSpec404=expect().statusCode(404);
	private  ResponseSpecification responseSpec422=expect().statusCode(422);
	private  ResponseSpecification responseSpec500=expect().statusCode(500);
	
	
	
	

	//private String baseUrl=ConfigManager.get("baseUrl");

	private RequestSpecification setupRequest(String baseUrl, AuthType authType,ContentType contentType) {

		RequestSpecification  request=RestAssured.given().log().all()
				.auth().none() 
				.baseUri(baseUrl)
				.contentType(contentType)
				.accept(contentType);

		switch (authType) {
		case BEARER_TOKEN:
			//request
			
			request.header("Authorization", "Bearer "+ConfigManager.get("bearerToken"));
			break;
		case CONTACTS_BEARER_TOKEN:
			//request
			
			request.header("Authorization", "Bearer "+ConfigManager.get("CONTACTS_BEARER_TOKEN"));
			break;

		case OAUTH2:
			request
			.header("Authorization","Bearer "+generateOAuth2Token());

			break;

		case BASIC_AUTH:
			request
			.header("Authorization","Basic "+generateBasicAuthToken());
			break;
		case API_KEY:
			request
			.header("api_key",ConfigManager.get("apiKey"));

			break;
		case NO_AUTH:
			System.out.println("No Auth required");
			break;
		default:

			System.out.println("This Auth is not supported... please provide the right Auth...");
			throw new FramweorkExceptions("NO AUTH SUPPORTED");

		}
		return request;


	}
	
	private String generateBasicAuthToken() {
		String credentials=ConfigManager.get("basicUsername")+ ":" + ConfigManager.get("basicPassword");
		
		return Base64.getEncoder().encodeToString(credentials.getBytes());
	}
		                  

	private String generateOAuth2Token() {

		return RestAssured.given()
				.contentType("application/x-www-form-urlencoded")
				.formParam("grant_type", ConfigManager.get("grantType"))
				.formParam("client_id", ConfigManager.get("clientID"))
				.formParam("client_secret", ConfigManager.get("clientSecret"))
				.when()
				.post(ConfigManager.get("tokenUrl"))
				
				.then()
				.extract()
				.path("access_token");




	}

	//***********************************CRUD OPERATIONS************************************

	/**
	 * 
	 * @param endpoint
	 * @param queryParam
	 * @param pathparam
	 * @param authtype
	 * @param contentType
	 * @return
	 */
	public Response get(String baseUrl,String endpoint,Map<String,String> queryParam, 
			Map<String,String> pathparam,AuthType authtype,ContentType contentType) {


		RequestSpecification request=setupRequest(baseUrl,authtype, contentType);

		applyParams(request, queryParam, pathparam);
		
		Response response=request.get(endpoint)
                 .then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;
                     
	}
	
	
	/**
	 * This method is to call the POST API
	 * @param <T>
	 * @param endpoint
	 * @param body
	 * @param queryParam
	 * @param pathparam
	 * @param authtype
	 * @param contentType
	 * @return post api response
	 */
	public <T>Response post(String baseUrl, String endpoint,T body, Map<String,String> queryParam,
			Map<String,String> pathparam,AuthType authtype,ContentType contentType) {
		
		RequestSpecification request=setupRequest(baseUrl,authtype, contentType);
		
		applyParams(request, queryParam, pathparam);
		
		Response response=request.body(body).post(endpoint).then().spec(responseSpec200or201).extract().response();
		
		response.prettyPrint();
		return response;
		
	}
	
	
	/**
	 * @param <T>
	 * @param endpoint
	 * @param file
	 * @param queryParam
	 * @param pathparam
	 * @param authtype
	 * @param contentType
	 * @return
	 */
	public <T>Response post(String baseUrl,String endpoint,File file, Map<String,String> queryParam,
			Map<String,String> pathparam,AuthType authtype,ContentType contentType) {
		
		RequestSpecification request=setupRequest(baseUrl,authtype, contentType);
		
		applyParams(request, queryParam, pathparam);
		
		Response response=request.body(file).post(endpoint).then().spec(responseSpec201).extract().response();
		
		response.prettyPrint();
		return response;
		
	}
	
	
	public <T>Response put(String baseUrl,String endpoint,T body, Map<String,String> queryParam,
			Map<String,String> pathparam,AuthType authtype,ContentType contentType) {
		
		RequestSpecification request=setupRequest(baseUrl,authtype, contentType);
		
		applyParams(request, queryParam, pathparam);
		
		Response response=request.body(body).put(endpoint).then().spec(responseSpec200).extract().response();
		
		response.prettyPrint();
		return response;
		
	}
	
	
	public <T>Response patch(String baseUrl,String endpoint,T body, Map<String,String> queryParam,
			Map<String,String> pathparam,AuthType authtype,ContentType contentType) {
		
		RequestSpecification request=setupRequest(baseUrl,authtype, contentType);
		
		applyParams(request, queryParam, pathparam);
		
		Response response=request.body(body).patch(endpoint).then().spec(responseSpec200).extract().response();
		
		response.prettyPrint();
		return response;
		
	}
	
	
	public Response delete(String baseUrl,String endpoint, Map<String,String> queryParam,
			Map<String,String> pathparam,AuthType authtype,ContentType contentType) {
		
		RequestSpecification request=setupRequest(baseUrl,authtype, contentType);
		
		applyParams(request, queryParam, pathparam);
		
		Response response=request.delete(endpoint).then().spec(responseSpec204).extract().response();
		
		response.prettyPrint();
		return response;
		
	}
	
	
	
	
	/* Generic method for adding query and path parametersparametes */
	/**
	 * @param request
	 * @param queryParam
	 * @param pathparam
	 */
	private void applyParams( RequestSpecification request,Map<String,String> queryParam,
			Map<String,String> pathparam) {
		
		if(queryParam!=null) {
		
		request.queryParams(queryParam);
		}

		if(pathparam!=null) {
			request.pathParams(pathparam);
			}
		
		
	}
























}
