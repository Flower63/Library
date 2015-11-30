package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.util.DAO;

public class ActionLogout extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		HttpSession session = request.getSession();
		session.removeAttribute("reader");
		return "index.jsp";
	}

}
