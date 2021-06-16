package com.devil.jdbc.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Oracle11toOracle19_Transfer {
	private static final String ORACLE11_INSERT_STUDENT = "INSERT INTO  STUDENT(NAME,CITY,AVG) VALUES(?,?,?)";
	private static final String ORACLE19_SELECT_STUDENT = "SELECT NAME,CITY,AVG FROM STUDENT";

	public static void main(String[] args) {

		Connection con1 = null,con2=null;
		
		Statement st=null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// CLASS LOAD
			//Class.forName("oracle.jbbc.driver.OracleDriver");

			// Establish
			con1 = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");
			con2 = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1523:prod", "SYSTEM", "LAPUN");

			// create statement
			if (con1 != null)
				st = con1.createStatement();
			if (con2 != null)
				ps = con2.prepareStatement(ORACLE11_INSERT_STUDENT);

			// Execute select query
			if (st != null)
				rs = st.executeQuery(ORACLE19_SELECT_STUDENT);
			// insert all data from oracle 11g to oracle 19g
			if (rs != null && ps != null) {
				while (rs.next()) {
					//int sno = rs.getInt(1);
					String NAME = rs.getString(1);
					String CITY = rs.getString(2);
					float AVG = rs.getFloat(3);

					ps.setString(1, NAME);
					ps.setString(2, CITY);
					ps.setFloat(3, AVG);

				} // while
				System.out.println("Records are copied successfully");
			} // if

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Records are not copied.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem in execution time.");
		} finally {
			// close jdbc objs
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
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
				if (con1 != null)
					con1.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con2 != null)
					con2.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} // finally

	}

}
