package com.sdet34L1.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains only property file specific common methods
 * @author User
 *
 */

public class FileUtility {

	static Properties property;
	
	/**
	 * This method is used to open the property File
	 * @throws IOException 
	 */
	
	public static void openPropertyFile(String filePath) throws IOException {
	FileInputStream fis=new FileInputStream(filePath);
	property=new Properties();
	property.load(fis);
	}
	
	
	/**
	 * This method is used to fetch the data from the Property File
	 * 
	 */
	
	public static  String getDataFromPropertyFile(String Key) {
		String value=property.getProperty(Key);
		return value;
		

		

	}

}
