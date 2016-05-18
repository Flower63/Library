package ua.epam.library.controller.action;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for actions
 * 
 * @author Dennis
 *
 */
public class ActionFactory {
	
	/**
	 * Instance of factory
	 */
	private static ActionFactory instance;
	
	/**
	 * Pool of actions
	 */
	private final Map<String, Action> actions;
	
	/**
	 * Constructor
	 */
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
		actions.put("return", new ActionReturn());
		actions.put("orders", new ActionGetOrders());
		actions.put("deny_order", new ActionDenyOrder());
		actions.put("allow_order", new ActionAllowOrder());
	}
	
	/**
	 * Method to get single instance (singleton)
	 * 
	 * @return
	 */
	public static ActionFactory getInstance() {
		if (instance == null) {
			instance = new ActionFactory();
		}
		
		return instance;
	}
	
	/**
	 * Method to get action instance, depending on input string
	 * 
	 * @param action name of action
	 * @return instance of Action class
	 */
	public Action getAction(String action) {
		return actions.get(action);
	}
}
