package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.util.DAO;

public class ActionMain extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		return "app/main.jsp";
	}

}
