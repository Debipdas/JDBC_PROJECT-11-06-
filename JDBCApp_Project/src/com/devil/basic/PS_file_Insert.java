package com.devil.basic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PS_file_Insert {

	private static final String INSERT_DETAILS_QUERY = "INSERT INTO EMP_DETAILS VALUES(?,?,?,?)";

	public static void main(String[] args)  {

		try (Scanner scn = new Scanner(System.in)) {
			String name = null, dept = null, image = null;
			int id=0;
			
		if(scn!=null) {
			System.out.println("Enter Emp id:::");
		    id=scn.nextInt();
			System.out.println("Enter Emp name::");
			name = scn.next();
			System.out.println("Enter emp dept::");
			dept = scn.next();
			scn.nextLine();
			
			System.out.println("Enter image loc.::");
			 image =scn.nextLine
					 ();//"D://desktop//debipdas.jpeg";
		}
			// var in = Play.class.getResourceAsStream("/music/" + songName)
			try (InputStream is = new FileInputStream(image)) {
				// try(var in=SaveImage.class.getResourceAsStream(photolocation)){
     try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM","TIGER"); 
						PreparedStatement ps = con.prepareStatement(INSERT_DETAILS_QUERY);) {
					if (ps != null) {// if
						ps.setInt(1, id);
						ps.setString(2, name);
						ps.setString(3, dept);
						ps.setBlob(4, is);
					} // if
						// execute the query
					int count = 0;
					if (ps != null)
						count = ps.executeUpdate();
					// process the result
					if (count == 0)
						System.out.println("Record not inserted.");
					else
 						System.out.println("Record is  inserted");
				} // try3

			} // try2
		} // try1
		catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in sql query.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main
}// class
