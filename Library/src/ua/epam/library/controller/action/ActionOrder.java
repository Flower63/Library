package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;
import ua.epam.library.util.DAO;

/**
 * Class represents "order book" command
 * 
 * @author Dennis
 *
 */
public class ActionOrder extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		int bookId = Integer.parseInt(request.getParameter("book"));
		HttpSession session = request.getSession();
		Reader reader = (Reader) session.getAttribute("reader");
		
		if (dao.checkOrderExistance(bookId, reader.geteMail())) {
			request.setAttribute("error", "error.already_taken");
			request.setAttribute("books", dao.getBooks());
			request.setAttribute("req", "book_shelf");
			return "/app/book_shelf.jsp";
		}
		
		request.setAttribute("books", dao.orderBook(bookId, reader));
		request.setAttribute("req", "my_books");
		
		return "/app/my_books.jsp";
	}

}
