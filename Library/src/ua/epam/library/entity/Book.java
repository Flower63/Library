package ua.epam.library.entity;

/**
 * Entity for book
 * 
 * @author Dennis
 *
 */
public class Book {

	private int id;
	private String name;
	private String author;
	private int year;
	private int quantity;
	
	public Book(int id, String name, String author, int year, int quantity) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.year = year;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getId() {
		return id;
	}
}
