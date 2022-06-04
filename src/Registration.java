/**
 * TCSS 360
 * 
 * Class will have functionality to read from a txt and create an array list of users. 
 * Will also provide login in checks to see if the fields match up with any users in the database.
 * Functionality to add new users to our list. 
 */

package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class represents Registration in the TeamName project.
 * 
 * @author  Jasharn Thiara
 * @version 1.0
 * @since   2022-04-10
 */

public class Registration {
	
	/**
     * User Storage File.
     */
    public static final File USERFILE = new File("UserFile.txt");

    /**
     * The registered user list for signin.
     */
    ArrayList<User> myUserList;

    /**
     * @author Jasharn Thiara
     * @throws FileNotFoundException 
     * 
     * Constructs a sigin/registration system.
     */
    public Registration() throws FileNotFoundException {
        myUserList = readItemsFromFile(USERFILE);
    }

    /**
     * @author Jasharn Thiara
     * @return myUserList ~ Getter for myUserList.
     */
    public ArrayList<User> getMyUserList() {
        return myUserList;
    }
    
    /**
     * @author Jasharn Thiara
     * @return boolean login result
     * 
     * Checks if Username and password given is in database. 
     */
    public boolean loginSuccessful(String theUserName, String theEmail, String thePassword) { 
    	
    	for (int i = 0; i < myUserList.size(); i++) {
    		if ((myUserList.get(i).getUserName().equals(theUserName)) 
    				&& (myUserList.get(i).getPassword().equals(thePassword)) && (myUserList.get(i).getEmail().equals(theEmail))) {
    			return true;
    		} 
    	}
    	return false;
    }
    
    /**
     * @author Jasharn Thiara
     * @throws IOException 
     * 
     * Method will take in a email, username, password, and priviliges and create a new user to add into the userList arraylist.
     */ 
    public void addToList(String theName, String theEmail, String thePassword, boolean thePrivileges) throws IOException {

    	//First we check to make sure user is not in our list already (checked with loginSuccessful).
    	if (!loginSuccessful(theName, theEmail, thePassword)) {
    		FileWriter writer = new FileWriter(USERFILE, true);
    	
    		if (thePrivileges) {
    			writer.write("\n"+theName + " " + theEmail + " " + thePassword + " yes");
    			myUserList.add(new User(theName, theEmail, thePassword, true));
    		} else {
    			writer.write("\n"+theName + " " + theEmail + " " + thePassword + " no");
    			myUserList.add(new User(theName, theEmail, thePassword, true));
    		}
    		writer.close();
    	}
    }
    
    /**
     * @author Jasharn Thiara
     * @param theFile
     * @return Array List of Users
     * @throws FileNotFoundException 
     * 
     * Method reads from User file and creates an array of user objeccts.
     * User File values are seperated by spaces, so the scanner rader will read token by token and get
     * username, email, password and priveleges in this order until there are no more lines to read.
     */
    public ArrayList<User> readItemsFromFile(File theFile) throws FileNotFoundException {

    	//Instantiate an ArrayList of Users.
        final ArrayList<User> userList = new ArrayList<User>(); 
        
        Scanner reader = new Scanner(theFile);
        String myName = "";
        String myEmail = "";
        String myPassword = "";
        String myPrivileges = "";
        
        while (reader.hasNextLine()) {

        	if (reader.hasNext()) {
        		myName = reader.next();
        	}
        	
        	if(reader.hasNext()) {
        		myEmail = reader.next();
        	}
        	
        	if (reader.hasNext()) {
        		myPassword = reader.next();
        	}
        	if (reader.hasNext()) {
        		myPrivileges = reader.next();
        	}
        	
        	//Creates boolean field for users depending on a yes or no in the text file.
        	if (myPrivileges.toLowerCase().equals("yes")) {
        		userList.add(new User(myName, myEmail, myPassword, true));
        	} else {
        		userList.add(new User(myName, myEmail, myPassword, false));
        	}
        }
        reader.close();
        return userList;
    }
    
    /**
     * @author Jasharn Thiara
     * @param args
     * @throws FileNotFoundException
     * 
     * For the sake of testing, prints out the current list of Users, as well as their email, password, and whether or not they
     * have special privileges.
     */
    public static void main(String[] args) throws FileNotFoundException {
    	Registration r = new Registration();

    	for (int i = 0; i < r.myUserList.size(); i++) {
    		System.out.println("Username = " + r.myUserList.get(i).getUserName());
    		System.out.println("Email = "  + r.myUserList.get(i).getEmail());
    	    System.out.println("Password = " + r.myUserList.get(i).getPassword());
    	    System.out.println("priveleges = " + r.myUserList.get(i).getPriveleges());
    	}
    }
}
