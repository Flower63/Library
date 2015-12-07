package ua.epam.library.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.entity.Order;
import ua.epam.library.util.dao.OrderDAO;

/**
 * Class represents "allow order" command
 * 
 * @author Dennis
 *
 */
public class ActionAllowOrder extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int bookId = Integer.parseInt(request.getParameter(BOOK_ID));
		String eMail = request.getParameter(READER);
		OrderDAO dao = FACTORY.getOrderDAO();
		
		dao.allowOrder(bookId, eMail);
		
		List<Order> orders = dao.getOrders();
		
		request.setAttribute(ORDERS, orders);
		request.setAttribute(REQ, ORDERS);
		
		return "/app/orders.jsp";
	}

}
