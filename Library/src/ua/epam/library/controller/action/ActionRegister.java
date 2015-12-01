package ua.epam.library.controller.action;

import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;
import ua.epam.library.util.DAO;

/**
 * Class represents "register" command
 * 
 * @author Dennis
 *
 */
public class ActionRegister extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		
		String eMail = request.getParameter("email");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		if (eMail.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
			request.setAttribute("error", "error.empty_fields");
			return "register.jsp";
		}
		
		Matcher matcher = MAIL_PATTERN.matcher(eMail);
		
		if (!matcher.matches()) {
			request.setAttribute("error", "error.incorrect_email");
			return "register.jsp";
		}
		
		if (!password1.equals(password2)) {
			request.setAttribute("error", "error.passwords_dont_matches");
			return "register.jsp";
		}
		
		Reader reader = new Reader(firstName, lastName, eMail, password1, false);
		
		if (!dao.registerReader(reader)) {
			request.setAttribute("error", "error.email_exists");
			return "register.jsp";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("reader", reader);
		session.setMaxInactiveInterval(60*60*3);
		
		return "app/main.jsp";
	}

}
