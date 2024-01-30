package Java.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MarksheetModel {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		int key;
	try {
		//Connection Establish
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();				
	do {
	System.out.println("Please Select the option you want perform :- ");
	System.out.println("Press 1 for Add New Marksheet - ");
	System.out.println("Press 2 for Update Marksheet - ");
	System.out.println("Press 3 for Delete Marksheet -");
	System.out.println("Press 4 for Get Marksheet - ");
	System.out.println("Press 5 for Get MaritList - ");
	System.out.println("Press 0 for Exit ");
	System.out.print("Enter here : ");
	 key = scan.nextInt();
	
	switch (key) {
	case 1:
		System.out.println("Add Successful " + addMarksheet(stmt));
		System.out.println("-----------------------------------------------------");
		break;
	case 2:
		System.out.println("updateMarksheet successful " + updateMarksheet());
		System.out.println("-----------------------------------------------------");
		break;
	case 3:
		System.out.println("deleteMarksheet Successful " + deleteMarksheet());
		System.out.println("-----------------------------------------------------");
		break;
	case 4:
		getMarksheet(stmt);
		System.out.println("-----------------------------------------------------");
		break;
	case 5:
		getMaritList();
		System.out.println("-----------------------------------------------------");
		break;
	default:
		System.out.println("Please Enter Valid Input...............");
		break;
		
	}
	}while(key!=0);
	
	stmt.close();
	con.close();
	} catch ( SQLException e) {
		e.printStackTrace();
	}
	
		
	}
	
	public static int addMarksheet(Statement stmt) {
		int addmarksheet =0;
		Marksheet marks = new Marksheet();
		System.out.print("Enter Roll No : ");
		marks.setRollNo(scan.next());
		System.out.print("Enter Your Name : ");
		marks.setName(scan.next());
		System.out.print("Enter Chemistry Marks  : ");
		marks.setChemistry(scan.nextInt());
		System.out.print("Enter Physics Marks : ");
		marks.setPhysics(scan.nextInt());
		System.out.print("Enter Maths Marks  : ");
		marks.setMaths(scan.nextInt());
		
		long total = marks.getChemistry()+marks.getPhysics()+marks.getMaths();
		
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps=
							con.prepareStatement("INSERT INTO marksheet values(?,?,?,?,?,?)");
			ps.setString(1, marks.getRollNo());
			ps.setString(2, marks.getName());
			ps.setInt(3, marks.getChemistry());
			ps.setInt(4, marks.getPhysics());
			ps.setInt(5, marks.getMaths());
			ps.setLong(6, total);
			
			addmarksheet = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addmarksheet;
	}
	
	public static int updateMarksheet() {
		int updatemarksheet =0;
		Marksheet marks = new Marksheet();
		System.out.print("Please Enter Old RollNumber : ");
		marks.setRollNo(scan.next());
		
		out:
			while(true) {
		System.out.println("please chose option permforme : ");
		System.out.println("Press 1 for Change Name ");
		System.out.println("Press 2 for Change Chemistry marks  ");
		System.out.println("Press 3 for Change physics marks  ");
		System.out.println("Press 4 for Change Maths marks ");
		System.out.println("Press 0 for Exit");
		
		System.out.print("Enter Here : ");
		int key = scan.nextInt();
		
		switch (key) {
		case 1:
			System.out.print("Enter Your New Name : ");
			String newName = scan.next();
			
			
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement ps  = 
							con.prepareStatement("UPDATE marksheet SET name = ?  WHERE rollnumber = ?");
				ps.setString(1, newName);
				ps.setString(2, marks.getRollNo());
				
				updatemarksheet = ps.executeUpdate();
			con.close();
			ps.close();
			System.out.println("Name insert Successfull...............");
			
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case 2:
			
			System.out.print("Enter Your New Chemistry Marks : ");
			int newChemistryMarks = scan.nextInt();
			
			try {
				Connection con = DBConnection.getConnection();  
				
				
				PreparedStatement pss = 
							con.prepareStatement("Update marksheet set total = ? + physics + maths where rollnumber = ? ");
				
				pss.setInt(1, newChemistryMarks);	
				pss.setString(2, marks.getRollNo());
				PreparedStatement ps  = 
								con.prepareStatement("UPDATE marksheet SET chemistry = ?  WHERE rollnumber = ?");
				
				ps.setInt(1, newChemistryMarks);
				ps.setString(2, marks.getRollNo());
				
				updatemarksheet = ps.executeUpdate();
				pss.executeUpdate();
				
				con.close();
				ps.close();
				pss.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case 3:
			System.out.print("Enter Your New Physics Marks  : ");
			int newPhysicsMarks = scan.nextInt();
			try {
				Connection con = DBConnection.getConnection();
				PreparedStatement pss = 
						con.prepareStatement("Update marksheet set total = chemistry + ?  + maths where rollnumber = ? ");
				
				pss.setInt(1, newPhysicsMarks);
				pss.setString(2, marks.getRollNo());
				
				PreparedStatement ps  = 
							con.prepareStatement("UPDATE marksheet SET physics = ?  WHERE rollnumber = ?");
				ps.setInt(1, newPhysicsMarks);
				ps.setString(2, marks.getRollNo());
				
				updatemarksheet = ps.executeUpdate();
				
				pss.executeUpdate();
			con.close();
			ps.close();
			ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			break;
			
		case 4:
			System.out.print("Enter your New Maths Marks : ");
			int newMathsMarks = scan.nextInt();
			
			Connection con = DBConnection.getConnection();
			try {
				PreparedStatement pss = 
						con.prepareStatement("Update marksheet set total = chemistry + physics + ? where rollnumber = ? ");
				
				pss.setInt(1, newMathsMarks);
				pss.setString(2, marks.getRollNo());
				
				PreparedStatement ps = 
						con.prepareStatement("UPDATE marksheet SET maths = ? WHERE rollnumber = ? ");
				ps.setInt(1, newMathsMarks);
				ps.setString(2, marks.getRollNo());
				
				updatemarksheet = ps.executeUpdate();
				pss.executeUpdate();
				
				con.close();
				ps.close();
				pss.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 0: 
			break out;
		default:
			System.out.println("Please Enter Valid Input...........");
			break;
		}
		}
		return updatemarksheet;
	}
	
	
	public static int deleteMarksheet() {
		int deleteMarksheet = 0;
		Marksheet marks = new Marksheet();

		System.out.print("Enter Roll Number : ");
		marks.setRollNo(scan.next());
		
		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement ps =
			con.prepareStatement("DELETE FROM marksheet where rollnumber = ?");
			
			ps.setString(1, marks.getRollNo());
			
			deleteMarksheet = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleteMarksheet;
	}
	
	public static void getMarksheet(Statement stmt ) {
		
		try {
			ResultSet  rs = stmt.executeQuery("SELECT * FROM marksheet");
			ResultSetMetaData rsm = rs.getMetaData();
			for(int i=1; i<=rsm.getColumnCount(); i++) {
				System.out.print(rsm.getColumnName(i) + "\t");
			}
			System.out.println();
		while(rs.next()) {
			System.out.print( rs.getString(1) +"\t\t" );
			System.out.print( rs.getString(2) +"\t");
			System.out.print( rs.getInt(3) +"\t\t");
			System.out.print( rs.getInt(4) +"\t");
			System.out.print( rs.getInt(5)+"\t");
			System.out.print(rs.getInt(6));
			
			System.out.println();
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void getMaritList() {
		
		Connection con = DBConnection.getConnection();
		 
		try {
			Statement stmt =con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT rollnumber,name, MAX(total) As total_marks from marksheet group by rollnumber,name "
					+ "ORDER BY total_marks DESC LIMIT 5");
			ResultSetMetaData rsm = rs.getMetaData();
			for(int i = 1; i<=rsm.getColumnCount(); i++) {
				System.out.print(rsm.getColumnName(i)+"\t\t ");
			}
			System.out.println();
			
			while(rs.next()) {
				System.out.print(rs.getString(1)+"\t\t\t");
				System.out.print(rs.getString(2)+"\t\t\t");
				System.out.print(rs.getInt(3));
				
				System.out.println();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
