package ua.epam.library.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.entity.Order;
import ua.epam.library.util.DAO;

/**
 * Class represents "get orders" command
 * 
 * @author Dennis
 *
 */
public class ActionGetOrders extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		
		List<Order> orders = dao.getOrders();
		
		request.setAttribute("orders", orders);
		request.setAttribute("req", "orders");
		
		return "/app/orders.jsp";
	}

}
