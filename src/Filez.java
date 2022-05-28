/**
 * TCSS 360
 * 
 * Class will represent File objects in the TeamName project.
 * The class will have a constructor that will read from the list of files and
 * create an object for each file taking in File Path and File name as parameters.
 */
package src;

import java.sql.Date;

/**
 * Filez utility class 
 * Program named Filez instead of file to avoid overriding original file class.
 *
 * @author  Jasharn Thiara
 * @version 1.0
 * @since   2022-05-24
 */

public class Filez {
	
	/**
	 * String field to represent file name.
	 */
	String fileName;
	
	/**
	 * String field to represent file path.
	 */
	String filePath;
	
	/**
	 * Date field if needed
	 */
	Date fileDate;
	
	public Filez(String theFileName, String theFilePath) {
		fileName = theFileName;
		filePath = theFilePath;
	}
	
	
	
	
}
