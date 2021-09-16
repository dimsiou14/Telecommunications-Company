package admins;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewProgServlet
 */
@WebServlet("/AddNewProgServlet")
public class AddNewProgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewProgServlet() {
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
		String f1 = request.getParameter("id");
		String f2 = request.getParameter("speech");
		String f3 = request.getParameter("sms");
		String f4 = request.getParameter("data");
		String f5 = request.getParameter("cost");
		AddNewProg addnp = new AddNewProg();
		
		//we are checking if the fields haven't the correct type
		if(!addnp.isNumber(f1) || !addnp.isNumber(f2) || !addnp.isNumber(f3) || 
		   !addnp.isNumber(f4) || !addnp.isFloat(f5)) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/addProgError.jsp");
			dispatcher.forward(request, response);
		}
		else 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			int speech = Integer.parseInt(request.getParameter("speech"));
			int sms = Integer.parseInt(request.getParameter("sms"));
			int data = Integer.parseInt(request.getParameter("data"));
			float cost = Float.parseFloat(request.getParameter("cost"));
			
			addnp.setId(id);
			addnp.setSpeech(speech);
			addnp.setSms(sms);
			addnp.setData(data);
			addnp.setCost(cost);
			
			try 
			{
				if(id!=addnp.getProgID())//we are cheking if this program id doesn't exist in the database  
				{
					addnp.addProg();//we add the new program
					RequestDispatcher dispatcher = request.getRequestDispatcher("/addProgMessage.jsp");
					dispatcher.forward(request, response);
				}
				else //if this program id already exists in the database 
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/addProgError.jsp");
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
