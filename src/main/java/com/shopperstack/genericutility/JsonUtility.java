package com.shopperstack.genericutility;

import static io.restassured.RestAssured.given;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class JsonUtility {
	FileUtility fUtil=new FileUtility();
	public String getDataFromJsonFile(String key) throws IOException, ParseException {
		FileReader fileR=new FileReader("./src/test/resources/TestData/appCommonData.json");
		JSONParser parser=new JSONParser();
	    Object obj = parser.parse(fileR);
		JSONObject jobj=(JSONObject)obj;
		String data = (String) jobj.get(key);
		return data;
	}
	public String getDataOnJsonPath(Response response,String jsonXpath) {
		List<String> list = JsonPath.read(response.asString(), jsonXpath);
		return list.get(0).toString();
	}
	public boolean verifyDataOnJsonPath(Response response,String jsonXpath,String expectedData) {
		List<String> list=JsonPath.read(response.asString(),jsonXpath);
		boolean flag=false;
		for(String str:list) {
			if(str.equals(expectedData)) {
				System.out.println(expectedData+" is available==Pass");
			}
		}if(flag==false) {
			System.out.println(expectedData+" is not available==Fail");
		}
		return flag;
	}
	public String getToken() throws IOException {
		Response response = given()
				.contentType(ContentType.JSON)
				.relaxedHTTPSValidation()
				.body("{\r\n"
						+ "  \"email\": \"rajakokila4813@gmail.com\",\r\n"
						+ "  \"password\": \"user@171\",\r\n"
						+ "  \"role\": \"SHOPPER\"\r\n"
						+ "}")
			.when()
				.post("https://www.shoppersstack.com/shopping/users/login");
			
			String token = response.jsonPath().getString("data.jwtToken");
			return token;
	}
}
