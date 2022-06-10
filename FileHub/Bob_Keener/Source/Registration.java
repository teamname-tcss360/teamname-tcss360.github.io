package FileHub.Bob_Keener.Source;

/**
 * TCSS 360
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class represents version control for TeamName Project
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
     * Constructs a sigin/registration system.
     * @throws FileNotFoundException 
     * 
     * 
     */
    public Registration() throws FileNotFoundException {
        myUserList = readItemsFromFile(USERFILE);
    }

    /**
     * getter for myUserList.
     * 
     * @return myUserList
     */
    public ArrayList<User> getMyUserList() {
        return myUserList;
    }
    
    /**
     * Checks if Username and password given is in database 
     * 
     * @return boolean login result
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
     * Method will take in a email, username, password, and priviliges and create a new user to add into the userList arraylist
     * @throws IOException 
     */ 
    public void addToList(String theName, String theEmail, String thePassword, boolean thePrivileges) throws IOException {

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
     * Method reads from User file and creates an array of user objeccts.
     * 
     * @param theFile
     * @return
     * @throws FileNotFoundException 
     */
    public ArrayList<User> readItemsFromFile(File theFile) throws FileNotFoundException {

    	//userList
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
        	
        	if (myPrivileges.toLowerCase().equals("yes")) {
        		userList.add(new User(myName, myEmail, myPassword, true));
        	} else {
        		userList.add(new User(myName, myEmail, myPassword, false));
        	}
        }
        	
        reader.close();
        return userList;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	Registration r = new Registration();
    	
    	System.out.println(r.loginSuccessful("Bob_Keener", "Bob@bob.com", "**********"));
    	
    	//for sake of testing 
    	for (int i = 0; i < r.myUserList.size(); i++) {
    		System.out.println("Username = " + r.myUserList.get(i).getUserName());
    		System.out.println("Email = "  + r.myUserList.get(i).getEmail());
    	    System.out.println("Password = " + r.myUserList.get(i).getPassword());
    	    System.out.println("priveleges = " + r.myUserList.get(i).getPriveleges());
    	}
    }
}
