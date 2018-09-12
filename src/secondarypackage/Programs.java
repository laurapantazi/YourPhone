package secondarypackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mainpackage.DBHandler;

public class Programs {

	public static void createProgram(String nameProgram, float chargeSeconds) {
		Connection con;
		try {
			con = DBHandler.openConnection();
			Statement stmt;
			stmt = con.createStatement();
			String command = "INSERT INTO yourphone.programs (nameProgram,chargeSeconds) VALUES ('" + nameProgram + "','"
						+ chargeSeconds + "')";
			stmt.executeUpdate(command);
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}// end of method createProgram
	
	public static ArrayList<String> programs()
	{
		ArrayList<String> showPrograms=new ArrayList<>();		
		PreparedStatement ps;
		try {
			Connection con= DBHandler.openConnection();
			ps = con.prepareStatement("SELECT nameProgram,chargeSeconds FROM programs");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				showPrograms.add(rs.getString("nameProgram"));	
				showPrograms.add(Float.toString(rs.getFloat("chargeSeconds")));	

			}	
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return showPrograms;
	}//end of method programs 
	
	
	public static ArrayList<String> changeCustomerProgram(String usernameChangeProgram) {
		ArrayList<String> programList = new ArrayList<>();
		Connection con;
		try {
			con= DBHandler.openConnection();
			ResultSet rs;
			ResultSet rs1;
			Statement stmt;
			Statement stmt2;
			stmt = con.createStatement();
			stmt2 = con.createStatement();
			String client="client";
			rs = stmt.executeQuery("SELECT users.role FROM users WHERE users.username='"+usernameChangeProgram + "' AND users.role='" + client + "'");
			if (rs.next())
			{				 
					 rs1 = stmt2.executeQuery("SELECT programs.nameProgram FROM programs WHERE programs.idProgram NOT IN(SELECT client.idProgram FROM users, client WHERE users.idUser=client.idClient AND users.username='"+usernameChangeProgram + "')");
						while (rs1.next())  
						{
							String nameProgram = rs1.getString("nameProgram");	
							programList.add(nameProgram);		
						}
						stmt2.close();		
			}
			stmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return programList;
	}//end of method changeProgram
	
	public static void updateChangeProgram(int idClient,int idProgram){
		Connection con;
		try {
			con=DBHandler.openConnection();
			Statement stmt;
			stmt=con.createStatement();
			String command = "UPDATE yourphone.client SET idProgram="+ idProgram +" WHERE idClient=" + idClient + "";
			stmt.executeUpdate(command);
			stmt.close();			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}//end of method updateChangeProgram

}
