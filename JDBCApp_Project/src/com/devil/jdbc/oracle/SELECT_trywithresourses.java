package com.devil.jdbc.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SELECT_trywithresourses {

	public static void main(String[] args) throws SQLException {

		try(
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
				){
			if(rs!=null) {
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
				if(flag=false)
					System.out.println("Record is not found.");
				
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
           e.printStackTrace();
		}
	}

}
