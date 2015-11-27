package ua.epam.library.entity;

public class Reader {
	private String firstName;
	private String lastName;
	private String eMail;
	private String password;
	private Boolean isLibrerian;
	
	public Reader(String firstName, String lastName, String eMail, String password, Boolean isLibrerian) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.password = password;
		this.isLibrerian = isLibrerian;
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

	public Boolean getIsLibrerian() {
		return isLibrerian;
	}
}
