/**
 * TCSS 360
 */

package testing;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import src.Registration;
import src.User;

/**
 * Class represents testing for TeamName project, class has functionality to test version control.
 * 
 * @author Jasharn Thiara
 * @version 1.0
 * @since   2022-05-28
 */

class RegistrationTest {
	
	/**
     * User Storage File.
     */
    public static final File tempUSERFILE = new File("UserFile.txt");
	
	public Registration mySubClass() throws FileNotFoundException {
		
		return new Registration();
	} 
	
	
	@Test
	void testLogInSuccessful() throws FileNotFoundException {
		Registration r = mySubClass();
		//ensures that login returns true with correct username/email/password.
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("Bob_Keener", "Bob@bob.com", "**********"), true);
	}
	
	@Test
	void testLogInSuccessful2() throws FileNotFoundException {
		Registration r = mySubClass();
		//ensures that login returns false with correct username/email but incorrect password.
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("Bob_Keener", "Bob@bob.com", "zoop"), false);
	}
	
	@Test
	void testLogInSuccessful3() throws FileNotFoundException {
		Registration r = mySubClass();
		//ensures that login returns false with correct username/password but incorrect email.
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("Bob_Keener", "123@bob.com", "**********"), false);
	}
	
	@Test
	void testLogInSuccessful4() throws FileNotFoundException {
		Registration r = mySubClass();
		//ensures that login returns false with correct email/password but incorrect username.
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("zerp", "Bob@bob.com", "**********"), false);
	}
	
	@Test
	void testLogInSuccessful5() throws FileNotFoundException {
		Registration r = mySubClass();
		//ensures that login returns false with incorrect username/email/password.
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("ner", "nerpy@bob.com", "sdlfsldfds"), false);
	}
	
	void testAddToList() throws IOException {
		Registration r = mySubClass();
		r.addToList("joeshmo", "joeShmo@shmoey", "itsjoe", false);
		
		//if user has been added then loginsuccessfull should return true.
		assertEquals("Should add to list of users", r.loginSuccessful("joeshmo", "joeShmo@shmoey", "itsjoe"), true);
		
	}
}

