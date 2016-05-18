package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;

/**
 * Class represents "my books" command
 * 
 * @author Dennis
 *
 */
public class ActionMyBooks extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Reader reader = (Reader) session.getAttribute(READER);
		
		request.setAttribute(BOOKS, FACTORY.getBookDAO().getReaderBooks(reader.geteMail()));
		request.setAttribute(REQ, "my_books");
		
		return "/app/my_books.jsp";
	}

}
