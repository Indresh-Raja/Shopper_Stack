package com.shopperstack.genericutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImp implements IRetryAnalyzer{
	int count=0;
	int lastCount=5;
	@Override
	public boolean retry(ITestResult result) {
		if(count<lastCount) {
			count++;
			return true;
		}
		return false;
	} 
}
