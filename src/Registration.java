/**
 * TCSS 360
 * 
 * Class will have functionality to read from a txt and create an array list of users. 
 * Will also provide login in checks to see if the fields match up with any users in the database.
 * Functionality to add new users to our list. 
 */

package src;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
    public static File USERFILE;





    /**
     * The registered user list for signin.
     */
    ArrayList<User> myUserList;

    /**
     * Constructs a sigin/registration system.
     * @throws FileNotFoundException 
     * 
     */
    public Registration() throws FileNotFoundException, URISyntaxException {

//		InputStream in = uri;
		//USERFILE = new File(getClass().getResource("/Users/UserFile.txt").toExternalForm());
		USERFILE = new File(System.getProperty("user.home") + "\\Desktop\\TEAMNAME-File Explorer\\" + "FileViewer\\" + "Users" + "\\UserFile.txt");

/*
		InputStream is = getClass().getResourceAsStream("/Users/UserFile.txt");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		try {
			System.out.println(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//String files = dir.list();
*/
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
     * Method reads from User file and creates an array of user objects.
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
        	};
        	
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
    /*
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
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

     */
}
