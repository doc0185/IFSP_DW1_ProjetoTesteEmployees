package controller;

import java.io.IOException;

import dao.EmployeeDAO;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet("/employeeDetails")
public class EmployeeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmployeeDAO employeeDAO = new EmployeeDAO();

    /**
     * Default constructor. 
     */
    public EmployeeDetailsServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        ServletContext servletContext = getServletContext();
        String username = (String) servletContext.getAttribute("username");
        String password = (String) servletContext.getAttribute("password");
		
		System.out.println(username);
		System.out.println(password);
				
		Employee employee = new Employee();
		employee.setUsername(username);
		employee.setPassword(password);
		Employee dataEmployee = new Employee();
		
		try {
			dataEmployee = employeeDAO.detailsEmployee(employee);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		/*
		PrintWriter writer = response.getWriter();
        String htmlRespone = "<html>";
        htmlRespone += "<h2> Nome: " + dataEmployee.getFirstName() + "<br/>";    
        htmlRespone += "Sobrenome: " + dataEmployee.getLastName() + "<br/>";
        htmlRespone += "Endereco: " + dataEmployee.getAddress() + "<br/>";
        htmlRespone += "Contato: " + dataEmployee.getContact() + "</h2>";
        htmlRespone += "</html>";
         
        // return response
        writer.println(htmlRespone);*/
		request.setAttribute("name", dataEmployee.getFirstName());
		request.setAttribute("lastName", dataEmployee.getLastName());
		request.setAttribute("address", dataEmployee.getAddress());
		request.setAttribute("contact", dataEmployee.getContact());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/employeedetails.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
