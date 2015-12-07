package ua.epam.library.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.entity.Order;

/**
 * Class represents "deny order" command
 * 
 * @author Dennis
 *
 */
public class ActionDenyOrder extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		int bookId = Integer.parseInt(request.getParameter(BOOK_ID));
		String eMail = request.getParameter(READER);
		
		FACTORY.getBookDAO().returnBook(bookId, eMail);
		
		List<Order> orders = FACTORY.getOrderDAO().getOrders();
		
		request.setAttribute(ORDERS, orders);
		request.setAttribute(REQ, ORDERS);
		
		return "/app/orders.jsp";
	}

}
