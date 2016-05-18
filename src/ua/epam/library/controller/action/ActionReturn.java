package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;
import ua.epam.library.util.dao.BookDAO;

/**
 * Class represents "return book" command
 * 
 * @author Dennis
 *
 */
public class ActionReturn extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int bookId = Integer.parseInt(request.getParameter(BOOK));
		HttpSession session = request.getSession();
		Reader reader = (Reader) session.getAttribute(READER);
		
		BookDAO dao = FACTORY.getBookDAO();
		
		dao.returnBook(bookId, reader.geteMail());
		
		request.setAttribute(BOOKS, dao.getReaderBooks(reader.geteMail()));
		request.setAttribute(REQ, "my_books");
		
		return "/app/my_books.jsp";
	}

}
