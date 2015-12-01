package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.util.DAO;

/**
 * Class represents "search books" command
 * 
 * @author Dennis
 *
 */
public class ActionSearch extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		String subject = request.getParameter("subject");
		
		request.setAttribute("books", dao.searchBooks(subject));
		request.setAttribute("req", "book_shelf");
		request.setAttribute("sub", subject);
		
		return "/app/book_shelf.jsp";
	}

}
