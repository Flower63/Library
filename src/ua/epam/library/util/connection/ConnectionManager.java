package ua.epam.library.util.connection;

import java.sql.Connection;

/**
 * Abstract class to manage database connection
 * 
 * @author Dennis
 *
 */
public abstract class ConnectionManager {
	
	/**
	 * Getting available connection
	 * 
	 * @return connection instance
	 */
	public abstract Connection getConnection();
}
