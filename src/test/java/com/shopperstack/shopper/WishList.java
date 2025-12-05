package com.shopperstack.shopper;

import org.testng.annotations.Test;

import com.shopperstack.baseclass.BaseAPIClass;
import com.shopperstack.constants.endpoints.IEndpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Random;

public class WishList extends BaseAPIClass{
	@Test
	public void wishList() throws Throwable {
		Random random=new Random();
		int randomNum = random.nextInt(50);
		Response response = given()
			.spec(reqSpecObj)
			.pathParam("shopperId",	292252)
			.body("{\r\n"
					+ "  \"productId\": "+randomNum+",\r\n"
					+ "  \"quantity\": 0\r\n"
					+ "}")
		.when()
			.post(IEndpoints.WishList);
		response.then()
			.statusCode(201)
			.log().all();
		String shopperId = response.jsonPath().getString("shopperId");
		String productId = response.jsonPath().getString("productId");
		
		
		int lastrow = eUtil.getLastRowNum("Sheet");
		eUtil.setDataInToExcelSheet("Sheet1", lastrow+1, 0, shopperId);
		eUtil.setDataInToExcelSheet("Sheet1", lastrow+1, 1, productId);
		
		Response response2= given()
			.spec(reqSpecObj)
			.pathParam("shopperId",	287797)
		.when()
			.get(IEndpoints.WishList);
		response2.then()
			.statusCode(200)
			.log().all();
	
		given()
			.spec(reqSpecObj)
			.pathParam("shopperId", shopperId)
			.pathParam("productId", productId)
		.when()
			.delete(IEndpoints.DeleteWishlist)
		.then()
			.statusCode(204)
			.log().all();
	}
}
