package com.devil.jdbc.oracle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SaveImage {

	private static final String INSERT_DETAILS_QUERY="INSERT INTO EMP_DETAILS VALUES(ID_SEQ.NEXVAL,?,?,?)";
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {

		try(Scanner scn=new Scanner(System.in)){
				String name=null,dept=null,photolocation=null;
					name="lapun";
					dept="java";
					photolocation="‪D:\\desktop\\debipdas.jpeg";
					try(InputStream is=new FileInputStream(photolocation)){
					
						try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");
								PreparedStatement ps=con.prepareStatement(INSERT_DETAILS_QUERY);){
							if(ps!=null) {//if
								ps.setString(1, name);
								ps.setString(2, dept);
								ps.setBlob(3, is);
							}//if
							//execute the query
							int count=0;
							if(ps!=null)
								count=ps.executeUpdate();
						//process the result
							if(count==0)
								System.out.println("Record not inserted.");
							else
								System.out.println("Record is not inserted");	
						}//try3
						
					}//try2
				}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in sql query.");
		}catch (Exception e) {
			e.printStackTrace();
		}
}//main
}//class