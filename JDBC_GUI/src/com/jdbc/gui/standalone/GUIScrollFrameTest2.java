package com.jdbc.gui.standalone;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIScrollFrameTest2 extends JFrame implements ActionListener,WindowListener {
	private static final  String  GET_EMP_QUERY="SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	private JLabel lempno,lename,ljob,lsal;
	private JTextField tempno,tename,tjob,tsal;
	private JButton  bFirst,bLast,bPrevious,bNext;
	private Connection con;
	private PreparedStatement ps;
	private  ResultSet rs;

	//constructor
	public GUIScrollFrameTest2() {
		System.out.println("GUIScrollFrameTest:: 0-param constructor(start)");
	  setTitle("GUIFrontEnd-Scroll Frame");
	  setSize(500,400);
	  setBackground(Color.RED);
	  setForeground(Color.cyan);
	  setLayout(new FlowLayout());

	  //add comps
	   lempno=new JLabel("empsno");
	  add(lempno);
	  tempno=new JTextField(50);
			add(tempno);

	   lename=new JLabel("ename");
	  add(lename);
	   tename=new JTextField(50);
		add(tename);

		 ljob=new JLabel("job");
		  add(ljob);
		 tjob=new JTextField(50);
			add(tjob);


		 lsal=new JLabel("Emp sal");
		  add(lsal);
		 tsal=new JTextField(50);
		add(tsal);

		 bFirst=new JButton("First");
		 bNext=new JButton("next");
		 bPrevious=new JButton("previous");
		 bLast=new JButton("Last");
		add(bFirst); add(bNext); add(bPrevious); add(bLast);

		//register and activate ActionListener for all the 4 buttons
		  bFirst.addActionListener(this);
		  bNext.addActionListener(this);
		  bPrevious.addActionListener(this);
		  bLast.addActionListener(this);

       //add WindowListener to frame
		  this.addWindowListener(this);

		  //disable editing on Text boxes
		  tempno.setEditable(false);
		  tename.setEditable(false);
		  tjob.setEditable(false);
		  tsal.setEditable(false);

	  setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			   // the closing of  frame window will terminates Application . 
            initializeJDBC();
     System.out.println("GUIScrollFrameTest:: 0-param constructor(end)");           
	}

	private  void  initializeJDBC() {
		System.out.println("GUIScrollFrameTest.initializeJDBC()");
		try {
			//establis the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-DRTQSH7:1522:xe","SYSTEM","TIGER");
			//create PreparedStatement obj
			PreparedStatement ps=con.prepareStatement(GET_EMP_QUERY,
					                                                                         ResultSet.TYPE_SCROLL_INSENSITIVE,
					                                                                         ResultSet.CONCUR_UPDATABLE);
			//prepare Scrollable RS obj
			rs=ps.executeQuery();
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//initializeJDBC


	public static void main(String[] args) {
		System.out.println("GUIScrollFrameTest.main() (Stat)");
		  new GUIScrollFrameTest2();  //anonymous object
		  System.out.println("end of mind metod");
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUIScrollFrameTest.actionPerformed()");
		boolean flag=false;
		if(ae.getSource()==bFirst) {
			try {
			rs.first();
			flag=true;
			System.out.println("First button is clicked");
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		else if(ae.getSource()==bNext) {
			System.out.println("next button is clicked");

		try {
			 if(!rs.isLast()) {
				rs.next();
			 flag=true;
			 }

				}
				catch(SQLException se) {
					se.printStackTrace();
				}
		}
		else if(ae.getSource()==bPrevious) {
			System.out.println("Previous button is clicked");
			try {
			if(!rs.isFirst()) {
				rs.previous();
				flag=true;
			  }
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		else {
			System.out.println("Last button is clicked");
			try {
				rs.last();
				flag=true;
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//else

		if(flag==true) {
			try {
			tempno.setText(rs.getString(1));
			tename.setText(rs.getString(2));
			tjob.setText(rs.getString(3));
			tsal.setText(rs.getString(4));
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//if


	}//acton peformed

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
	 System.out.println("GUIScrollFrameTest.windowClosing()");
	 try {
		 if(rs!=null)
			 rs.close();
	 }
	 catch(SQLException se) {
		 se.printStackTrace();
	 }
	 try {
		 if(ps!=null)
			 ps.close();
	 }
	 catch(SQLException se) {
		 se.printStackTrace();
	 }
	 try {
		 if(con!=null) {
			 con.close();
			 System.out.println("GUIScrollFrameTest.windowClosing() :: JDBC con is closed");
		 }
	 }
	 catch(SQLException se) {
		 se.printStackTrace();
	 }

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}


}//class