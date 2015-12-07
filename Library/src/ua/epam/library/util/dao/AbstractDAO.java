package ua.epam.library.util.dao;

import ua.epam.library.util.connection.ConnectionManager;

/**
 * Abstract class for DAOs. Required to keep all types in common array,
 * and keep connection manager instance
 * 
 * @author Dennis
 *
 */
public abstract class AbstractDAO {
	
	/**
	 * Instance of connection manager
	 */
	protected ConnectionManager connectionManager;

	/**
	 * Constructor
	 * 
	 * @param connectionManager Implemented instance for connection manager
	 */
	public AbstractDAO(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
}
