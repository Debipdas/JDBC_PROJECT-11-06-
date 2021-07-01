package com.jdbc.project2;
//Student Table get Student details 

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE STUDENT_PRO 
(
  SNO IN NUMBER 
, NAME OUT VARCHAR2 
, CITY OUT VARCHAR2 
, AVG OUT NUMBER 
) AS 
BEGIN
  SELECT NAME,CITY,AVG INTO NAME,CITY,AVG FROM STUDENT WHERE SNO=SNO;
END STUDENT_PRO;*/

public class CsProcedureTest3 {
private static final String Student_query="{CALL STUDENT_PRO(?,?,?,?)}";
	public static void main(String[] args) {
		int sno=0;
		try(Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter Student no:::");
				 sno=sc.nextInt();
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
					 CallableStatement cs=con.prepareCall(Student_query)){
				
			if(cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
			}
			if(cs!=null) {
				cs.setInt(1, sno);
			}
			if(cs!=null)
				cs.execute();
			
			if(cs!=null) {
				String NAME=cs.getString(2);
				String CITY=cs.getString(3);
				float AVG=cs.getFloat(4);
				
				System.out.println("name::"+NAME+" desg::"+CITY+" salary::"+AVG);
			}
			
			
			}//try2
		}//try1
		catch (SQLException se) {
            se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
