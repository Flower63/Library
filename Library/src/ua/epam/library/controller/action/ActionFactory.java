package ua.epam.library.controller.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
	
	private static ActionFactory instance;
	private final Map<String, Action> actions;
	
	private ActionFactory() {
		actions = new HashMap<>();
		
		actions.put("book_shelf", new ActionBookShelf());
		actions.put("login", new ActionLogin());
		actions.put("main", new ActionMain());
		actions.put("register", new ActionRegister());
		actions.put("logout", new ActionLogout());
		actions.put("my_books", new ActionMyBooks());
		actions.put("search", new ActionSearch());
		actions.put("order", new ActionOrder());
	}
	
	public static ActionFactory getInstance() {
		if (instance == null) {
			instance = new ActionFactory();
		}
		
		return instance;
	}
	
	public Action getAction(String action) {
		return actions.get(action);
	}
}
