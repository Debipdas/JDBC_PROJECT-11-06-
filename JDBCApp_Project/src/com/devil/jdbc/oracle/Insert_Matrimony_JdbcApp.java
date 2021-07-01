package com.devil.jdbc.oracle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Insert_Matrimony_JdbcApp {

	private static final String INSERT_QUERY = "INSERT INTO MARTIMONY_DETAILS VALUES(CID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";

	public static void main(String[] args) throws ParseException, SQLException, FileNotFoundException, IOException {

		try (Scanner scn = new Scanner(System.in);) {
			String name = null, gender = null, dob = null, photo_loc = null, resume_loc = null, doj = null,
					biodata_loc = null, audio_file = null, video_file = null;
			int id = 0;
			if (scn != null) {
				//System.out.println("Enter candiate id::");
				//id = scn.nextInt();
				System.out.println("Enter candiate name::");
				name = scn.next();
				System.out.println("Enter candiate gender::");
				gender = scn.next();
				System.out.println("Enter candiate DOB(dd-mm-yyyy)::");
				dob = scn.next();
				scn.nextLine();
				//System.out.println("Enter photo location for upload:");//C:\\Users\\HP\\Desktop\\MATRI_APP\\image.jpeg
				photo_loc ="C:\\Users\\HP\\Desktop\\MATRI_APP\\image.jpeg";// scn.next();
				//System.out.println("Enter resume location for uplod:");//C:\\Users\\HP\\Desktop\\MATRI_APP\\resume.docx
				resume_loc ="C:\\Users\\HP\\Desktop\\MATRI_APP\\resume.docx";// scn.next();
				System.out.println("Enter candiate DOJ(yyyy-mm-dd)::");
				doj = scn.next();
				//System.out.println("Upload your biodata:::::::::::::");//C:\\Users\\HP\\Desktop\\MATRI_APP\\biodata.txt
				biodata_loc ="C:\\Users\\HP\\Desktop\\MATRI_APP\\biodata.txt";// scn.next();
				//System.out.println("Upload your audio file::::::::::");//C:\\Users\\HP\\Desktop\\MATRI_APP\\song.mp3
				audio_file ="C:\\Users\\HP\\Desktop\\MATRI_APP\\song.mp3";// scn.next();
				//System.out.println("Upload your video file::::::::::");//C:\\Users\\HP\\Desktop\\MATRI_APP\\video.mp4
				video_file ="C:\\Users\\HP\\Desktop\\MATRI_APP\\video.mp4"; //scn.next();
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");// dob date convert to string to sql date
			java.util.Date ud1 = sdf1.parse(dob);
			long ms = ud1.getTime();
			java.sql.Date sd1 = new java.sql.Date(ms);

			java.sql.Date sd2 = java.sql.Date.valueOf(doj);// doj date convert into sql date

			try (InputStream photo = new FileInputStream(photo_loc)) {
				try (InputStream audio = new FileInputStream(audio_file)) {
					try (InputStream video = new FileInputStream(video_file)) {
						try (Reader read1 = new FileReader(resume_loc)) {
							try (Reader read2 = new FileReader(biodata_loc)) {

								try (Connection con = DriverManager
										.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe", "SYSTEM", "TIGER");
										PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
									if (ps != null) {
										ps.setString(1, name);
										ps.setString(2, gender);
										ps.setDate(3, sd1);
										ps.setBlob(4, photo);
										ps.setClob(5, read1);
										ps.setDate(6, sd2);
										ps.setClob(7, read2);
										ps.setBlob(8, audio);
										ps.setBlob(9, video);

									} // if
									int count = 0;
									if (ps != null)
										count = ps.executeUpdate();
									if (count == 0) {
										System.out.println("Record is not inserted.");
									} else {
										System.out.println("Record is inserted.");
									}

								}//try 1

							}//try 2
						}//try 3

					}//try 4
				}//try 5
			} //try 6
			catch (SQLException se) {
				se.printStackTrace();
				System.out.println("Problem in sql query .");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}// main

}// class