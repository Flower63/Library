package ua.epam.library.controller.action;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.util.DAO;

public abstract class Action {
	static final Pattern MAIL_PATTERN = Pattern.compile("\\w+\\.?\\w+@\\w+\\.\\w+\\.?\\w+");
	public abstract String execute(HttpServletRequest request, HttpServletResponse response, DAO dao);
}
