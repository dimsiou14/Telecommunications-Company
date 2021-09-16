package sellers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProgChangeServlet
 */
@WebServlet("/ProgChangeServlet")
public class ProgChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgChangeServlet() {
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
		String progid = request.getParameter("progid");
		ProgChange prgchng = new ProgChange();
		
		//we check if the fields username is empty and program id is integer
		if (username.isBlank() || !prgchng.isNumber(progid)) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/progChangeError.jsp");
			dispatcher.forward(request, response);
		}
		else  
		{
			int progID = Integer.parseInt(request.getParameter("progid"));
			prgchng.setUsername(username);
			prgchng.setProgid(progID);
			try 
			{
				//we check if this username and program id exist in the database
				if(username.equals(prgchng.getUsrnm()) && progID==prgchng.getProg())
				{
					try 
					{
						prgchng.progChange();
						prgchng.billUpdate();
						RequestDispatcher dispatcher = request.getRequestDispatcher("/progChangeMessage.jsp");
						dispatcher.forward(request, response);
					}
					catch (ClassNotFoundException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else //if username and program id don't exist in database
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/progChangeError.jsp");
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
