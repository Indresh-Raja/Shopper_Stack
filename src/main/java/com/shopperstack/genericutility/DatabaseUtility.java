package com.shopperstack.genericutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	FileUtility fUtil = new FileUtility();
	Connection conn;

	public void getDBConnection() throws SQLException, Throwable {
		String dbUrl = fUtil.getDataFromPropetiesFile("dbUrl");
		String db_Username = fUtil.getDataFromPropetiesFile("db_Username");
		String db_password = fUtil.getDataFromPropetiesFile("db_password");
		Driver driverObj = new Driver();
		DriverManager.registerDriver(driverObj);
		conn = DriverManager.getConnection(dbUrl, db_Username, db_password);
	}

	public void closeDBConnection() throws SQLException {
		try {
			conn.close();
		} catch (Exception e) {
		}
	}

	public boolean executeSelectQuery(String query, String expectedData, int column) throws SQLException {
		ResultSet result = null;
		boolean flag = false;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeQuery(query);
			while (result.getString(column).contains(expectedData)) {
				flag = true;
				break;
			}
		} catch (Exception e) {
		}
		return flag;
	}

	public String getSelectQuery(String query, int column) throws SQLException {
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery(query);
		return result.getString(column);
	}

	public int executeNonSelectQuery(String query) throws SQLException {
		int result = 0;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(query);
		} catch (Exception e) {
		}
		return result;
	}
}
