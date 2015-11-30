package ua.epam.library.util;

public abstract class DAOFactory {
	
	public static DAO getDao(DAOType type) {
		switch(type) {
		case MYSQL:
			return type.getInstance();
		default:
			return null;
		}
	}
	
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
