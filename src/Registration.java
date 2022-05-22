/**
 * TCSS 360
 */

package src;

import java.io.File;
import java.io.FileNotFoundException;
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
    final ArrayList<User> myUserList;

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
     * Method reads from User file and creates an array of user objeccts.
     * 
     * @param theFile
     * @return
     * @throws FileNotFoundException 
     */
    public ArrayList<User> readItemsFromFile(File theFile) throws FileNotFoundException {

    	//userList currently has capacity of 100.
        final ArrayList<User> userList = new ArrayList<User>(); 
        
        Scanner reader = new Scanner(theFile);
        String myName = "";
        String myPassword = "";
        String myPrivileges = "";
        
        while (reader.hasNextLine()) {

        	if (reader.hasNext()) {
        		myName = reader.next();
        	}
        	
        	if (reader.hasNext()) {
        		myPassword = reader.next();
        	}
        	if (reader.hasNext()) {
        		myPrivileges = reader.next();
        	}
        	
        	if (myPrivileges.toLowerCase().equals("yes")) {
        		userList.add(new User(myName, myPassword, true));
        	} else {
        		userList.add(new User(myName, myPassword, false));
        	}
        }
        	
        reader.close();
        return userList;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	Registration r = new Registration();
    	
    	for (int i = 0; i < r.myUserList.size(); i++) {
    		System.out.println(r.myUserList.get(i).getUserName() + " " + r.myUserList.get(i).getPassword() + " " + r.myUserList.get(i).getPriveleges());
    	}
    }
}
