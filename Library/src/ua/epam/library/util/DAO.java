package ua.epam.library.util;

import java.util.List;
import java.util.Map;

import ua.epam.library.entity.Book;
import ua.epam.library.entity.Order;
import ua.epam.library.entity.Reader;
import ua.epam.library.util.connection.ConnectionManager;

/**
 * Abstract data access object for library
 * 
 * @author Dennis
 *
 */
public abstract class DAO {

	/**
	 * Instance of connection manager
	 */
	protected ConnectionManager connectionManager;
	
	/**
	 * Constructor
	 * 
	 * @param connectionManager Implemented instance for connection manager
	 */
	public DAO(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	/**
	 * Finding reader by given e-mail address
	 * 
	 * @param eMail address to find account
	 * @return reader account, corresponding given e-mail
	 * @return null, if such user not found
	 */
	public abstract Reader getReader(String eMail);
	
	/**
	 * Getting list of books that belongs to user (reader)
	 * or ordered by user
	 * 
	 * @param eMail address to find account
	 * @return map of books. Key actually contains book, value (boolean) shows allowed this book to reader or not
	 */
	public abstract Map<Book, Boolean> getReaderBooks(String eMail);
	
	/**
	 * Getting list of all available books in library
	 * 
	 * @return list of books
	 */
	public abstract List<Book> getBooks();
	
	/**
	 * Searching for books, that corresponding given subject
	 * 
	 * @param subject keyword for search
	 * @return list of matching books (if present)
	 */
	public abstract List<Book> searchBooks(String subject);
	
	/**
	 * Registering new user in system
	 * 
	 * @param reader new member of our lovely team
	 * @return is the operation success
	 */
	public abstract boolean registerReader(Reader reader);
	
	/**
	 * Checking if user already have this book.
	 * In our library you cannot take all books home, you know!
	 * 
	 * @param bookId id of book of interest
	 * @param eMail address to find account
	 * @return is user have the book
	 */
	public abstract boolean checkOrderExistance(int bookId, String eMail);
	
	/**
	 * Ordering book. You have to ask permission of librarian to take book
	 * 
	 * @param bookId id of book of interest
	 * @param reader eMail address to find account
	 * @return list of reader books
	 */
	public abstract Map<Book, Boolean> orderBook(int bookId, Reader reader);
	
	/**
	 * Returning book to book shelf (library)
	 * 
	 * @param bookId id of book of interest
	 * @param eMail address to find account
	 * @return is the operation success
	 */
	public abstract boolean returnBook(int bookId, String eMail);
	
	/**
	 * Getting active orders. For librarians
	 * 
	 * @return list of orders
	 */
	public abstract List<Order> getOrders();
	
	/**
	 * Giving permission to take book. For librarians
	 * 
	 * @param bookId id of book of interest
	 * @param eMail address to find account
	 * @return is the operation success
	 */
	public abstract boolean allowOrder(int bookId, String eMail);
}
