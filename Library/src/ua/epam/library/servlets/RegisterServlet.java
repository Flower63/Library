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

import ua.epam.library.Lang;
import ua.epam.library.LibraryModel;
import ua.epam.library.Student;

/**
 * Servlet implementation class RegisterServlet
 */
@SuppressWarnings("serial")
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String readerId = request.getParameter("student_id");
		String password = request.getParameter("pwd1");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		
		if (readerId.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
			redirect(request, response, "Fields must not be empty!");
			return;
		}
		
		int id = 0;
		
		try {
			id = Integer.parseInt(readerId);
		} catch (NumberFormatException e) {
			redirect(request, response, "Incorrect id");
			return;
		}
		
		if (!password.equals(request.getParameter("pwd2"))) {
			redirect(request, response, "Passwords don't matches");
			return;
		}
		
		Student student = new Student(firstName, lastName, id, password, false, Lang.RU);
		
		if (LibraryModel.registerNewRider(student)) {
			HttpSession session = request.getSession();
			
			session.setAttribute("student", student);
			
			// 3 hours
			session.setMaxInactiveInterval(60*60*3);
			
			response.sendRedirect("app/main.jsp");
		} else {
			redirect(request, response, "Such ID already exists");
		}
	}
	
	private void redirect(ServletRequest request, ServletResponse response, String cause) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		
		request.setAttribute("cause", cause);
		
		dispatcher.forward(request, response);
	}

}
