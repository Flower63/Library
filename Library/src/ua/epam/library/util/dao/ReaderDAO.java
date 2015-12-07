package ua.epam.library.util.dao;

import ua.epam.library.entity.Reader;
import ua.epam.library.util.connection.ConnectionManager;

/**
 * Abstract class to represent dao for readers (users)
 * 
 * @author Dennis
 *
 */
public abstract class ReaderDAO extends AbstractDAO{
	
	/**
	 * Constructor
	 * 
	 * @param connectionManager Implemented instance for connection manager
	 */
	public ReaderDAO(ConnectionManager connectionManager) {
		super(connectionManager);
	}

	/**
	 * Finding reader by given e-mail address
	 * 
	 * @param eMail address to find account
	 * @return reader account, corresponding given e-mail
	 * @return null, if such user not found
	 */
	public abstract Reader getReader(String eMail);
	
	/**
	 * Registering new user in system
	 * 
	 * @param reader new member of our lovely team
	 * @return is the operation success
	 */
	public abstract boolean registerReader(Reader reader);
}
