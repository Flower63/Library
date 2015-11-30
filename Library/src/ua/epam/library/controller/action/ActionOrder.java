package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;
import ua.epam.library.util.DAO;

public class ActionOrder extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		int bookId = Integer.parseInt(request.getParameter("book"));
		HttpSession session = request.getSession();
		Reader reader = (Reader) session.getAttribute("reader");
		
		if (dao.checkOrderExistance(bookId, reader.geteMail())) {
			request.setAttribute("error", "already_taken");
			return "/app/book_shelf.jsp";
		}
		
		request.setAttribute("books", dao.orderBook(bookId, reader));
		request.setAttribute("req", "my_books");
		
		return "/app/my_books.jsp";
	}

}
