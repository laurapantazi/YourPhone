package mainpackage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import secondarypackage.Accounts;
import secondarypackage.Find;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		int idClient=Find.findClient(username);
		ArrayList<String> accountList=new ArrayList<>();
		float debtAccount=0;
		//show client account and debt
		if (idClient>0){
			accountList=Accounts.showCustomerAccount(idClient);
			debtAccount=Accounts.showDebtAccount(idClient);
		}
		else{
			request.setAttribute("message","You don't have an account yet.");
		}
		session.setAttribute("accountList", accountList);
		session.setAttribute("debtAccount", debtAccount);
		request.getRequestDispatcher("clientMenu.jsp").include(request, response);				
	}//end of doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		int idClient=Find.findClient(username);
		float debt=Accounts.showDebtAccount(idClient);
		if (debt!=0.0){
		//pay client debt
		Accounts.payAccount(idClient);
		request.setAttribute("message","Successful payment.");
		}
		else
		{
			request.setAttribute("message","There is nothing to pay.");
		}
		doGet(request,response);
	}//end of doPost

}
