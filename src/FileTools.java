/**
 * TCSS 360
 * 
 * Class will represent File objects in the TeamName project.
 * The class will have a constructor that will read from the list of files and
 * create an object for each file taking in File Path and File name as parameters.
 */
package src;

import java.io.File;
import java.sql.Date;
import java.util.Arrays;

/**
 * Filez utility class 
 * Program named Filez instead of file to avoid overriding original file class.
 *
 * @author  Jasharn Thiara
 * @version 1.0
 * @since   2022-05-24
 */

public class FileTools {
	
	/**
     * Helper method to organize methods and sort File List.
     */
    File[] sortFilesFromFolders(File[] theFileList) {
        Arrays.sort(theFileList, (a, b) -> {
            // do your comparison here returning -1 if a is before b, 0 if same, 1 if a is after b
            if (a.isFile() && !b.isFile()) {
                return 1;
            } else if (a.isFile() && b.isFile()) {
                return a.compareTo(b);
            } else if (!a.isFile() && !b.isFile()) {
                return a.compareTo(b);
            } else {
                return -1;
            }
        });
        return theFileList;
    }
    
    /**
     * Helper method to organize methods and reverse sort File List.
     **/
    File[] reverseSortFilesFromFolders(File[] theFileList) {
        Arrays.sort(theFileList, (a, b) -> {
            // do your comparison here returning -1 if a is before b, 0 if same, 1 if a is after b
            if (a.isFile() && !b.isFile()) {
                return 1;
            } else if (a.isFile() && b.isFile()) {
                return b.compareTo(a);
            } else if (!a.isFile() && !b.isFile()) {
                return b.compareTo(a);
            } else {
                return -1;
            }
        });
        return theFileList;
    }
	
	
}
