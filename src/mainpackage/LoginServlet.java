package mainpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import secondarypackage.Accounts;
import secondarypackage.Find;
import secondarypackage.HasherUserPassword;
import secondarypackage.Programs;
import secondarypackage.Users;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		session.invalidate();
		session=request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String roleDB = " ";
		String loginNameDB = " ";
		String loginSurnameDB = " ";

		try {
			Connection con = DBHandler.openConnection();
			Statement stmt = con.createStatement();
			PreparedStatement ps1=null;
			password=HasherUserPassword.hashPassword(password);
			ps1=con.prepareStatement(
					"select * from yourphone.users where username='" + username + "' and password='" + password+"'");
			ResultSet rs=ps1.executeQuery();
			if (rs.next()) {
				roleDB = rs.getString("role");
				loginNameDB = rs.getString("name");
				loginSurnameDB = rs.getString("surname");
				session.setAttribute("username", username);
				session.setAttribute("loginNameDB", loginNameDB);
				session.setAttribute("loginSurnameDB", loginSurnameDB);
				request.setAttribute("message","No changes saved yet.");
				if (roleDB.equals("admin")) {
					ArrayList<String> allPrograms=Programs.programs();
					session.setAttribute("allPrograms",allPrograms);
					ArrayList<String> allUsers=Users.users();
					session.setAttribute("allUsers",allUsers);
					request.getRequestDispatcher("adminMenu.jsp").include(request, response);
				} else if (roleDB.equals("seller")) {
					ArrayList<String> availablePrograms=Programs.programs();
					request.setAttribute("availablePrograms",availablePrograms);
					request.getRequestDispatcher("sellerMenu.jsp").include(request, response);				
				} else {
					ArrayList<String> accountList=new ArrayList<>();
					ArrayList<String> historyLogList=new ArrayList<>();
					int idClient=Find.findClient(username);
					float debtAccount=0;
					if (idClient>0){
						accountList=Accounts.showCustomerAccount(idClient);
						historyLogList=Accounts.showHistoryLog(username);
						debtAccount=Accounts.showDebtAccount(idClient);
					}
					else{
						request.setAttribute("message","You don't have an account yet.");
					}
					session.setAttribute("accountList", accountList);
					session.setAttribute("historyLogList", historyLogList);	
					session.setAttribute("debtAccount", debtAccount);
					request.getRequestDispatcher("clientMenu.jsp").include(request, response);
				}
			} else {
				out.print("Wrong username or password!");
				request.getRequestDispatcher("index.jsp").include(request, response);
			}
			stmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		out.close();
	}
}