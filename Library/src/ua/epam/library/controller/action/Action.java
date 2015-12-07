package ua.epam.library.controller.action;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.util.dao.DaoFactory;
import ua.epam.library.util.dao.DaoFactory.DAOType;

/**
 * Abstract class for actions in application
 * 
 * @author Dennis
 *
 */
public abstract class Action {
	
	/**
	 * Pattern to match e-mails
	 */
	static final Pattern MAIL_PATTERN = Pattern.compile("\\w+\\.?\\w+@\\w+\\.\\w+\\.?\\w+");
	
	/**
	 * HTTP request parameters
	 */
	static final String READER = "reader";
	static final String BOOK_ID = "book_id";
	static final String EMAIL = "email";
	static final String BOOK = "book";
	static final String BOOKS = "books";
	static final String FIRST_NAME = "first_name";
	static final String LAST_NAME = "last_name";
	static final String PASSWORD = "password";
	static final String ERROR = "error";
	static final String SUBJECT = "subject";
	static final String REQ = "req";
	static final String SUB = "sub";
	static final String ORDERS = "orders";
	
	/**
	 * DAO factory instance
	 */
	static final DaoFactory FACTORY;
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("ua.epam.library.properties.config");
		
		FACTORY = DaoFactory.getFactory(DAOType.valueOf(rb.getString("type")));
	}
	
	/**
	 * Method to perform action
	 * 
	 * @param request Http servlet request
	 * @param response Http servlet response
	 * @param dao Instance of data access object (DAO)
	 * @return path to forward request
	 */
	public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}
