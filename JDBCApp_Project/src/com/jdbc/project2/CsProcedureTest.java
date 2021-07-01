package com.jdbc.project2;

//CREATE OR REPLACE PROCEDURE FIRST_PRO(X IN NUMBER, Y IN NUMBER,Z OUT NUMBER) AS
//2  BEGIN
//3  Z:=X+Y;
//4  END;
//5  /

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTest {

	private static final String CALL_PROCEDURE="{CALL_FIRST_PRO(?,?,?)}";
	public static void main(String[] args) throws SQLException {

		try(Scanner sc=new Scanner(System.in)){
			System.out.println("Enter 1st value");
			int first=sc.nextInt();
			System.out.println("Enter 2nd");
			int second=sc.nextInt();
			
			
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
					//CREATE CALLBLESTATEMENT OBJECT
					CallableStatement cs=con.prepareCall(CALL_PROCEDURE);){
					if(cs!=null)
					cs.registerOutParameter(3,Types.INTEGER);
					if(cs!=null) {
						cs.setInt(1,first);
						cs.setInt(2,second);
					}
					if(cs!=null)
						cs.execute();
					
					int result=0;
					if(cs!=null)
						result=cs.getInt(3);
					
				
			}catch(SQLException se) {
				se.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}//main

}//class
