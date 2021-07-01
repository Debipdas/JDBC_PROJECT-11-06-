package com.jdbc.project2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

//P_GET_EMP_DETAILS_BY_ID

public class CsProcedureTest2{
	
	private static final String CALL_PROCEDURE="{CALL P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";
	public static void main(String[] args) throws SQLException {
		int eno=0;
		try(Scanner sc=new Scanner(System.in)){
			if(sc!=null) {
				System.out.println("Enter EmpNo:::");
				 eno=sc.nextInt();
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
					 CallableStatement cs=con.prepareCall(CALL_PROCEDURE)){
				
			if(cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
			}
			if(cs!=null) {
				cs.setInt(1, eno);
			}
			if(cs!=null)
				cs.execute();
			
			if(cs!=null) {
				String name=cs.getString(2);
				String desg=cs.getString(3);
				float salary=cs.getFloat(4);
				
				System.out.println("name::"+name+" desg::"+desg+" salary::"+salary);
			}
			
			
			}//try2
		}//try1
		catch (SQLException se) {
            se.printStackTrace();
            System.out.println("nO DATA FOUND..");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class