package com.devil.basic;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestInsert {

	private static final String JOB_SEEKER_QUERY = "INSERT INTO JOB_SEEKER VALUES(?,?,?)";

	public static void main(String[] args)  {

		try (Scanner scn = new Scanner(System.in)) {
			String jsname = null, resume = null;
			int jsid=0;
			
		if(scn!=null) {
		   System.out.println("Enter JOB SEEKER id:::");
		    jsid=scn.nextInt();
			System.out.println("Enter JOB SEEKER name::");
			jsname = scn.next();
			scn.nextLine();
			
			System.out.println("Enter RESUME loc.::");
			 resume =scn.nextLine().replace("?", "");
				//"D://desktop//debipdas.jpeg";
		}
			// var in = Play.class.getResourceAsStream("/music/" + songName)
			try (Reader read = new FileReader(resume)) {
				
				// try(var in=SaveImage.class.getResourceAsStream(photolocation)){
     try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM","TIGER"); 
						PreparedStatement ps = con.prepareStatement(JOB_SEEKER_QUERY);) {
					if (ps != null) {// if
						ps.setInt(1, jsid);
						//ps.setClob(2, );
						ps.setString(2, jsname);
						ps.setCharacterStream(3, read);
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
