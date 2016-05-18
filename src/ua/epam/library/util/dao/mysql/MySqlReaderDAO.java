package ua.epam.library.util.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ua.epam.library.entity.Reader;
import ua.epam.library.util.connection.MySqlConnectionManager;
import ua.epam.library.util.dao.ReaderDAO;

/**
 * Dao for readers (users) implementation for MySQL database
 * 
 * @author Dennis
 *
 */
public class MySqlReaderDAO extends ReaderDAO {
	
	/**
	 * Queries
	 */
	private final String getReaderQuery = "SELECT first_name, last_name, password, is_librerian FROM readers WHERE email = ?";
	private final String registerReaderQuery = "INSERT INTO readers (email, first_name, last_name, password) VALUES (?, ?, ?, ?)";
	
	/**
	 * Constructor
	 */
	public MySqlReaderDAO() {
		super(MySqlConnectionManager.getInstace());
	}

	@Override
	public Reader getReader(String eMail) {
		Reader reader = null;
		
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(getReaderQuery)) {
			
			statement.setString(1, eMail);
			
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String password = rs.getString("password");
				boolean isLibrerian = rs.getInt("is_librerian") == 1;
				
				reader = new Reader(firstName, lastName, eMail, password, isLibrerian);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reader;
	}

	@Override
	public boolean registerReader(Reader reader) {
		
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(registerReaderQuery)) {
			
			statement.setString(1, reader.geteMail());
			statement.setString(2, reader.getFirstName());
			statement.setString(3, reader.getLastName());
			statement.setString(4, reader.getPassword());
			
			int res = statement.executeUpdate();
			
			if (res == 0) {
				return false;
			}
		} catch (MySQLIntegrityConstraintViolationException e) {
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
