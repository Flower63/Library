package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.util.DAO;

/**
 * Class represents "go to book shelf" command
 * 
 * @author Dennis
 *
 */
public class ActionBookShelf extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		request.setAttribute("books", dao.getBooks());
		request.setAttribute("req", "book_shelf");
		
		return "/app/book_shelf.jsp";
	}

}
