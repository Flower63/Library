package ua.epam.library;

public class Student {

	private String firstName;
	private String lastName;
	private int studentId;
	private String password;
	private boolean isLabrerian;
	private Lang lang;
	
	public Student(String firstName, String lastName, int studentId, String password, boolean isLabrerian, Lang lang) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentId = studentId;
		this.password = password;
		this.isLabrerian = isLabrerian;
		this.lang = lang;
	}

	public Lang getLang() {
		return lang;
	}

	public void setLang(Lang lang) {
		this.lang = lang;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getStudentId() {
		return studentId;
	}
	
	public String getPassword() {
		return password;
	}

	public boolean isLabrerian() {
		return isLabrerian;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " " + studentId + " " + password + " " + lang;
	}
}
