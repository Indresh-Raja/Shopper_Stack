package com.shopperstack.shopper;

import org.testng.annotations.Test;

import com.shopperstack.baseclass.BaseAPIClass;
import com.shopperstack.constants.endpoints.IEndpoints;

import static io.restassured.RestAssured.*;

public class Cart extends BaseAPIClass{
	@Test
	public void cart() {
		given()
			.spec(reqSpecObj)
			.body("{\r\n"
					+ "  \"productId\": ,\r\n"
					+ "  \"quantity\": 0\r\n"
					+ "}")
		.when()
			.post(IEndpoints.AddIntoCart)
		.then()
			.log().all();
	}
}
