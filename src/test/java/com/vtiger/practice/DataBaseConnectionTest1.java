package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseConnectionTest1 {

	public static void main(String[] args) throws SQLException {
		  Connection connection = null;
		try {
		//step1: create object for driver class
		Driver driver=new Driver();
		
		//step2: Register the Driver with JDBC
		DriverManager.registerDriver(driver);
		
		//step3: establish the database connection
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet34", "root", "");
		 
		//step4: create statement
		Statement statement = connection.createStatement();
		
		//step5: execute query
		ResultSet result = statement.executeQuery("select * from sdet34");
		
		//step6: validate(based on testcase)
		while(result.next())
		{
			System.out.println(result.getInt("id"));
		}}
		catch(Exception e) {
		}finally {
		
		//step7: close the connection
		connection.close();
		

	}

}
}
