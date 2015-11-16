package ua.epam.library;

import java.util.List;

public class LibraryModel {
	
	private static DBController controller;
	
	static {
		controller = new DBController();
	}

	public static Student getStudentById(int studentId) {
		
		return controller.findStudentById(studentId);
	}

	public static List<Book> getStudentBooks(int studentId) {
		
		String query = "SELECT name, author, year FROM books WHERE book_id IN "
    			+ "(SELECT book_id FROM subscription WHERE student_id =" + studentId + ")";
		
		return controller.getBooks(query);
	}
	
	public static List<Book> getBooks() {
		String query = "SELECT name, author, year FROM books";
		
		return controller.getBooks(query);
	}
	
	public static boolean registerNewRider(Student reader) {
		
		return controller.registerNewReader(reader);
	}

}
