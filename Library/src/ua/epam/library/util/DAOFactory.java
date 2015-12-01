package ua.epam.library.util;

/**
 * Factory for DAOs
 * 
 * @author Dennis
 *
 */
public abstract class DAOFactory {
	
	/**
	 * Getting dao of representative type
	 * 
	 * @param type of desired DAO
	 * @return instance of DAO
	 */
	public static DAO getDao(DAOType type) {
		switch(type) {
		case MYSQL:
			return type.getInstance();
		default:
			return null;
		}
	}
	
	/**
	 * Enum to hold different representations of DAOs
	 * 
	 * @author Dennis
	 *
	 */
	public enum DAOType {
		MYSQL(new MySqlDAO());
		
		private DAO instance;
		
		private DAOType(DAO instance) {
			this.instance = instance;
		}
		
		public DAO getInstance() {
			return instance;
		}
	}
}
