package ua.epam.library.util.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.epam.library.entity.Book;
import ua.epam.library.util.connection.MySqlConnectionManager;
import ua.epam.library.util.dao.BookDAO;

/**
 * Dao for books implementation for MySQL database
 * 
 * @author Dennis
 *
 */
public class MySqlBookDAO extends BookDAO {
	
	/**
	 * Queries
	 */
	private final String getBooksQuery = "SELECT * FROM books";
	private final String getReaderBooksQuery = "SELECT books.*, subscription.approved FROM books JOIN subscription "
			+ "ON subscription.book_id = books.book_id WHERE subscription.reader = ?";
	private final String searchBooksQuery = getBooksQuery + " WHERE name LIKE ? OR author LIKE ?";
	private final String returnBookQueryGet = "DELETE FROM subscription WHERE reader = ? AND book_id = ?";
	private final String returnBookQueryPut = "UPDATE books SET quantity = quantity + 1 WHERE book_id = ?";
	
	/**
	 * Constructor
	 */
	public MySqlBookDAO() {
		super(MySqlConnectionManager.getInstace());
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

}
