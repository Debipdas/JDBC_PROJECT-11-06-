package com.jdbc.project2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class CsProcedureTest5 {
	private static final String CALL_QUERY="{CALL P_GET_EMPS_BYNAME_INITIAL(?,?)}";
	public static void main(String[] args) throws SQLException {

		try(Scanner scn=new Scanner(System.in)){
			String initialchars=null;
			if(scn!=null) {
				System.out.println("Enter initial chars of employee name::");
				initialchars=scn.next()+"%";
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
					CallableStatement cs=con.prepareCall(CALL_QUERY)){
				
				if(cs!=null)
					cs.registerOutParameter(2, OracleTypes.CURSOR);
				if(cs!=null)
					cs.setString(1, initialchars);
				
				if(cs!=null)
					cs.execute();
				
				if(cs!=null) {
					ResultSet rs=(ResultSet)cs.getObject(2);
					System.out.println("The output is---");
					boolean flag=false;
					while(rs.next()) {
						flag =true;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));
					}
					if(flag==false)
						System.out.println("Record not found..");
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//main

}//class
