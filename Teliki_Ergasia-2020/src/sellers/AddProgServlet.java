package sellers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddProgServlet
 */
@WebServlet("/AddProgServlet")
public class AddProgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProgServlet() {
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
		String telephone = request.getParameter("telephone");
		String program = request.getParameter("program");
		AddProg addp = new AddProg();
		
		//we check if username, telephone and program id fields are empty
		if(username.isBlank() || telephone.isBlank() || !addp.isNumber(program)) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/progToClientError.jsp");
			dispatcher.forward(request, response);
		}
		else  
		{
			int progId = Integer.parseInt(request.getParameter("program"));
			addp.setUsername(username);
			addp.setTelephone(telephone);
			addp.setProgId(progId);
				
			try 
			{
				//check if the client has already a program
				if(username.equals(addp.getClient())) 
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/progToClientUError.jsp");
					dispatcher.forward(request, response);
				}
				//check if username and program Id exist in database and telephone number is correctly typed
				else if(username.equals(addp.getUsrnm()) && progId==addp.getProg()
					&& telephone.matches("[69]{2}[0-9]{8}")) 
				{
					try //add program to the client 
					{
						addp.addProg();
						RequestDispatcher dispatcher = request.getRequestDispatcher("/progToClientMessage.jsp");
						dispatcher.forward(request, response);
					}
					catch (ClassNotFoundException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else //if username and program Id don't exist in database and telephone number isn't correctly typed
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/progToClientError.jsp");
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
