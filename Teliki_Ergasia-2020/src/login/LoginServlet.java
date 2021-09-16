package login;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		//get the values of the username and password that user types
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//check if the fields are blank
		if(username.isBlank() || password.isBlank()) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/indexError.html");
			dispatcher.forward(request, response);	
		}
		else
		{
			Login login = new Login();
			SecureRandom random = new SecureRandom();
			byte bytes[]= new byte[20];
			random.nextBytes(bytes);
			String pass = login.getHashMD5(password, random.toString());
			
			
			login.setUsername(username);
			login.setPassword(pass);
			HttpSession session = request.getSession();
			try 
			{
				//if username and password exist in database
				if(username.equals(login.username) && pass.equals(login.password))
				{
						//send the user at the correct home page
						if(login.getType().equals("client")||login.getType().equals("Client")||login.getType().equals("CLIENT"))
						{
							session.setAttribute("username",username);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/homeClient.jsp");
							dispatcher.forward(request, response);
						}
						else if(login.getType().equals("seller")||login.getType().equals("Seller")||login.getType().equals("SELLER")) 
						{
							session.setAttribute("username",username);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/homeSeller.jsp");
							dispatcher.forward(request, response);
						}
						else if(login.getType().equals("Administrator")||login.getType().equals("administrator")||login.getType().equals("ADMINISTRATOR")) 
						{
							session.setAttribute("username",username);
							RequestDispatcher dispatcher = request.getRequestDispatcher("/homeAdministrator.jsp");
							dispatcher.forward(request, response);
						}
						else //wrong username and password  
						{
							RequestDispatcher dispatcher = request.getRequestDispatcher("/indexError.html");
							dispatcher.forward(request, response);	
						}
				}
				else //wrong field input 
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/indexError.html");
					dispatcher.forward(request, response);	
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();			
			}
		}
	}

}
