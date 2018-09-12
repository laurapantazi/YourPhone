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
import secondarypackage.Programs;
import secondarypackage.Users;


@WebServlet("/SellerServlet")
public class SellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");			
		String usernameShowAccount = request.getParameter("usernameShowAccount");
		String usernameChangeProgram = request.getParameter("usernameChangeProgram");				
		//store in the session the name of the client, 
		//in order to change his program
		HttpSession session = request.getSession();
		session.setAttribute("usernameChangeProgram", usernameChangeProgram);
		//show available program for this client
		ArrayList<String> availablePrograms=Programs.programs();
		request.setAttribute("availablePrograms",availablePrograms);
		ArrayList<String> accountList=new ArrayList<>();
		//show customer account
		if(usernameShowAccount!=null)
		{	
			if(Find.findUser(usernameShowAccount)==0)
			{
				request.setAttribute("message","Wrong username.");
			}
			else{
				
				int idClientShowAccount=Find.findClient(usernameShowAccount);
				if(idClientShowAccount==-1)// represents admin / seller
				{
					request.setAttribute("message","This username does not represent a client.");
				}
				else if(idClientShowAccount==0) //represents client without account
				{			
					request.setAttribute("message","This user hasn't create an account yet.");
				}
				else  //represents client with account
				{
					request.setAttribute("message","Account successfully found.");
					request.setAttribute("message","Customer account found successfully!");
					accountList=Accounts.showCustomerAccount(idClientShowAccount);	
					request.setAttribute("accountList", accountList);		
				}
			}
		}	//end show customer account		
		if(usernameChangeProgram!=null){//change program customer
			ArrayList<String> programList = new ArrayList<>();
			if(Find.findUser(usernameChangeProgram)==0)
			{
				request.setAttribute("message","Wrong username.");
			}
			else{
					
				if (Find.findClient(usernameChangeProgram)==-1) //represents admin / seller
				{
					request.setAttribute("message","This username does not represent a client.");	
				}
				else if	(Find.findClient(usernameChangeProgram)==0)//represents client without account
				{
					request.setAttribute("message","This user hasn't create an account yet.");	
				}
				else
				{
					request.setAttribute("message","Customer account found successfully.You can now select a new program");
					programList=Programs.changeCustomerProgram(usernameChangeProgram);
					request.setAttribute("programList", programList);	
				}
			}
		
		} //end of program customer
			request.getRequestDispatcher("sellerMenu.jsp").include(request, response);
	}//end of doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String clientName = request.getParameter("clientName");
		String clientSurname = request.getParameter("clientSurname");
		String clientUsername = request.getParameter("clientUsername");
		String clientPassword = request.getParameter("clientPassword");

		String username = request.getParameter("username");
		String nameProgram = request.getParameter("nameProgram");	
		HttpSession session = request.getSession();	
		String usernameChangeProgram = (String) session.getAttribute("usernameChangeProgram");
		long phoneNumber=0;
		ArrayList<String> availablePrograms=Programs.programs();
		request.setAttribute("availablePrograms",availablePrograms);
		if((usernameChangeProgram==null)&&(clientUsername==null)){
			//create customer account	
			if(Find.findUser(username)==0)
			{
				request.setAttribute("message","There is no user with this username.");
			}
			else{
				if(Find.findClient(username)==-1) //represents admin/seller
				{
					request.setAttribute("message","This username does not represent a client.");
				}
				else if(Find.findClient(username)==0) //represents client without account
				{							
					request.setAttribute("message","New customer account created successfully!");
					phoneNumber=Accounts.createCustomerAccount(username,nameProgram);
					request.setAttribute("phoneNumber",phoneNumber);
				}
				else  //represents client with account
				{
					request.setAttribute("message","This customer has already created an account.");
				}
			}
		}//end of create customer account
		else if (clientUsername==null)//change program customer
		{	
			//change customer program
			int idNewProgramClient=Find.findClient(usernameChangeProgram);
			int idNewProgram=Find.findProgram(nameProgram);
			Programs.updateChangeProgram(idNewProgramClient,idNewProgram);	
			request.setAttribute("message","Customer program updated successfully!");
		}//end of program customer
		else
		{
			//create customer 
			if(Find.findUser(clientUsername)!=0)
			{
				request.setAttribute("message","This username is already in use. "
						+ "Please use another one.");
			}
			else
			{				
				request.setAttribute("message","Customer created successfully.");
				Users.createUser(clientUsername,clientPassword,"client",clientName,clientSurname);
			}
		}
	request.getRequestDispatcher("sellerMenu.jsp").include(request, response);
	}//end of doPost
}