/**
 * 
 */
package model;

/**
 * @author nikolalsvk
 * Model of an User
 */
public class User {
	private String username;
	private String password;
	private String name;
	private String lastName;
	private String role;
	private String phoneNumber;
	private String email;
	
	/**
	 * Default constructor for making an instance of an User.
	 */
	public User() {
		
	}
	
	/**
	 * Constructor for creating an instance of an User
	 * @param username Users username
	 * @param password Users password
	 * @param name Users name
	 * @param lastName Users last name
	 * @param role Users role in system
	 * @param phoneNumber Users phoneNumber
	 * @param email Users e-mail
	 */
	public User(String username, String password, String name, String lastName,
			String role, String phoneNumber, String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}


	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}


	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}


	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
