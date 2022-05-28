package com.sdet34L1.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebElement;

/**
 * This class contains only java specific reusable methods
 * @author User
 *
 */

public class JavaUtility {
	/**
	 * This method is used to convert String value to long datatype
	 */
	public long stringToLong(String value) {
		return Long.parseLong(value);
	}
	
	/**
	 * This method used to get the random Number
	 */
	public int getRandomNumber(int limit) {
		Random ran=new Random();
		return ran.nextInt(limit);
	}
	
	/**
	 * This method is used to print the message
	 */
	public void printStatement(String message) {
		System.out.println(message);
	}
	
	public void assertionThroughIfCondition(String actualResult, String expectedResult,String testCaseName) {
		if(actualResult.equalsIgnoreCase(expectedResult)) {
			System.out.println(testCaseName+"created Successfully");
			System.out.println("TC Pass");
		}
	}
		
			
	public void customWait(WebElement element, long pollingTime, int duration) throws InterruptedException {
		int count=0;
		while(count<=duration) {
			try {
			element.click();
			break;
		}
			catch(Exception e) {
		Thread.sleep(pollingTime);
		count++;
	}
}
		}
	
	public String dateTimeInFormat() {
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_sss").format(new Date());
	}
	}
	
