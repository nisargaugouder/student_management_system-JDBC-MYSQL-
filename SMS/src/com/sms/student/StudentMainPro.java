package com.sms.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentMainPro {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/sms_db";
		Connection con=DriverManager.getConnection(url,"root","123456");
		
		while(true) {
			intro();
		Scanner s=new Scanner(System.in);
		int operations;
		System.out.println("enter your operation:");
		operations=s.nextInt();
		//switch case
		switch(operations) {
		case 1:
			System.out.println("------------------------------------------");
			System.out.println("*  *  *         INSERT             *  *  *");
			System.out.println("------------------------------------------");
			insert();
			break;
		case 2:
			System.out.println("------------------------------------------");
			System.out.println("*  *  *          EDIT              *  *  *");
			System.out.println("------------------------------------------");
			edit();
			break;
		case 3:
			System.out.println("------------------------------------------");
			System.out.println("*  *  *          VIEW              *  *  *");
			System.out.println("------------------------------------------");
			view();
			break;
		case 4:
			System.out.println("------------------------------------------");
			System.out.println("*  *  *           DELETE         *  *  *  ");
			System.out.println("------------------------------------------");
			delete();
			break;
		case 5:
			System.out.println("------------------------------------------");
			System.out.println("*  *  *          EXIT              *  *  *");
			System.out.println("------------------------------------------");
			System.exit(0);
		default:
			System.out.println("Enter the valid Number..");
		}	
	}
	}
	public static void view() throws SQLException {
		//VIEW THE RECORD
				String url1="jdbc:mysql://localhost:3306/sms_db";
				Connection con1=DriverManager.getConnection(url1,"root","123456");
				
				Statement st=con1.createStatement();
				ResultSet rs=st.executeQuery("select * from student_info ");
				System.out.println("ID |  NAME  | STD  |  FATHER  |   MOBILE");
				System.out.println("****************************************");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"  |  "+rs.getString(2)+"  |  "+rs.getString(3)+"  |  "+rs.getString(4)+"  |  "+rs.getString(5));
				}
	}
	
	public static void edit() throws SQLException {
		String url="jdbc:mysql://localhost:3306/sms_db";
		Connection con=DriverManager.getConnection(url,"root","123456");
		//Edit
		
		String query="UPDATE student_info SET Name = ?, std =?, fname = ?, mobile = ? WHERE(id =?);";
		PreparedStatement ps=con.prepareStatement(query);
		
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the id to Edit:  ");
		int i=s.nextInt();
		s.nextLine();
		System.out.println("enter your name:");
		String n=s.nextLine();
		System.out.println("Enter your Class:  ");
		String c=s.nextLine();
		System.out.println("Enter your father Name:  ");
		String f=s.nextLine();
		System.out.println("Enter your Mobile No:  ");
		String m=s.nextLine();
		
		
		ps.setString(1, n);
		ps.setString(2, c);
		ps.setString(3, f);
		ps.setString(4, m);
		ps.setInt(5, i);
		ps.executeUpdate();
		System.out.println("data updated sucessfully");
		
	}

	public static void insert() throws SQLException {
		//step2 :connection
		Scanner s=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/sms_db";
		Connection con=DriverManager.getConnection(url,"root","123456");
		System.out.println("enter your name:");
		String n=s.nextLine();
		System.out.println("Enter your Class:  ");
		String c=s.nextLine();
		System.out.println("Enter your father Name:  ");
		String f=s.nextLine();
		System.out.println("Enter your Mobile No:  ");
		String m=s.nextLine();
		//step3 :query
		String query="insert into student_info(name,std,fname,mobile) value"
		               +"(?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, n);
		ps.setString(2, c);
		ps.setString(3, f);
		ps.setString(4, m);
		ps.executeUpdate();
		System.out.println("Data Inserted sucefully...");//confirmation
		
	}

	public static void delete() throws SQLException {
		//Delete the record
		String url2="jdbc:mysql://localhost:3306/sms_db";
		Connection con2=DriverManager.getConnection(url2,"root","123456");
				String query="delete from student_info where id=? ";
				PreparedStatement ps=con2.prepareStatement(query);
				
				Scanner s=new Scanner(System.in);
				System.out.println("select id to delete");
				int id=s.nextInt();
				
				ps.setInt(1, id);
				ps.executeUpdate();
				System.out.println("Data deleted sucessfully...");
		
	}
    
	public static void intro() {
		System.out.println("********************************************");
		System.out.println("------       STUDENT MODULE       ----------");
		System.out.println("********************************************");
		System.out.println("\n  1.  Insert  ");
		System.out.println("\n  2.  Edit  ");
		System.out.println("\n  3.  View  ");
		System.out.println("\n  4.  Delete  ");
		System.out.println("\n  5.  Stop  ");
		System.out.println("---------------------------------------------");
	}

}
