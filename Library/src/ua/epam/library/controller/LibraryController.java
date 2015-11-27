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
@WebServlet("/LybraryController")
@SuppressWarnings("serial")
public class LibraryController extends HttpServlet {
       
	private DAO dao;
	private ActionFactory factory;
	
    public LibraryController() {
        dao = new MySqlDAO();
        factory = ActionFactory.getInstance();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = factory.getAction(request.getParameter("request")).execute(request, response, dao);
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		dispatcher.forward(request, response);
	}
}
