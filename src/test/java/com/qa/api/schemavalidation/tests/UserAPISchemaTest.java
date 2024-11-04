package com.qa.api.schemavalidation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserAPISchemaTest extends BaseTest {

	
@Test
	
	public void productsAPISchemaTest() {
		
		/*
		 * RestAssured.given() .baseUri("https://gorest.co.in") .header(
		 * "Authorization","Bearer 1c2361da4c6648df4f474f630c77993e7037aedfd56d3503647d767876d80e3d"
		 * ) .when() .get("/public/v2/users/7502057") .then().assertThat()
		 * .statusCode(200) .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(
		 * "schema/user-schema.json"));
		 */
		
	Response response=restClient.get(BASE_URL_GOREST,"/public/v2/users/7502057", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(SchemaValidator.validateSchema(response, "schema/user-schema.json"),true);
		
		
	}

}
