package src;

/**
 * TCSS 360
 * 
 * @author Jasharn Thiara
 * @version 1.0
 * @since   2022-05-2
 */

public class User {
	
	/**
	 * Class represents a User object for team project.
	 */
	
	private String myName; 
	
	private String myPassword;
	
	private boolean hasPriveleges;
	
	public User(String theName, String thePassword, boolean theHasPriveleges) {
		myName = theName;
		myPassword = thePassword;
		hasPriveleges = theHasPriveleges;
	}
	
	/**
	 * getter for User name
	 * @return String UserName
	 */
	public String getUserName() {
		return this.myName;
	}
	
	/**
	 * getter for Password 
	 * @return String myPasssword
	 */
	public String getPassword() {
		return this.myPassword;
	}
	
	/**
	 * getter for hasPriveleges
	 * @return boolean hasPriveleges
	 */
	public Boolean getPriveleges() {
		return this.hasPriveleges;
	}

}
