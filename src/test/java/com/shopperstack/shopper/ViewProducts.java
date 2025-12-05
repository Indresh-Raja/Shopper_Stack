package com.shopperstack.shopper;

import org.testng.annotations.Test;

import com.shopperstack.baseclass.BaseAPIClass;
import com.shopperstack.constants.endpoints.IEndpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ViewProducts extends BaseAPIClass{
	@Test(invocationCount = -1)
	public void viewProducts() {
		Response response = given()
			.spec(reqSpecObj)
			.formParam("zoneId", "ALPHA")
		.when()
			.get(IEndpoints.Products);
		response.then()
			.log().all();
			
		String productId = response.jsonPath().getString("data[12].productId");
		System.out.println(productId);
	}
}
