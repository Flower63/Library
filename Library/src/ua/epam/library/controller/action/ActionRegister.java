package ua.epam.library.controller.action;

import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;

/**
 * Class represents "register" command
 * 
 * @author Dennis
 *
 */
public class ActionRegister extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		String eMail = request.getParameter(EMAIL);
		String firstName = request.getParameter(FIRST_NAME);
		String lastName = request.getParameter(LAST_NAME);
		String password1 = request.getParameter(PASSWORD + "1");
		String password2 = request.getParameter(PASSWORD + "2");
		
		if (eMail.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
			request.setAttribute(ERROR, "error.empty_fields");
			return "register.jsp";
		}
		
		Matcher matcher = MAIL_PATTERN.matcher(eMail);
		
		if (!matcher.matches()) {
			request.setAttribute(ERROR, "error.incorrect_email");
			return "register.jsp";
		}
		
		if (!password1.equals(password2)) {
			request.setAttribute(ERROR, "error.passwords_dont_matches");
			return "register.jsp";
		}
		
		Reader reader = new Reader(firstName, lastName, eMail, password1, false);
		
		if (!FACTORY.getReaderDAO().registerReader(reader)) {
			request.setAttribute(ERROR, "error.email_exists");
			return "register.jsp";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(READER, reader);
		session.setMaxInactiveInterval(60*60*3);
		
		return "app/main.jsp";
	}

}
