package com.carmada.drivers.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcURL="jdbc:mysql://localhost:3306/driver-database?useSSL=false&serverTimeZone=UTC";
		String user = "admincarmada";
		String password = "admincarmada";
		
		try {
			
			System.out.println("Connecting to database: " + jdbcURL);
			
			Connection myConn = DriverManager.getConnection(jdbcURL, user, password);
			
			System.out.println(myConn.toString());
			System.out.println("Connection Successful!!");
			
		} catch (Exception exc){
			exc.printStackTrace();
			
		}
	}

}
