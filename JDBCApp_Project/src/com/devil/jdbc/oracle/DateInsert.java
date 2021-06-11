package com.devil.jdbc.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsert {

	private static final String INSERT_DATE_QUERY = "INSERT INTO PERSON_DETAILS VALUES(PID_SEQ.NEXTVAL,?,?,?,?)";

	public static void main(String[] args) {

		Scanner scn = null;

		Connection con = null;
		PreparedStatement ps = null;
		try {
			String Name = null, DOB = null, DOJ = null, DOM = null;
			scn = new Scanner(System.in);

			if (scn != null) {
				System.out.print("Enter person name             ::");
				Name = scn.next();
				System.out.print("Enter person DOB(dd-MM-yyyy)  ::");
				DOB = scn.next();
				System.out.print("Enter person DOJ(MMM-dd-yyyy) ::");
				DOJ = scn.next();
				System.out.print("Enter person DOM(yyyy-MM-dd)  ::");
				DOM =scn.next();

			}
			// convert String date value to java.util.Date
		// DOB
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date ud1 = sdf1.parse(DOB);
			long ms = ud1.getTime();
			java.sql.Date sd1 = new java.sql.Date(ms);
		// DOJ
			SimpleDateFormat sdf2 = new SimpleDateFormat("MMM-dd-yyyy");
			java.util.Date ud2 = sdf2.parse(DOJ);
			ms = ud2.getTime();
			java.sql.Date sd2 = new java.sql.Date(ms);
 		// DOM
			java.sql.Date sd3 = java.sql.Date.valueOf(DOM);

			// load class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
//@DESKTOP-DRTQSH7
			// established
			con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");

			// create preparestatement
			if (con != null)
				ps = con.prepareStatement(INSERT_DATE_QUERY);

			// set value to query
			if (ps != null) {
				ps.setString(1, Name);
				ps.setDate(2, sd1);
				ps.setDate(3, sd2);
				ps.setDate(4, sd3);
			}
			// execute query
			int count = 0;
			if (ps != null)
				count = ps.executeUpdate();
			if (count == 0) {
				System.out.println("Record not inserted");
			} else {
				System.out.println("Record inserted.");
			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in sql query or date format.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (scn != null)
					scn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
	}// main

}// class
