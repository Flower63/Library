package ua.epam.library.controller.action;

import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;

/**
 * Class represents "login" command
 * 
 * @author Dennis
 *
 */
public class ActionLogin extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String eMail = request.getParameter(EMAIL);
		Matcher matcher = MAIL_PATTERN.matcher(eMail);
		
		if (!matcher.matches()) {
			request.setAttribute(ERROR, "error.incorrect_email");
			return "login.jsp";
		}
		
		Reader reader = FACTORY.getReaderDAO().getReader(eMail);
		
		if (reader == null) {
			request.setAttribute(ERROR, "error.user_not_found");
			return "login.jsp";
		}
		
		String password = request.getParameter(PASSWORD);
		
		if (!reader.getPassword().equals(password)) {
			request.setAttribute(ERROR, "error.incorrect_password");
			return "login.jsp";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(READER, reader);
		session.setMaxInactiveInterval(60*60*3);
		
		return "app/main.jsp";
	}

}
