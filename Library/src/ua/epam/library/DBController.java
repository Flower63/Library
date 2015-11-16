package ua.epam.library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBController {
	
	private Context context;
    private DataSource datasource;
    
    public DBController(){
    	try {
			context = new InitialContext();
			datasource = (DataSource) context.lookup("java:/comp/env/jdbc/library");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
    
    public Student findStudentById(int id) {
    	
    	String query = "SELECT * FROM STUDENTS WHERE student_id =" + id;

    	Student student = null;
    	
    	try (Statement statement = datasource.getConnection().createStatement()) {
    		ResultSet result;
    		
    		result = statement.executeQuery(query);
    		
    		String firstName = null;
    		String lastName = null;
    		int studentId = 0;
    		String password = null;
    		boolean isLibrerian = false;
    		Lang lang = null;
    		

    		if (result.next()) {
    			studentId = result.getInt("student_id");
    			firstName = result.getString("first_name");
    			lastName = result.getString("last_name");
    			password = result.getString("password");
    			isLibrerian = result.getInt("is_labrerian") == 1;
    			lang = Lang.valueOf(result.getString("lang"));
    			
    			student = new Student(firstName, lastName, studentId, password, isLibrerian, lang);
    		}
    		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return student;
    }
    
    public List<Book> getBooks(String query) {
    	List<Book> books = new ArrayList<>();
    	
    	try (Statement statement = datasource.getConnection().createStatement()) {
    		ResultSet result = statement.executeQuery(query);
    		
    		while (result.next()) {
    			String name = result.getString("name");
    			String author = result.getString("author");
    			int year = result.getInt("year");
    			
    			books.add(new Book(name, author, year));
    		}
    		
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return books;
    }
    
    public boolean registerNewReader(Student reader) {
    	
    	String query = "INSERT INTO students (student_id, first_name, last_name, password, is_labrerian, lang)"
    				+ " VALUES ('" + reader.getStudentId() + "', '" + reader.getFirstName() + "', '"
    				+ reader.getLastName() + "', '" + reader.getPassword() + "', '" + (reader.isLabrerian() ? 1 : 0) + "', '"
    				+ reader.getLang() + "')";
    	
    	try (Statement statement = datasource.getConnection().createStatement()) {
    		statement.executeUpdate(query);
    	} catch (SQLException e) {
    		e.printStackTrace();
			return false;
		}
    	
    	return true;
    }
}
