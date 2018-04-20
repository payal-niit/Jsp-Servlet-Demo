package com.niit.project.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {
		
	
	public static Connection createConnection() {
		Properties props = new Properties();
		FileInputStream fis = null;
		Connection connection = null;	

		try {
			/*fis = new FileInputStream("db.properties");
			props.load(fis);
*/
			// load the Driver Class
			//Class.forName(props.getProperty("jdbc.driver"));
			Class.forName("org.h2.Driver");
			// create the connection now
			/*connection = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"),
					props.getProperty("jdbc.password"));*/
			connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/myDB", "sa", "");
			System.out.println("Connected");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}