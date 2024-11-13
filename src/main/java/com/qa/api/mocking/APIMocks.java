package com.qa.api.mocking;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import com.github.tomakehurst.wiremock.client.WireMock;;

public class APIMocks {
	
	public static void getDummyUser() {
		
		stubFor(get(urlEqualTo("/api/users"))
				  .willReturn(aResponse()
				          .withStatus(200)
				          .withHeader("Content-Type","application/json")
				          .withBody("{\n"
				          		+ "   \"name\":\"Deepika\"\n"
				          		+ "}")
		           )
				  );
	}
	
	
	public static void getDummyUserWithJson() {
		
		stubFor(get(urlEqualTo("/api/users"))
				  .willReturn(aResponse()
				          .withStatus(200)
				          .withHeader("Content-Type","application/json")
				          .withBodyFile("user.json")
		           )
				  );
	}
	
	public static void getDummyProductWithJson() {
		
		stubFor(get(urlEqualTo("/products"))
				  .willReturn(aResponse()
				          .withStatus(200)
				          .withHeader("Content-Type","application/json")
				          .withBodyFile("product.json")
		           )
				  );
	}

}
