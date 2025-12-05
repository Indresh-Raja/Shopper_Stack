package com.shopperstack.shopper;

import org.testng.annotations.Test;

import com.shopperstack.baseclass.BaseAPIClass;
import com.shopperstack.constants.endpoints.IEndpoints;
import com.shopperstack.pojo.ShopperPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class RegisterShopper extends BaseAPIClass{
	@Test
	public void registerShopper() {
		int randomNum = jUtil.getRandomNumber();
//		ShopperPojo shopperPojo=new ShopperPojo("bangalore", "india", "indreshraja"+randomNum+"@gmail.com", "Indresh", "Male", "r", "user@123", "9876543218", "Karnataka");
		
		Response response = given()
			.spec(reqSpecObj)
			.queryParam("zoneId", "ALPHA")
			.body("{\r\n"
					+ "  \"city\": \"Bangalore\",\r\n"
					+ "  \"country\": \"india\",\r\n"
					+ "  \"email\": \"rajakokila"+randomNum+"@gmail.com\",\r\n"
					+ "  \"firstName\": \"Raja\",\r\n"
					+ "  \"gender\": \"MALE\",\r\n"
					+ "  \"lastName\": \"kokila\",\r\n"
					+ "  \"password\": \"user@171\",\r\n"
					+ "  \"phone\": 9876543299,\r\n"
					+ "  \"state\": \"Karnataka\"\r\n"
					+ "}")
//			.body(shopperPojo)
		.when()
			.post(IEndpoints.Register);
		response.then()
			.spec(respSpecObj)
			.statusCode(201)
			.log().all();
		
		String shopperId = response.jsonPath().getString("data.userId");
		System.out.println(shopperId);
		
		given()
			.spec(reqSpecObj)
			.pathParam("shopperId", shopperId)
		.when()
			.get(IEndpoints.ReadShopper)
		.then()
			.log().all();
		
		given()
			.spec(reqSpecObj)
			.pathParam("shopperId", shopperId)
			.queryParam("zoneId", "ALPHA")
			.body("{\r\n"
					+ "  \"city\": \"Bangalore\",\r\n"
					+ "  \"country\": \"India\",\r\n"
					+ "  \"email\": \"rajakokila"+randomNum+"@gmail.com\",\r\n"
					+ "  \"firstName\": \"Indresh\",\r\n"
					+ "  \"gender\": \"MALE\",\r\n"
					+ "  \"lastName\": \"r\",\r\n"
					+ "  \"password\": \"user@121\",\r\n"
					+ "  \"phone\": 9876543299,\r\n"
					+ "  \"state\": \"Karnataka\"\r\n"
					+ "}")
		.when()
			.patch(IEndpoints.ReadShopper)
		.then()
			.statusCode(200)
			.log().all();

	}
}
