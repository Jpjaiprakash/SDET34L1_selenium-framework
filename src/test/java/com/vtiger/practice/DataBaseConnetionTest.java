package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseConnetionTest {

	public static void main(String[] args) throws SQLException {
		//step 1: Create object for for implementation class
		
		Driver driver=new Driver();
		
		//step2:  register the driver to jdbc
		
		DriverManager.registerDriver(driver);
		
		
	    //step 3: Establish the database connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SDET34", "root", "");
		
		//step 4: create statement
		Statement statement = connection.createStatement();
		
		//step 5: Execute query
		ResultSet result = statement.executeQuery("select * from SDET34;");
		
		//step 6: validate(based on testcase
		while(result.next())
		{
			System.out.println(result.getString("name"));
		}
		
		//step 7:close the connection
		connection.close();
		}
}

		
		
		
		



