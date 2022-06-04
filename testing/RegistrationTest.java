/**
 * TCSS 360
 */

package testing;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import src.Registration;

/**
 * Class represents testing for TeamName project, class has functionality to test the Registration class.
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
    
    /**
     * Registration field. 
     */
    Registration r;
	
    /**
     * @author Jasharn Thiara
     * @return
     * @throws FileNotFoundException
     * 
     * creates instance of registration to apply test on.
     */
	public Registration mySubClass() throws FileNotFoundException {
		
		return new Registration();
	} 
	
	/**
	 * @author Jasharn Thiara
	 * @throws FileNotFoundException
	 * 
	 * Tests that loginSuccessful returns true with correct username/email/password.
	 */
	@Test
	void testLogInSuccessful() throws FileNotFoundException {
		r = mySubClass();
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("Bob_Keener", "Bob@bob.com", "**********"), true);
	}
	
	/**
	 * @author Jasharn Thiara
	 * @throws FileNotFoundException
	 * 
	 * Tests that loginSuccessful returns false with correct username/email but incorrect password.
	 */
	@Test
	void testLogInSuccessful2() throws FileNotFoundException {
		r = mySubClass();
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("Bob_Keener", "Bob@bob.com", "zoop"), false);
	}
	
	/**
	 * @author Jasharn Thiara
	 * @throws FileNotFoundException
	 * 
	 * Tests that loginSuccessful returns false with correct username/password but incorrect email.
	 */
	@Test
	void testLogInSuccessful3() throws FileNotFoundException {
		r = mySubClass();
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("Bob_Keener", "123@bob.com", "**********"), false);
	}
	
	/**
	 * @author Jasharn Thiara
	 * @throws FileNotFoundException
	 * 
	 * Tests that loginSuccessful returns false with correct email/password but incorrect username.
	 */
	@Test
	void testLogInSuccessful4() throws FileNotFoundException {
		r = mySubClass();
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("zerp", "Bob@bob.com", "**********"), false);
	}
	
	/**
	 * @author Jasharn Thiara
	 * @throws FileNotFoundException
	 * 
	 * Tests that loginSuccessful returns false with incorrect username/email/password.
	 */
	@Test
	void testLogInSuccessful5() throws FileNotFoundException {
		r = mySubClass();
		assertEquals("Login Should be Successful but is not", r.loginSuccessful("ner", "nerpy@bob.com", "sdlfsldfds"), false);
	}
	
	/**
	 * @author Jasharn Thiara
	 * @throws IOException
	 * 
	 * Tests that addToList method in registration successfully adds a new user to our arraylist.
	 * If the user has been added, then the method login successful using the new user information should return true.
	 */
	void testAddToList() throws IOException {
		Registration r = mySubClass();
		r.addToList("joeshmo", "joeShmo@shmoey", "itsjoe", false);
		assertEquals("Should add to list of users", r.loginSuccessful("joeshmo", "joeShmo@shmoey", "itsjoe"), true);
		
	}
}

