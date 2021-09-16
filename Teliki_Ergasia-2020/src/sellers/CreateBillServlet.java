package sellers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateBillServlet
 */
@WebServlet("/CreateBillServlet")
public class CreateBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBillServlet() {
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
		String m = request.getParameter("month");
		CreateBill crtbill = new CreateBill();
		
		//we check if client's username and current month fields are empty
		if(username.isBlank() || !crtbill.isNumber(m)) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/sellerCreateBillError.jsp");
			dispatcher.forward(request, response);
		}
		//if the two fields are filed
		else
		{
			int month = Integer.parseInt(request.getParameter("month"));
			crtbill.setUsername(username);
			crtbill.setMonth(month);
			
			try 
			{
				//we are cheking if the current client has already a bill in order to update it with a new one
				if(username.equals(crtbill.getClient())) 
				{
					//we are checking if month has correct type: 1-12
					if(month>=1 && month<=12)
					{
						//update the bill for the current client
						try 
						{
							crtbill.getProgID();
							crtbill.billUpdate();
							RequestDispatcher dispatcher = request.getRequestDispatcher("/sellerCreateBillMessage.jsp");
							dispatcher.forward(request, response);
						}
						catch (ClassNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else 
					{
						RequestDispatcher dispatcher = request.getRequestDispatcher("/sellerCreateBillError.jsp");
						dispatcher.forward(request, response);
					}
				}
				//we check if username exists   
				else if(username.equals(crtbill.getUsrnm())) 
				{
					//we are checking if month has correct type: 1-12
					if(month>=1 && month<=12)
					{
						//create bill for the current client
						try 
						{
							crtbill.getTelNum();
							crtbill.getProgID();
							crtbill.createBill();
							RequestDispatcher dispatcher = request.getRequestDispatcher("/sellerCreateBillMessage.jsp");
							dispatcher.forward(request, response);
						}
						catch (ClassNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else 
					{
						RequestDispatcher dispatcher = request.getRequestDispatcher("/sellerCreateBillError.jsp");
						dispatcher.forward(request, response);
					}
				}
				else //if client's username doesn't exist in the database 
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/sellerCreateBillError.jsp");
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
