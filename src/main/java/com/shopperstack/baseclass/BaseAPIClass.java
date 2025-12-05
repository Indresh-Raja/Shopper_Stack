package com.shopperstack.baseclass;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.shopperstack.genericutility.DatabaseUtility;
import com.shopperstack.genericutility.ExcelUtility;
import com.shopperstack.genericutility.FileUtility;
import com.shopperstack.genericutility.JavaUtility;
import com.shopperstack.genericutility.JsonUtility;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {
	
	public FileUtility fUtil=new FileUtility();
	public ExcelUtility eUtil=new ExcelUtility();
	public JavaUtility jUtil=new JavaUtility();
	public DatabaseUtility dUtil=new DatabaseUtility();
	public JsonUtility jsonUtil=new JsonUtility();
	public RequestSpecification reqSpecObj;
	public ResponseSpecification respSpecObj;
	
	@BeforeSuite
	public void configBS() throws SQLException, Throwable {
//		dUtil.getDBConnection();
		String baseUrl = fUtil.getDataFromPropetiesFile("baseUrl");
		String token = jsonUtil.getToken();
		
		RequestSpecBuilder reqbuilder=new RequestSpecBuilder();
		reqbuilder.setRelaxedHTTPSValidation();
		reqbuilder.setAuth(oauth2(token));
//		reqbuilder.addHeader("token", token);
//		reqbuilder.addHeader("Authorization", "Bearer " + token);
		reqbuilder.setContentType(ContentType.JSON);
		reqbuilder.setBaseUri(baseUrl);
		reqSpecObj=reqbuilder.build();
		
		ResponseSpecBuilder respbuilder=new ResponseSpecBuilder();
		respbuilder.expectContentType(ContentType.JSON);
		respSpecObj= respbuilder.build();
	}

	@AfterSuite
	public void configAS() throws SQLException {
		dUtil.closeDBConnection();
	}
}
