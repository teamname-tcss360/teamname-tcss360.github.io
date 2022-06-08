/**
 * TCSS 360
 * 
 * Class represents a User object for team project. These objects will be created while reading from our user list file. 
 * This class will work together with registration to create a list as well as add users to this list.
*/

package src;

/**
 * User object class. 
 * 
 * @author Jasharn Thiara
 * @version 1.0
 * @since   2022-05-2
 */

public class User {
	
	/**
	 * String field to represent name.
	 */
	private String myName; 

	/**
	 * String field to represent Password.
	 */
	private String myPassword;
	
	/**
	 * String field to represent Email.
	 */
	private String myEmail;

	/**
	 * Boolean field to represent whether the User has priveleges or not. 
	 */
	private boolean hasPriveleges;
	
	/**
	 * User constructor
	 * 
	 * @author Jasharn Thiara
	 * @param theName
	 * @param thePassword
	 * @param theHasPriveleges
	 */
	public User(String theName, String theEmail, String thePassword, boolean theHasPriveleges) {
		myName = theName;
		myPassword = thePassword;
		myEmail = theEmail;
		hasPriveleges = theHasPriveleges;
	}
	
	/**
	 * @author JasharnThiara
	 * @return String UserName ~ getter for User name
	 */
	public String getUserName() {
		return this.myName;
	}
	
	/**
	 * @author Jasharn Thiara
	 * @return String myPasssword ~ getter for Password 
	 */
	public String getPassword() {
		return this.myPassword;
	}
	
	/**
	 * @author Jasharn Thiara
	 * @return String myEmail ~ getter for myEmail
	 */
	public String getEmail() {
		return this.myEmail;
	}

	/**
	 * @author Jasharn Thiara
	 * @return boolean hasPriveleges ~ getter for hasPriveleges
	 */
	public Boolean getPriveleges() {
		return this.hasPriveleges;
	}

}
