package sellers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddClientServlet
 */
@WebServlet("/AddClientServlet")
public class AddClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClientServlet() {
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
		//we check if there is any blank field
		if(username.isBlank()||password.isBlank()||name.isBlank()||surname.isBlank()) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/addClientError.jsp");
			dispatcher.forward(request, response);	
		}
		else//if all fields are filed 
		{
			AddClient addc = new AddClient();
			addc.setUsername(username);
			addc.setPassword(password);
			addc.setName(name);
			addc.setSurname(surname);
			
			try 
			{
				//check if username already exists in database
				if(username.equals(addc.getUsrnm()))  
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/addClientUError.jsp");
					dispatcher.forward(request, response);
				}
				//if doesn't exist
				else 
				{
					//client registration 
					try
					{
						addc.registerClient();
						RequestDispatcher dispatcher = request.getRequestDispatcher("/addClientMessage.jsp");
						dispatcher.forward(request, response);
					}
					catch (ClassNotFoundException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
