package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;
import ua.epam.library.util.DAO;

/**
 * Class represents "my books" command
 * 
 * @author Dennis
 *
 */
public class ActionMyBooks extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		HttpSession session = request.getSession();
		Reader reader = (Reader) session.getAttribute("reader");
		
		request.setAttribute("books", dao.getReaderBooks(reader.geteMail()));
		request.setAttribute("req", "my_books");
		
		return "/app/my_books.jsp";
	}

}
