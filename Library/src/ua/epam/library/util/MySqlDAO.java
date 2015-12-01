package ua.epam.library.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ua.epam.library.entity.Book;
import ua.epam.library.entity.Order;
import ua.epam.library.entity.Reader;
import ua.epam.library.util.connection.MySqlConnectionManager;

/**
 * Implementation of DAO for MySQL database
 * 
 * @author Dennis
 *
 */
public class MySqlDAO extends DAO {
	
	/**
	 * Pre-defined sql queries
	 */
	private final String getReaderQuery = "SELECT first_name, last_name, password, is_librerian FROM readers WHERE email = ?";
	private final String getBooksQuery = "SELECT * FROM books";
	private final String registerReaderQuery = "INSERT INTO readers (email, first_name, last_name, password) VALUES (?, ?, ?, ?)";
	private final String getReaderBooksQuery = "SELECT books.*, subscription.approved FROM books JOIN subscription "
										+ "ON subscription.book_id = books.book_id WHERE subscription.reader = ?;";
	private final String searchBooksQuery = getBooksQuery + " WHERE name LIKE ? OR author LIKE ?";
	private final String checkOrderQuery = "SELECT book_id FROM subscription WHERE book_id = ? AND reader = ?";
	private final String orderBookQueryGet = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
	private final String orderBookQueryPut = "INSERT INTO subscription (reader, book_id, approved) VALUES (?, ?, 0)";
	private final String returnBookQueryGet = "DELETE FROM subscription WHERE reader = ? AND book_id = ?";
	private final String returnBookQueryPut = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";
	private final String getOrdersQuery = "SELECT subscription.reader, subscription.book_id, books.name, readers.first_name, readers.last_name "
										+ "FROM subscription JOIN books ON subscription.book_id = books.book_id JOIN readers "
										+ "ON subscription.reader = readers.email WHERE subscription.approved = 0";
	private final String allowOrderQuery = "UPDATE subscription SET approved = 1 WHERE reader = ? AND book_id = ?";
	
	/**
	 * Constructor
	 */
	public MySqlDAO() {
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
	public Map<Book, Boolean> getReaderBooks(String eMail) {
		Map<Book, Boolean> books = new HashMap<>();
		
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(getReaderBooksQuery)) {
			
			statement.setString(1, eMail);
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("book_id");
				String name = rs.getString("name");
    			String author = rs.getString("author");
    			int year = rs.getInt("year");
    			int quantity = rs.getInt("quantity");
    			boolean approved = rs.getBoolean("approved");
    			
    			books.put(new Book(id, name, author, year, quantity), approved);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}

	@Override
	public List<Book> getBooks() {
		List<Book> books = new ArrayList<>();
		
		try (Connection connection = connectionManager.getConnection();
			 Statement statement = connection.createStatement()) {
			
			ResultSet rs = statement.executeQuery(getBooksQuery);
			
			while (rs.next()) {
				int id = rs.getInt("book_id");
				String name = rs.getString("name");
    			String author = rs.getString("author");
    			int year = rs.getInt("year");
    			int quantity = rs.getInt("quantity");
    			
    			books.add(new Book(id, name, author, year, quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
	@Override
	public List<Book> searchBooks(String subject) {
		List<Book> books = new ArrayList<>();
		
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement statement = connection.prepareStatement(searchBooksQuery)) {
			
			statement.setString(1, "%" + subject + "%");
			statement.setString(2, "%" + subject + "%");
			
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("book_id");
				String name = rs.getString("name");
    			String author = rs.getString("author");
    			int year = rs.getInt("year");
    			int quantity = rs.getInt("quantity");
    			
    			books.add(new Book(id, name, author, year, quantity));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
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
	public Map<Book, Boolean> orderBook(int bookId, Reader reader) {
		
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
		}
		
		return getReaderBooks(reader.geteMail());
	}

	@Override
	public boolean returnBook(int bookId, String eMail) {

		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement withdrawStatement = connection.prepareStatement(returnBookQueryGet);
			 PreparedStatement putStatement = connection.prepareStatement(returnBookQueryPut)) {
			
			connection.setAutoCommit(false);
			
			withdrawStatement.setString(1, eMail);
			withdrawStatement.setInt(2, bookId);
			
			putStatement.setInt(1, bookId);
			
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
