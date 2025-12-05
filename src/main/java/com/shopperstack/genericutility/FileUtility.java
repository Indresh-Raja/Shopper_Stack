package com.shopperstack.genericutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromPropetiesFile(String key)throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/ConfigData/env.config.data.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String data = prop.getProperty(key);
		return data;
	}
}
