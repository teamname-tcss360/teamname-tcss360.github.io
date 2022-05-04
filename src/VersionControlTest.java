/**
 * TCSS 360
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Class represents testing for TeamName project, class has functionality to test version control so far. 
 * 
 * @author TeamName
 * @version Spring 2022
 * 
 */

class VersionControlTest {
	
	private VersionControl myVersionControl;
	
	private String myVersion = "0.0.1";

	
    /**
     * Test method for {@VersionControl#getVersion()}.
     */
	@Test
	void testGetVersion() {
		myVersionControl = new VersionControl("0.0.1");
		assertEquals(myVersion, myVersionControl.getVersion(), "getVersion() method fail");
	}

}
