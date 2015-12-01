package ua.epam.library.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.entity.Order;
import ua.epam.library.util.DAO;

/**
 * Class represents "deny order" command
 * 
 * @author Dennis
 *
 */
public class ActionDenyOrder extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		int bookId = Integer.parseInt(request.getParameter("book_id"));
		String eMail = request.getParameter("reader");
		
		dao.returnBook(bookId, eMail);
		
		List<Order> orders = dao.getOrders();
		
		request.setAttribute("orders", orders);
		request.setAttribute("req", "orders");
		
		return "/app/orders.jsp";
	}

}
