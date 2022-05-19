/**
 * TCSS 360
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

/**
 * Class represents testing for TeamName project, class has functionality to test version control so far. 
 * 
 * @author TeamName
 * @version Spring 2022
 * 
 */

class VersionControlTest {

	
	private String myVersion = "0.0.1";

	
    /**
     * Test method for {@VersionControl#getVersion()}.
     */
	@org.junit.jupiter.api.Test

	void testGetVersion() {
		assertEquals(myVersion, VersionControl.getVersion(), "getVersion() method fail");
	}

}
