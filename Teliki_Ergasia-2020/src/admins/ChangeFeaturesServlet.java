package admins;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChangeFeaturesServlet
 */
@WebServlet("/ChangeFeaturesServlet")
public class ChangeFeaturesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeFeaturesServlet() {
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
		ChangeFeatures cf = new ChangeFeatures();
		
		//we are checking if the fields haven't the correct type
		if(!cf.isNumber(f1) || !cf.isNumber(f2) || !cf.isNumber(f3) || 
				!cf.isNumber(f4) || !cf.isFloat(f5)) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/featuresChangeError.jsp");
			dispatcher.forward(request, response);
		}
		else 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			int speech = Integer.parseInt(request.getParameter("speech"));
			int sms = Integer.parseInt(request.getParameter("sms"));
			int data = Integer.parseInt(request.getParameter("data"));
			float cost = Float.parseFloat(request.getParameter("cost"));
			
			cf.setId(id);
			cf.setSpeech(speech);
			cf.setSms(sms);
			cf.setData(data);
			cf.setCost(cost);
			
			try 
			{
				//check if this program id exists in programs table in order to update the current program
				if(id==cf.getProgID()) 
				{
					cf.changeSpeech();
					cf.changeSMS();
					cf.changeData();
					cf.changeCost();
					RequestDispatcher dispatcher = request.getRequestDispatcher("/featuresChangeMessage.jsp");
					dispatcher.forward(request, response);
				}
				else //if program id doesn't exist in table programs 
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/featuresChangeError.jsp");
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
