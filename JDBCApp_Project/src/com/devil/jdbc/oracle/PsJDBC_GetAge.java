package com.devil.jdbc.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//SQL> SELECT PID,NAME,DOB, TRUNC((SYSDATE-DOB)/365) AGE FROM PERSON_DETAILS WHERE PID=1000;
public class PsJDBC_GetAge {
	private static final String CALCULATE_AGE_QUERY = "SELECT TRUNC((SYSDATE-DOB)/365.5)  FROM PERSON_DETAILS WHERE PID=?";

	public static void main(String[] args) {
		Scanner scn = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			scn = new Scanner(System.in);
			
			//if (scn != null) {
				
			//}
			// class load
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish
			con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");

			// create preparestatement
			if (con != null)
				ps = con.prepareStatement(CALCULATE_AGE_QUERY);

			if(ps!=null && scn!=null)
				System.out.print("Enter Person Pid::");
			    int id = scn.nextInt();
				ps.setInt(1, id);
			
			// execute query
			if (ps != null)
				rs = ps.executeQuery();
          
			if (rs != null) {
				//boolean flag = false;
				//while (rs.next()) {
				//	flag = true;
				 // int PID = rs.getInt(1);
				if(rs.next()) {
			  float age = rs.getFloat(1);
			System.out.println("Person is age "+age);
			}
			else {
				System.out.println(" Person record is not found.");
			//	String Name = rs.getString(1);
				//	java.sql.Date DOB = rs.getDate(2);
					//int Age = rs.getInt(3);

				//	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					//String sdob = sdf.format(DOB);
					//System.out.println( Name + "  " + DOB + "  " + Age);
				//}
					//if(flag==false) 
				//		System.out.println("Record is not found.");
					

					// convert java.sql.Date to String Date
					//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					//String sdob = sdf.format(DOB);

					// print all of
					
						//System.out.println("Name     DOB    AGE(As per today)");
						//System.out.println("--------------------------------");
						
					//	System.out.println( Name + "  " + DOB + "  " + Age);
						
			
					
					

			}	// while
		} // if

		}catch(SQLException se)
	{
		se.printStackTrace();
	}catch(
	Exception e)
	{
		e.printStackTrace();
	}finally
	{
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
		try {
			if (scn != null)
				scn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// main
}
// class
