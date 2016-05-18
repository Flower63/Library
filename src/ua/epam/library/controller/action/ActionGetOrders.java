package ua.epam.library.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.entity.Order;

/**
 * Class represents "get orders" command
 * 
 * @author Dennis
 *
 */
public class ActionGetOrders extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<Order> orders = FACTORY.getOrderDAO().getOrders();
		
		request.setAttribute(ORDERS, orders);
		request.setAttribute(REQ, ORDERS);
		
		return "/app/orders.jsp";
	}

}
