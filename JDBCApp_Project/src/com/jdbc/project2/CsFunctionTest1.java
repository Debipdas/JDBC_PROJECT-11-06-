package com.jdbc.project2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION GET_STUDENT_DETAILS_BYNO 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, CITY OUT VARCHAR2 
) RETURN FLOAT AS 
 PERCENTAGE FLOAT;
BEGIN
  SELECT NAME,CITY,AVG INTO  NAME,CITY,PERCENTAGE FROM STUDENT WHERE SNO=NO;
  RETURN PERCENTAGE;
  END GET_STUDENT_DETAILS_BYNO;*/

public class CsFunctionTest1 {
  private static final String CALL_QUERY="{?=CALL GET_STUDENT_DETAILS_BYNO(?,?,?)}";
	public static void main(String[] args) throws SQLException {

		try(Scanner scn=new Scanner(System.in)){
			int no=0;
			if(scn!=null) {
				System.out.println("ENTER STUDENT NO::");
				no=scn.nextInt();
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
                                                               CallableStatement cs=con.prepareCall(CALL_QUERY)){
				
				
				if(cs!=null) {
					cs.registerOutParameter(1, Types.FLOAT);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.VARCHAR);
				}
				if(cs!=null)
					cs.setInt(2, no);
				if(cs!=null)
					cs.execute();
				if(cs!=null) {
					System.out.println("Student name   ::"+cs.getString(3));
					System.out.println("Student address::"+cs.getString(4));
					System.out.println("Student avg    ::"+cs.getFloat(1));
				}
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}//main

}//class
