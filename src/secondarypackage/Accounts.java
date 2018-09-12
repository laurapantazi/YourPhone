package secondarypackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mainpackage.DBHandler;

public class Accounts {
	
	public static long createCustomerAccount(String username,String nameProgram) {
		Connection con;
		ResultSet rs;
		long phoneNumber=0;
		try {
			con=DBHandler.openConnection();
			Statement stmt;
			Statement stmt1;
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			String client="client";
			rs = stmt.executeQuery(
					"SELECT users.idUser,programs.idProgram  FROM yourphone.users,yourphone.programs WHERE username='" + username + "' AND role='" + client + "'  AND nameProgram='" + nameProgram + "'");
			if (rs.next())    
			{
				int idClient=rs.getInt("idUser");
				int idProgram=rs.getInt("idProgram");
				phoneNumber= CreatePhoneNumber.generatePhoneNumber();
				String command = "INSERT INTO yourphone.client (idClient,idProgram,phoneNumber) VALUES ('" + idClient
					+ "','" + idProgram + "','" + phoneNumber + "')";
				stmt1.executeUpdate(command);
				stmt.close();
				stmt1.close();
				con.close();
			}
			else{
				phoneNumber=0;
			}
			stmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return phoneNumber;
	}//end of method newCustomer
	
	public static ArrayList<String> showHistoryLog(String usernameShowAccount)
	{
		ArrayList<String> historyLogList = new ArrayList<>();		
		try {
			Connection con=DBHandler.openConnection();
	        PreparedStatement ps=con.prepareStatement("SELECT idCall,dialedNumber,callDuration,paid FROM callLogs,users WHERE callLogs.idClient=users.idUser and users.username='"+usernameShowAccount+"'");
	        ResultSet rs=ps.executeQuery();	
			while (rs.next()) {
				String idCall = Integer.toString(rs.getInt("idCall"));
				String dialedNumber = Long.toString(rs.getLong("dialedNumber"));
				String secondCall =Integer.toString(rs.getInt("callDuration"));
				//προσθέτω τις τιμές που διαβάστηκαν στον arraylist πίνακα
				historyLogList.add(idCall);
				historyLogList.add(dialedNumber);
				historyLogList.add(secondCall);
			}	
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return historyLogList;		
	} //end of method showHistoryLog	
	
	public static ArrayList<String> showCustomerAccount(int idClientShowAccount)
	{	
		ArrayList<String> accountList = new ArrayList<>();
		try {
			Connection con=DBHandler.openConnection();	
			Statement stmt=con.createStatement();
			String phoneNumber=" ",nameProgram=" ",debt=" ",chargeSeconds=" ",callDuration=" ";
			ResultSet rs=stmt.executeQuery(
					"SELECT sum(callDuration*chargeSeconds),sum(callDuration),nameProgram,chargeSeconds,phoneNumber FROM yourphone.client INNER JOIN yourphone.calllogs ON client.idClient=calllogs.idClient AND client.idClient="+ idClientShowAccount +" AND paid=FALSE INNER JOIN yourphone.programs ON programs.idProgram=client.idProgram");
			if (rs.next()) {
				nameProgram=rs.getString("nameProgram");
				chargeSeconds=Float.toString(rs.getFloat("chargeSeconds"));
				callDuration=Integer.toString(rs.getInt("sum(callDuration)"));
				phoneNumber =Long.toString((rs.getLong("phoneNumber")));
				debt=Float.toString(rs.getFloat("sum(callDuration*chargeSeconds)"));
				accountList.add(nameProgram);
				accountList.add(phoneNumber);
				accountList.add(chargeSeconds);
				accountList.add(callDuration);
				accountList.add(debt);
			}	
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return accountList;		
	} //end of method showAccount
	
	
	
	public static float showDebtAccount(int idClient)
	{
		float debt=0;
		try {
			Connection con=DBHandler.openConnection();	
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(
"SELECT sum(callDuration*chargeSeconds) FROM yourphone.client INNER JOIN yourphone.calllogs ON client.idClient=calllogs.idClient AND client.idClient="+ idClient +"  AND paid=FALSE INNER JOIN yourphone.programs ON programs.idProgram=client.idProgram");
			if (rs.next()) {
				debt=rs.getFloat("sum(callDuration*chargeSeconds)");
			}	
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return debt;		
	} //end of method showDebtAccount

	
	public static void payAccount(int idClient)
	{
		try {
			Connection con=DBHandler.openConnection();	
			Statement stmt=con.createStatement();
			stmt.executeUpdate("UPDATE yourphone.calllogs SET paid=TRUE WHERE idClient=" + idClient );	
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	} //end of method payAccount
}
