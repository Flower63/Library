package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;

/**
 * Class represents "order book" command
 * 
 * @author Dennis
 *
 */
public class ActionOrder extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int bookId = Integer.parseInt(request.getParameter(BOOK));
		HttpSession session = request.getSession();
		Reader reader = (Reader) session.getAttribute(READER);
		
		if (FACTORY.getOrderDAO().checkOrderExistance(bookId, reader.geteMail())) {
			request.setAttribute(ERROR, "error.already_taken");
			request.setAttribute(BOOKS, FACTORY.getBookDAO().getBooks());
			request.setAttribute(REQ, "book_shelf");
			return "/app/book_shelf.jsp";
		}
		
		FACTORY.getOrderDAO().orderBook(bookId, reader);
		
		request.setAttribute(BOOKS, FACTORY.getBookDAO().getReaderBooks(reader.geteMail()));
		request.setAttribute(REQ, "my_books");
		
		return "/app/my_books.jsp";
	}

}
