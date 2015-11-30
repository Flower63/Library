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

import ua.epam.library.entity.Book;
import ua.epam.library.entity.Reader;
import ua.epam.library.util.connection.MySqlConnectionManager;

public class MySqlDAO extends DAO {
	private String getReaderQuery = "SELECT first_name, last_name, password, is_librerian FROM readers WHERE email = ?";
	private String getBooksQuery = "SELECT * FROM books";
	private String registerReaderQuery = "INSERT INTO readers (email, first_name, last_name, password) VALUES (?, ?, ?, ?)";
	private String getReaderBooksQuery = "SELECT books.*, subscription.approved FROM books, subscription "
										+ "WHERE books.book_id IN (SELECT subscription.book_id FROM subscription WHERE reader = ?)";
	private String searchBooksQuery = getBooksQuery + " WHERE name LIKE ? OR author LIKE ?";
	private String checkOrderQuery = "SELECT book_id FROM subscription WHERE book_id = ? AND reader = ?";
	private String orderBookQuery = "UPDATE books SET quantity = ? WHERE book_id = ?";
	private String getBookQuantityQuery = "SELECT quantity FROM books WHERE book_id = ?";
	private String putBookQuery = "INSERT INTO subscription (reader, book_id, first_name, last_name, approved) VALUES "
									+ "(?, ?, ?, ?, 0)";
	
	public MySqlDAO() {
		super(MySqlConnectionManager.getInstace());
	}

	@Override
	public Reader getReader(String eMail) {
		Reader reader = null;
		
		try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(getReaderQuery)) {
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
		
		try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(getReaderBooksQuery)) {
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
		
		try (Statement statement = connectionManager.getConnection().createStatement()) {
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
		
		try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(searchBooksQuery)) {
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
		
		try(PreparedStatement statement = connectionManager.getConnection().prepareStatement(registerReaderQuery)) {
			
			statement.setString(1, reader.geteMail());
			statement.setString(2, reader.getFirstName());
			statement.setString(3, reader.getLastName());
			statement.setString(4, reader.getPassword());
			
			int res = statement.executeUpdate();
			
			if (res == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean checkOrderExistance(int bookId, String eMail) {

		try (PreparedStatement statement = connectionManager.getConnection().prepareStatement(checkOrderQuery)) {
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
			 PreparedStatement getQuantity = connection.prepareStatement(getBookQuantityQuery);
			 PreparedStatement withdrawStatement = connection.prepareStatement(orderBookQuery);
			 PreparedStatement putStatement = connection.prepareStatement(putBookQuery)) {
			
			connection.setAutoCommit(false);
			
			getQuantity.setInt(1, bookId);
			
			ResultSet rs = getQuantity.executeQuery();
			
			int quantity = 0;
			
			if (rs.next()) {
				quantity = rs.getInt("quantity");
			}
			
			withdrawStatement.setInt(1, quantity - 1);
			withdrawStatement.setInt(2, bookId);
			
			putStatement.setString(1, reader.geteMail());
			putStatement.setInt(2, bookId);
			putStatement.setString(3, reader.getFirstName());
			putStatement.setString(4, reader.getLastName());
			
			withdrawStatement.executeUpdate();
			putStatement.executeUpdate();
			
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return getReaderBooks(reader.geteMail());
	}
}
