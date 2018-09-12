package mainpackage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import secondarypackage.Find;
import secondarypackage.Programs;
import secondarypackage.Users;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String nameProgram = request.getParameter("nameProgram");
		String sec= request.getParameter("chargeSeconds");
		HttpSession session=request.getSession();
		float chargeSeconds=0;
		if(sec !=null && !sec.isEmpty()) chargeSeconds = Float.parseFloat(sec);
		if (nameProgram == null) {
			 //create new user
			if (Find.findUser(username)==0)
			{
				Users.createUser(username,password,role,name,surname);
				request.setAttribute("message","User created successfully!");
				ArrayList<String> allUsers=Users.users();
				session.setAttribute("allUsers",allUsers);
			}		
			else
			{
				request.setAttribute("message","This username is already in use."
						+ "Please use another one.");
			}
		} 
		//create new program
		else {		
			if (Find.findProgram(nameProgram)==0)
			{
				Programs.createProgram(nameProgram, chargeSeconds);
				request.setAttribute("message","Program created successfully!");
				ArrayList<String> allPrograms=Programs.programs();
				session.setAttribute("allPrograms",allPrograms);
			}
			else
			{
				request.setAttribute("message","This program has already been added.");
			}
		}
		request.getRequestDispatcher("adminMenu.jsp").include(request, response);
	}
}
