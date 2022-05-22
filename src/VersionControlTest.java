/**
 * TCSS 360
 */

package src;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class represents testing for TeamName project, class has functionality to test version control so far. 
 * 
 * @author Jasharn Thiara
 * @version 1.0
 * @since   2022-04-10
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
