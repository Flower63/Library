package ua.epam.library.controller.action;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.util.DAO;

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
	 * Method to perform action
	 * 
	 * @param request Http servlet request
	 * @param response Http servlet response
	 * @param dao Instance of data access object (DAO)
	 * @return path to forward request
	 */
	public abstract String execute(HttpServletRequest request, HttpServletResponse response, DAO dao);
}
