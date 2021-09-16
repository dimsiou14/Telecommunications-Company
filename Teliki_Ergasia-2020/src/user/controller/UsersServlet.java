package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;

import user.db.UserDb;
import users.User;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/register")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserDb userdb = new UserDb();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String type = request.getParameter("type");
		//check if there is any blank field
		if(username.isBlank()||password.isBlank()||name.isBlank()||surname.isBlank()||type.isBlank()) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Register_Form/registererror.html");
			dispatcher.forward(request, response);	
		}
		//check the input of Type field
		else if(type.equals("Client") || type.equals("Seller")
			|| type.equals("client") || type.equals("seller") 
			|| type.equals("CLIENT") || type.equals("SELLER")) 
		{
			SecureRandom random = new SecureRandom();
			byte bytes[]= new byte[20];
			random.nextBytes(bytes);
			String pass = userdb.getHashMD5(password, random.toString());
		
			User user = new User();
			user.setUsername(username);
			user.setPassword(pass);
			user.setName(name);
			user.setSurname(surname);
			user.setType(type);
			
			try 
			{
				//check in database if username exists 
				userdb.get(user);
				if(username.equals(userdb.get(user))) 
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Register_Form/registerUsrnm.html");
					dispatcher.forward(request, response);	
				}
				//if all fields are correct
				else 
				{
					try 
					{
						userdb.registerUser(user);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/Register_Form/registermessage.jsp");
						dispatcher.forward(request, response);
					} 
					catch (ClassNotFoundException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} 
			catch (Exception e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//if a field is wrong
		else 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Register_Form/registererror.html");
			dispatcher.forward(request, response);	
		}
			
	}

}
