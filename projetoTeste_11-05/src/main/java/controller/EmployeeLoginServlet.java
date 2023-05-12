package controller;

import java.io.IOException;

import dao.EmployeeDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/login")
public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	EmployeeDAO employeeDAO = new EmployeeDAO();

    /**
     * Default constructor. 
     */
    public EmployeeLoginServlet() {
        // TODO Auto-generated constructor stub
    }
    
    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        String username = null;
        String password = null;
        // save counter to the application scope
        getServletContext().setAttribute( "username", username);
        getServletContext().setAttribute( "password", password);

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/employeelogin.jsp");
		dispatcher.forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Employee employee = new Employee();
		employee.setUsername(username);
		employee.setPassword(password);
		
		try {
 			
			if(employeeDAO.loginEmployee(employee) == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/employeeloginerror.jsp");
				dispatcher.forward(request, response);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("username", username);
        servletContext.setAttribute("password", password);
        

		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeDetails");
		dispatcher.forward(request, response);
	}

}
