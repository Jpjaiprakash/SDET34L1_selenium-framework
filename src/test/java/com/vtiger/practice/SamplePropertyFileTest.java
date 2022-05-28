package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;

public class SamplePropertyFileTest {

	public static void main(String[] args) throws IOException {
		
		JavaUtility jutil=new JavaUtility();
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		String url = FileUtility.getDataFromPropertyFile("url");
		String username =FileUtility.getDataFromPropertyFile("username");
		String password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");
		
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		System.out.println(timeout);
		System.out.println(browser);
		
		
		
		
		

	}

}
