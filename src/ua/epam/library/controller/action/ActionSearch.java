package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class represents "search books" command
 * 
 * @author Dennis
 *
 */
public class ActionSearch extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String subject = request.getParameter(SUBJECT);
		
		request.setAttribute(BOOKS, FACTORY.getBookDAO().searchBooks(subject));
		request.setAttribute(REQ, "book_shelf");
		request.setAttribute(SUB, subject);
		
		return "/app/book_shelf.jsp";
	}

}
