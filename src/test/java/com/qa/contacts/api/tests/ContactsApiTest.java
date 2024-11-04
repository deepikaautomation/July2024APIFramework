package com.qa.contacts.api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.ContactsCredentials;
import com.qa.test.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsApiTest extends BaseTest {

	private String token;

	@BeforeMethod
	public void getToken() {

		ContactsCredentials creds = ContactsCredentials.builder().email("naveenanimation20@gmail.com")
				.password("test@123").build();

		Response response = restClient.post(BASE_URL_CONTACTS,"/users/login", creds, null, null, AuthType.NO_AUTH, ContentType.JSON);
		token = response.jsonPath().getString("token");
		System.out.println(token);
		ConfigManager.set("CONTACTS_BEARER_TOKEN", token);

	}

	@Test
	public void getContacts() {

		Response response = restClient.get(BASE_URL_CONTACTS,"/contacts", null, null, AuthType.CONTACTS_BEARER_TOKEN, ContentType.JSON);
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

}
