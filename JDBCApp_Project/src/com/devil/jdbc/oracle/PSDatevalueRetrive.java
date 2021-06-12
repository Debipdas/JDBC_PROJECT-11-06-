package com.devil.jdbc.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PSDatevalueRetrive {
	private static final String DATE_RETRIVE_QUERY = "SELECT PID,NAME,DOB,DOJ,DOM FROM PERSON_DETAILS";

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// load class		//	Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish
			con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");

			// create prepare statement
			if (con != null)
				ps = con.prepareStatement(DATE_RETRIVE_QUERY);
			// Execute query
			if (ps != null)
				rs = ps.executeQuery();

			// process sql query
			if (rs != null) {
				while (rs.next()) {
					int PID = rs.getInt(1);
					String Name = rs.getString(2);
					java.sql.Date DOB = rs.getDate(3);
					java.sql.Date DOJ = rs.getDate(4);
					java.sql.Date DOM = rs.getDate(5);
					// convert java.sql.date values to string value

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dob = sdf.format(DOB);
					String doj = sdf.format(DOJ);
					String dom = sdf.format(DOM);
					// print all details
					System.out.println("PID    NAME      DOB         DOJ         DOM");
					System.out.println("------------------------------------------------------");
					System.out.println(PID + "  " + Name + "  " + DOB + "  " + DOJ + "  " + DOM);

				} // while
			} // if

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		} // final

	}// main
}// class
