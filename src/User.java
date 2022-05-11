/**
 * TCSS 360
 * 
 * @author jasht
 * @version Spring 2022
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

}
