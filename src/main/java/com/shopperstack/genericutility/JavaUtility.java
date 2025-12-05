package com.shopperstack.genericutility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import org.openqa.selenium.interactions.Actions;

public class JavaUtility {
	public int getRandomNumber() {
		Random random=new Random();
		int randomInt = random.nextInt(7777);
		return randomInt;
	}
	public String getSystemDate() {
		String date = LocalDate.now().toString();
		return date;
	}
	public String getRequiredDate(int days) {
		String reqDate = LocalDate.now().plusDays(days).toString();
		return reqDate;
	}
	
	public String getSystemTime() {
		String time = LocalDateTime.now().toString().replace(":","-");
		return time;
	}
	
}
