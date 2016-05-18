package ua.epam.library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.controller.action.ActionFactory;

/**
 * Controller servlet to library
 * 
 * @author Dennis
 *
 */
@WebServlet("/MyLibraryController")
@SuppressWarnings("serial")
public class MyLibraryController extends HttpServlet {
	
	/**
	 * Encoding
	 */
	private final static String ENCODING = "UTF-8";
	
	/**
	 * HTTP request parameters
	 */
	private final static String REQUEST = "request";
	private final static String FROM = "from";
	
	/**
	 * Factory for actions (pattern command)
	 */
	private ActionFactory factory;
	
	/**
	 * Constructor, initializes fields
	 */
    public MyLibraryController() {
        factory = ActionFactory.getInstance();
    }

    /**
     * To handle POST requests
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Setting encoding
		 */
		request.setCharacterEncoding(ENCODING);

		/*
		 * Checking, if "request" parameter is empty. If so, forwards request by "from" parameter.
		 * It needs to be able change language anywhere in app.
		 */
		if (request.getParameter(REQUEST) == null) {
			String from = request.getParameter(FROM);
			request.getRequestDispatcher(from.substring(from.indexOf('/', 1))).forward(request, response);
			return;
		}
		
		/*
		 * Getting path by executing Action object, which is chosen due to "request" parameter
		 */
		String path = factory.getAction(request.getParameter(REQUEST)).execute(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		/*
		 * Forwarding
		 */
		dispatcher.forward(request, response);
	}
}
