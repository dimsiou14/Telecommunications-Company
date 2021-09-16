package admins;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddSellerServlet
 */
@WebServlet("/AddSellerServlet")
public class AddSellerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSellerServlet() {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/addSellerError.jsp");
			dispatcher.forward(request, response);	
		}
		else//if all fields are filed 
		{
			AddSeller adds = new AddSeller();
			adds.setUsername(username);
			adds.setPassword(password);
			adds.setName(name);
			adds.setSurname(surname);
			
			try 
			{
				//check if username already exists in database
				if(username.equals(adds.getUsrnm()))  
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/addSellerUError.jsp");
					dispatcher.forward(request, response);
				}
				//if doesn't exist
				else 
				{
					//client registration 
					try
					{
						adds.registerSeller();
						RequestDispatcher dispatcher = request.getRequestDispatcher("/addSellerMessage.jsp");
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
