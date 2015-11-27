package ua.epam.library.controller.action;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
	
	private static ActionFactory instance;
	private final Map<String, Action> actions;
	
	private ActionFactory() {
		actions = new HashMap<>();
		
		actions.put("book_shelf", new ActionBookShelf());
	}
	
	public static ActionFactory getInstance() {
		if (instance == null) {
			instance = new ActionFactory();
		}
		
		return instance;
	}
	
	public Action getAction(String command) {
		return actions.get(command);
	}
}
