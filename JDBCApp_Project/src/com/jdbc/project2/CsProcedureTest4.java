package com.jdbc.project2;
//IRCTC USERNAME AND PASSWORD..
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTest4 {
 private static final String QUERY="{CALL IRCTC_PRO(?,?,?)}";
	public static void main(String[] args) throws SQLException {

		try(Scanner sc=new Scanner(System.in)){
			String username=null,password=null;
			if(sc!=null) {
				System.out.println("Enter username::");
				username=sc.next();
				System.out.println("Enter password::");
				password=sc.next();
			}
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
					CallableStatement cs=con.prepareCall(QUERY)){
				
			
			if(cs!=null)
				cs.registerOutParameter(3, Types.VARCHAR);
			if(cs!=null) {
				cs.setString(1, username);
				cs.setString(2, password);
			}
			if(cs!=null)
				cs.execute();
			String result=null;
			if(cs!=null)
				result=cs.getString(3);
			System.out.println(result);
			
			}
		}

		catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
