/**
 * Class represents version control 
 * 
 * @author TeamName
 *
 */
public class VersionControl {
	
	/**
	 * String to represent version number
	 */
	private static String myVersion;
	
	public VersionControl(String theVersion) {
		myVersion = theVersion;
	}
	
    public String getVersion(){
        return myVersion;

    }

}
