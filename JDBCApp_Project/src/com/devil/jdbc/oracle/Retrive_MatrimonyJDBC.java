package com.devil.jdbc.oracle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class Retrive_MatrimonyJDBC {

	private static final String SELECT_QUERY = "SELECT CID,CNAME,GENDER,DOB,PHOTO,RESUME,DOJ_FIRSTJOB,BIODATA,AUDIOINFO,VIDEOINFO FROM MARTIMONY_DETAILS WHERE CID=?";

	public static void main(String[] args) throws ParseException, SQLException, FileNotFoundException, IOException {

		try (Scanner scn = new Scanner(System.in);) {
			int cid = 0;
			if (scn != null) {
				System.out.println("Enter candiate id::");
				cid = scn.nextInt();
			}

//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//			String dob = sdf.format(DOB);
//			String doj = sdf.format(DOJ);
//			try (InputStream photo = new FileInputStream(photo_loc)) {
//				try (InputStream audio = new FileInputStream(audio_file)) {
//					try (InputStream video = new FileInputStream(video_file)) {
//						try (Reader read1 = new FileReader(resume_loc)) {
//							try (Reader read2 = new FileReader(biodata_loc)) {

			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM",
					"TIGER"); PreparedStatement ps = con.prepareStatement(SELECT_QUERY)) {
				if (ps != null) {
					ps.setInt(cid, 1);

					try (ResultSet rs = ps.executeQuery()) {
						if (rs != null) {
							if (rs.next()) {
								int id = rs.getInt(1);
								String name = rs.getString(2);
								String gender = rs.getString(3);
								java.sql.Date DOB = rs.getDate(4);
								java.sql.Date DOJ = rs.getDate(7);

								SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
								String dob = sdf.format(DOB);
								String doj = sdf.format(DOJ);

								System.out.println(id + "  " + name + "   " + gender + "   " + dob + "   " + doj);

								try (InputStream image = rs.getBinaryStream(5);
										OutputStream os = new FileOutputStream("retrive_image.jpeg");) {
									IOUtils.copy(image, os);
									System.out.println("Image retrived and store in your given location.");
								}

								try (Reader reader = rs.getCharacterStream(6);
										Reader reader1 = rs.getCharacterStream(8);
										Writer writer = new FileWriter("retrive_resume.txt");
										Writer writer1 = new FileWriter("retrive_biodata.txt")) {

									IOUtils.copy(reader, writer);
									IOUtils.copy(reader1, writer1);
									System.out.println("Resume and Biodata retrived and store in your given location.");
								}

								try (InputStream audio = rs.getBinaryStream(9);
										OutputStream os1 = new FileOutputStream("retrive_audio.mp3");) {
									IOUtils.copy(audio, os1);
									System.out.println("Audio file retrived and store in your given location.");
								}
								try (InputStream video = rs.getBinaryStream(10);
										OutputStream os2 = new FileOutputStream("retrive_video.mp4");) {
									IOUtils.copy(video, os2);
									System.out.println("Video file retrived and store in your given location.");

								}

								System.out.println("Successfully completed!!!!");
							} // try 2
						} // try 3

					} // try 4
				} // try 5
			} // try 6
			catch (SQLException se) {
				se.printStackTrace();
				System.out.println("Problem in sql query .");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// main

}// class