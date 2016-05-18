package ua.epam.library.util.connection;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Implementation of connection manager to manage MySQL connections
 * 
 * @author Dennis
 *
 */
public class MySqlConnectionManager extends ConnectionManager {

	private Context context;
    private DataSource datasource;
    private static MySqlConnectionManager instance;
    
    /**
     * Static method to get single instance (singleton)
     * 
     * @return instance of manager
     */
    public static MySqlConnectionManager getInstace() {
    	if (instance == null) {
    		instance = new MySqlConnectionManager();
    	}
    	
    	return instance;
    }
    
    /**
     * Constructor
     */
    private MySqlConnectionManager() {
 
    	try {
			context = new InitialContext();
			datasource = (DataSource) context.lookup("java:/comp/env/jdbc/library");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Override
	public Connection getConnection() {
		
		Connection connection = null;
		
		try {
			connection = datasource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}

}
