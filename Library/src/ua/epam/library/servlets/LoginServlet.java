package ua.epam.library.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.LibraryModel;
import ua.epam.library.Student;

/**
 * Servlet implementation class LoginServlet
 */
@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int studentId;
		String password;
		
		try {
			studentId = Integer.parseInt(request.getParameter("student_id"));
		} catch (NumberFormatException e) {
			redirect(request, response, "login");
			return;
		}
		
		Student student = LibraryModel.getStudentById(studentId);
		
		password = request.getParameter("pwd");
		
		if (student == null) {
			redirect(request, response, "login");
			return;
			
		} else if(!student.getPassword().equals(password)) {
			redirect(request, response, "password");
			return;
			
		} else {			// If login succeed
			HttpSession session = request.getSession();
			
			session.setAttribute("student", student);
			
			// 3 hours
			session.setMaxInactiveInterval(60*60*3);
			
			response.sendRedirect("app/main.jsp");
		}
	}
	
	private void redirect(ServletRequest request, ServletResponse response, String cause) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		
		request.setAttribute("cause", cause);
		
		dispatcher.forward(request, response);
	}

}
