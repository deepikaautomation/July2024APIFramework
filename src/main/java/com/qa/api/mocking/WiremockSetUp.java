package com.qa.api.mocking;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

public class WiremockSetUp {
	
	private static WireMockServer server;
	

	public static void createWiremockServer() {
		
		server=new WireMockServer(8089);
		WireMock.configureFor("localhost", 8089);
		server.start();

	}

	public static void stopWiremockServer() {
		
		server.stop();

	}


}