package ua.epam.library.util.dao;

import java.util.List;
import java.util.Map;

import ua.epam.library.entity.Book;
import ua.epam.library.util.connection.ConnectionManager;

/**
 * Abstract class to represent dao for books
 * 
 * @author Dennis
 *
 */
public abstract class BookDAO extends AbstractDAO {

	/**
	 * Constructor
	 * 
	 * @param connectionManager Implemented instance for connection manager
	 */
	public BookDAO(ConnectionManager connectionManager) {
		super(connectionManager);
	}
	
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
	 * Returning book to book shelf (library)
	 * 
	 * @param bookId id of book of interest
	 * @param eMail address to find account
	 * @return is the operation success
	 */
	public abstract boolean returnBook(int bookId, String eMail);
}
