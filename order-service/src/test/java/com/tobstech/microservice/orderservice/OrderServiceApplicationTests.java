package com.tobstech.microservice.orderservice;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import com.tobstech.microservice.orderservice.stubs.InventoryClientStub;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) //randomly assign port (Wiremock Testing, -> create a sub class (InventoryClientStubs in stubs package) that calls the inventory
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");
	
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";		
		RestAssured.port = port;	
	}
	
	static {
		mySQLContainer.start();
	}

	
	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = """
				{
					"skuCode": "iPhone_15",
					"price": 1000,
					"quantity": 1					
				}
				""";
		//call stub - wiremock for true
		InventoryClientStub.stubInventoryCall("iPhone_15", 1);
		
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();
		
		
		assertThat(responseBodyString, Matchers.is("order Placed Successfully"));
		
	}

}
