package ua.epam.library.controller.action;

import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.epam.library.entity.Reader;
import ua.epam.library.util.DAO;

public class ActionLogin extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, DAO dao) {
		String eMail = request.getParameter("email");
		Matcher matcher = MAIL_PATTERN.matcher(eMail);
		
		if (!matcher.matches()) {
			request.setAttribute("error", "incorrect_email");
			return "login.jsp";
		}
		
		Reader reader = dao.getReader(eMail);
		
		if (reader == null) {
			request.setAttribute("error", "user_not_found");
			return "login.jsp";
		}
		
		String password = request.getParameter("password");
		
		if (!reader.getPassword().equals(password)) {
			request.setAttribute("error", "incorrect_password");
			return "login.jsp";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("reader", reader);
		session.setMaxInactiveInterval(60*60*3);
		
		return "app/main.jsp";
	}

}
