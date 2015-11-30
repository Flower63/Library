package ua.epam.library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.controller.action.ActionFactory;
import ua.epam.library.util.DAO;
import ua.epam.library.util.MySqlDAO;

/**
 * Servlet implementation class LybraryController
 */
@WebServlet("/MyLibraryController")
@SuppressWarnings("serial")
public class MyLibraryController extends HttpServlet {
       
	private DAO dao;
	private ActionFactory factory;
	
    public MyLibraryController() {
        dao = new MySqlDAO();
        factory = ActionFactory.getInstance();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("request") == null) {
			String from = request.getParameter("from");
			request.getRequestDispatcher(from.substring(from.indexOf('/', 1))).forward(request, response);
			return;
		}
		
		String path = factory.getAction(request.getParameter("request")).execute(request, response, dao);
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		dispatcher.forward(request, response);
	}
}
