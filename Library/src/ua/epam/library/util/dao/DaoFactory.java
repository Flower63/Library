package ua.epam.library.util.dao;

import ua.epam.library.util.dao.mysql.MySqlDaoFactory;

/**
 * Abstract factory to get proper dao
 * 
 * @author Dennis
 *
 */
public abstract class DaoFactory {

	/**
	 * Getting dao for readers
	 * 
	 * @return
	 */
	public abstract ReaderDAO getReaderDAO();
	
	/**
	 * Getting dao for books
	 * 
	 * @return
	 */
	public abstract BookDAO getBookDAO();
	
	/**
	 * Getting dao for orders
	 * 
	 * @return
	 */
	public abstract OrderDAO getOrderDAO();
	
	/**
	 * Static factory distributor
	 * 
	 * @param type - Type of required factory
	 * @return Factory instance
	 */
	public static DaoFactory getFactory(DAOType type) {
		return type.getFactory();
	}
	
	/**
	 * Enum to hold different factory types and implementations
	 * 
	 * @author Dennis
	 *
	 */
	public enum DAOType {
		MYSQL (new MySqlDaoFactory());
		
		/**
		 * Factory instance
		 */
		private DaoFactory factory;
		
		/**
		 * Constructor
		 * 
		 * @param factory Factory implementation instance
		 */
		private DAOType(DaoFactory factory) {
			this.factory = factory;
		}
		
		/**
		 * Factory getter
		 * 
		 * @return Factory
		 */
		public DaoFactory getFactory() {
			return factory;
		}
	}
}
