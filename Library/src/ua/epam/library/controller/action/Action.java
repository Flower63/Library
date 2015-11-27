package ua.epam.library.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.epam.library.util.DAO;

public abstract class Action {
	public abstract String execute(HttpServletRequest request, HttpServletResponse response, DAO dao);
}
