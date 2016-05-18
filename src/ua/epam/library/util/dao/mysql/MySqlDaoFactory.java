package ua.epam.library.util.dao.mysql;

import java.util.HashMap;
import java.util.Map;

import ua.epam.library.util.dao.*;

/**
 * DaoFactory Implementation for MySQL database
 * 
 * @author Dennis
 *
 */
public class MySqlDaoFactory extends DaoFactory {
	
	/**
	 * Pool of DAOs
	 */
	private Map<String, AbstractDAO> daos = new HashMap<>();
	
	/**
	 * Constructor
	 */
	public MySqlDaoFactory() {
		daos.put("reader", new MySqlReaderDAO());
		daos.put("book", new MySqlBookDAO());
		daos.put("order", new MySqlOrderDAO());
	}

	@Override
	public ReaderDAO getReaderDAO() {
		return (ReaderDAO) daos.get("reader");
	}

	@Override
	public BookDAO getBookDAO() {
		return (BookDAO) daos.get("book");
	}

	@Override
	public OrderDAO getOrderDAO() {
		return (OrderDAO) daos.get("order");
	}

}
