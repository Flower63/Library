package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class represents "go to book shelf" command
 * 
 * @author Dennis
 *
 */
public class ActionBookShelf extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(BOOKS, FACTORY.getBookDAO().getBooks());
		request.setAttribute(REQ, "book_shelf");
		
		return "/app/book_shelf.jsp";
	}

}
