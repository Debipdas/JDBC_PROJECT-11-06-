package com.devil.jdbc.oracle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

public class PsImageRetrive {
	private static final String IMAGE_RETRIVE = "SELECT ID,NAME,DEPT,IMAGE FROM EMP_DETAILS WHERE ID=?";

	public static void main(String[] args) throws SQLException, IOException {

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM",
				"TIGER"); 
				PreparedStatement ps = con.prepareStatement(IMAGE_RETRIVE);) {
			if (ps != null)
				ps.setInt(1, 124);

		try (ResultSet rs = ps.executeQuery()) {
				if (rs != null) {
					if (rs.next()) {
						int ID = rs.getInt(1);
						String NAME = rs.getString(2);
						String DEPT = rs.getString(3);
						System.out.println(ID + " " + NAME + " " + DEPT);
					}
					try (InputStream is = rs.getBinaryStream(4);
							OutputStream os = new FileOutputStream("retrive_image.jpeg");) {

						// copy BLOB col value to Destination file
						IOUtils.copy(is, os);
						System.out.println("Image retrived and store in your given location.");
					} // try4
				} // if
				else {

					System.out.println("Record not found");
				}
				
			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in sql query.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main
}// class