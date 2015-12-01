package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.util.DAO;

/**
 * Class represents "go main" command
 * 
 * @author Dennis
 *
 */
public class ActionMain extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		return "app/main.jsp";
	}

}
