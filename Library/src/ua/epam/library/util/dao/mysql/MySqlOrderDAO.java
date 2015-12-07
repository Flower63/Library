package ua.epam.library.util.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.epam.library.entity.Order;
import ua.epam.library.entity.Reader;
import ua.epam.library.util.connection.MySqlConnectionManager;
import ua.epam.library.util.dao.OrderDAO;

/**
 * Dao for orders implementation for MySQL database
 * 
 * @author Dennis
 *
 */
public class MySqlOrderDAO extends OrderDAO {
	
	/**
	 * Queries
	 */
	private final String checkOrderQuery = "SELECT book_id FROM subscription WHERE book_id = ? AND reader = ?";
	private final String orderBookQueryGet = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
	private final String orderBookQueryPut = "INSERT INTO subscription (reader, book_id, approved) VALUES (?, ?, 0)";
	private final String getOrdersQuery = "SELECT subscription.reader, subscription.book_id, books.name, readers.first_name, readers.last_name "
			+ "FROM subscription JOIN books ON subscription.book_id = books.book_id JOIN readers "
			+ "ON subscription.reader = readers.email WHERE subscription.approved = 0";
	private final String allowOrderQuery = "UPDATE subscription SET approved = 1 WHERE reader = ? AND book_id = ?";
	
	/**
	 * Constructor
	 */
	public MySqlOrderDAO() {
		super(MySqlConnectionManager.getInstace());
	}

	@Override
	public boolean checkOrderExistance(int bookId, String eMail) {

		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(checkOrderQuery)) {
			
			statement.setInt(1, bookId);
			statement.setString(2, eMail);
			
			ResultSet rs = statement.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean orderBook(int bookId, Reader reader) {
		
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement withdrawStatement = connection.prepareStatement(orderBookQueryGet);
			 PreparedStatement putStatement = connection.prepareStatement(orderBookQueryPut)) {
			
			connection.setAutoCommit(false);
			
			withdrawStatement.setInt(1, bookId);
			
			putStatement.setString(1, reader.geteMail());
			putStatement.setInt(2, bookId);
			
			withdrawStatement.executeUpdate();
			putStatement.executeUpdate();
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		
		try (Connection connection = connectionManager.getConnection();
			 Statement statement = connection.createStatement()) {
			
			ResultSet rs = statement.executeQuery(getOrdersQuery);
			
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("name");
				String readerEmail = rs.getString("reader");
				String readerFirstName = rs.getString("first_name");
				String readerLastName = rs.getString("last_name");
				
				orders.add(new Order(bookId, bookName, readerEmail, readerFirstName, readerLastName));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public boolean allowOrder(int bookId, String eMail) {

		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(allowOrderQuery)) {
			
			statement.setString(1, eMail);
			statement.setInt(2, bookId);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
