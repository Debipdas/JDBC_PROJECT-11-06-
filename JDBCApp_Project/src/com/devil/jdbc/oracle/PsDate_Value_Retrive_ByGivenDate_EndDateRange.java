package com.devil.jdbc.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDate_Value_Retrive_ByGivenDate_EndDateRange {

	private static final String DATE_RETRIVE_QUERY = "SELECT PID,NAME,DOB,DOJ,DOM FROM PERSON_DETAILS WHERE DOB>=? AND DOB<=?";

	public static void main(String[] args) {

		Scanner scn = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			scn = new Scanner(System.in);
			String sd = null, ed = null;
			if (scn != null) {
				System.out.print("Enter Start Date of DOB::");
				sd = scn.next();
				System.out.print("Enter End   Date of DOB::");
				ed = scn.next();
			} // if
				// converting String date value to java.sql.utilDate obj
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Date sdob = new java.sql.Date(sdf.parse(sd).getTime());
			java.sql.Date edob = new java.sql.Date(sdf.parse(ed).getTime());

			// load class
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish
			con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");

			// create prepare statement
			if (con != null)
				ps = con.prepareStatement(DATE_RETRIVE_QUERY);
			// set value for query param
			if (ps != null) {
				ps.setDate(1, sdob);
				ps.setDate(2, edob);
			}
			// Execute query

			if (ps != null)
				rs = ps.executeQuery();

			if (rs != null) {
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					int PID = rs.getInt(1);
					String Name = rs.getString(2);
					java.sql.Date sqdob = rs.getDate(3);
					java.sql.Date sqdoj = rs.getDate(4);
					java.sql.Date sqdom = rs.getDate(5);

					// print details
					System.out.println(PID + " " + Name + " " + sqdob + " " + sqdoj + " " + sqdom);
				} // while
			} // if
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

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
		}

	}// main
}// class
