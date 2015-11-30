package ua.epam.library.util;

import java.util.List;
import java.util.Map;

import ua.epam.library.entity.Book;
import ua.epam.library.entity.Reader;
import ua.epam.library.util.connection.ConnectionManager;

public abstract class DAO {

	protected ConnectionManager connectionManager;
	
	public DAO(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	public abstract Reader getReader(String eMail);
	
	public abstract Map<Book, Boolean> getReaderBooks(String eMail);
	
	public abstract List<Book> getBooks();
	
	public abstract List<Book> searchBooks(String subject);
	
	public abstract boolean registerReader(Reader reader);
	
	public abstract boolean checkOrderExistance(int bookId, String eMail);
	
	public abstract Map<Book, Boolean> orderBook(int bookId, Reader reader);
}
