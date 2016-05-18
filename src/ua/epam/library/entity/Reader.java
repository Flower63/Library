package ua.epam.library.entity;

/**
 * Entity for reader
 * 
 * @author Dennis
 *
 */
public class Reader {
	private String firstName;
	private String lastName;
	private String eMail;
	private String password;
	private Boolean librerian;
	
	public Reader(String firstName, String lastName, String eMail, String password, Boolean librerian) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.password = password;
		this.librerian = librerian;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String geteMail() {
		return eMail;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getLibrerian() {
		return librerian;
	}
}
