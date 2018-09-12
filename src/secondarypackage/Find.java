package secondarypackage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mainpackage.DBHandler;

public class Find {
	//returns 0 if there is no user with this username
	//or returns the id of the user
	public static int findUser(String username){
		int user=0;
		Connection con;
		ResultSet rs;
		try {
			con = DBHandler.openConnection();
			Statement stmt;
			stmt = con.createStatement();
			rs = stmt.executeQuery(	"SELECT * FROM yourphone.users WHERE username='" + username + "'");
			if (rs.next())   
			{
				user=rs.getInt("idUser");		
			}
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return user;		
	}//end of method findUser
	
	//returns 0 if there is no program with this name
	//or returns the id of the program
	public static int findProgram(String nameProgram){
		int program=0; 
		Connection con;
		ResultSet rs;
		try {
			con = DBHandler.openConnection();
			Statement stmt;
			stmt = con.createStatement();
			rs = stmt.executeQuery(	"SELECT * FROM yourphone.programs WHERE nameProgram='" + nameProgram + "'");
			if (rs.next())   
			{
				program=rs.getInt("idProgram");
			}
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		return program;
	}//end of method findProgram
	
	//returns -1 if this username represents an admin/seller
	//returns 0 if this username represents a client without account
	//or returns the id of the client , if he has an account
	public static int findClient(String username){
		int clientId=-1; 
		Connection con;
		try {
			con = DBHandler.openConnection();
			Statement stmt = con.createStatement();
			Statement stmt2 = con.createStatement();
			String client="client";	
			// search if client exists in users
			ResultSet rs = stmt.executeQuery("SELECT idUser FROM yourphone.users WHERE username='"+ username +"' AND role='"+ client +"'");						
			if (rs.next()){   // if we found him 
				clientId=0;  
				int userId=rs.getInt("idUser");	
				ResultSet rs1 = stmt2.executeQuery(	"SELECT idClient FROM yourphone.users,yourphone.client WHERE client.idClient="+ userId +"");						
				if (rs1.next())  
				{
					clientId=rs1.getInt("idClient");	
				}
			}
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return clientId;		
	}//end of method findClient
}
