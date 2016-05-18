package ua.epam.library.entity;

/**
 * Entity for order
 * 
 * @author Dennis
 *
 */
public class Order {
	
	private int bookId;
	private String bookName;
	private String readerEmail;
	private String readerFirstName;
	private String readerLastName;
	
	public Order(int bookId, String bookName, String readerEmail, String readerFirstName, String readerLastName) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.readerEmail = readerEmail;
		this.readerFirstName = readerFirstName;
		this.readerLastName = readerLastName;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public String getReaderEmail() {
		return readerEmail;
	}

	public String getReaderFirstName() {
		return readerFirstName;
	}

	public String getReaderLastName() {
		return readerLastName;
	}
}
