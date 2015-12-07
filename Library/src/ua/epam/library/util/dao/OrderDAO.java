package ua.epam.library.util.dao;

import java.util.List;

import ua.epam.library.entity.Order;
import ua.epam.library.entity.Reader;
import ua.epam.library.util.connection.ConnectionManager;

/**
 * Abstract class to represent dao for orders
 * 
 * @author Dennis
 *
 */
public abstract class OrderDAO extends AbstractDAO {
	
	/**
	 * Constructor
	 * 
	 * @param connectionManager Implemented instance for connection manager
	 */
	public OrderDAO(ConnectionManager connectionManager) {
		super(connectionManager);
	}
	
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
	public abstract boolean orderBook(int bookId, Reader reader);
	
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
