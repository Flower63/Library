package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class represents "go main" command
 * 
 * @author Dennis
 *
 */
public class ActionMain extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "app/main.jsp";
	}

}
