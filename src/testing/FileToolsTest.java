/**
 * TCSS 360
 */

package testing;

import static org.junit.Assert.assertEquals;
import java.io.File;
import org.junit.jupiter.api.Test;

import src.FileTools;
import src.FileView;

/**
 * Class tests TeamName project, class has functionality to test the FileTools class.
 * 
 * @author Jasharn Thiara
 * @version 1.0
 * @since   2022-06-04
 */

class FileToolsTest {

	private FileTools ft = new FileTools(null,null);
	
	/**
	 * @author Jasharn Thiara
	 * 
	 * Ensures that the sortFilesFromFolders method returns a sorted list by creating an array of folders in random order
	 * and making a comparison after calling the sortFilesFromFolders method in File Tools.
	 */
	@Test
	void testSortFiles() {
		File[] temp = {new File("empty file"), new File("Bobs files"), new File("Aarons Files"), new File("Zarty file")};
		temp = ft.sortFilesFromFolders(temp);
		
		//File array that represents the desired order
		File[] tempCompare = {new File("Aarons Files"), new File("Bobs files"),new File("empty file"), new File("Zarty file")};
		int result = 0; 
				for (int i = 0; i < 4; i++) {
					if (!temp[i].getName().equals(tempCompare[i].getName())) {
						result = 1; 
						break;
					}
				}
				
		//logic here is that int result will be 0 if the files were sorted correctly. 
		assertEquals("Files were not sorted correctly", result, 0);
	}
	
	/**
	 * @author Jasharn Thiara
	 * 
	 * Ensures that the reverseSortFilesFromFolders method returns a reverse sorted list by creating an array of folders in random order
	 * and making a comparison after calling the reverseSortFilesFromFolders method in File Tools.
	 */
	@Test
	void testReverseSortFiles() {
		File[] temp = {new File("empty file"), new File("cupids files"), new File("aaron's Files") ,new File("aavon's Files"), new File("Zarty file")};
		temp = ft.reverseSortFilesFromFolders(temp);
		
		//File array that represents the desired order
		File[] tempCompare = {new File("Zarty file"), new File("empty file"), new File("cupids files"), new File("aavon's Files"), new File("aaron's Files")};
		int result = 0; 
				for (int i = 0; i < 4; i++) {
					if (!temp[i].getName().equals(tempCompare[i].getName())) {
						result = 1; 
						break;
					}
				}
				
		//logic here is that int result will be 0 if the files were sorted correctly. 
		assertEquals("Files were not sorted correctly", result, 0);
	}
	
	/**
	 * @author Jasharn Thiara
	 *
	 * Tests search method; case where user does not have any files related to the search
	 * The method search in the fileTools class should return an empty file array.
	 */
	@Test
	void testSearch() {
		File[] results = ft.search("bed", "josh");
		assertEquals("Search for User with no files failed", results.length, 0);
	}
	
	/**
	 * @author Jasharn Thiara
	 *
	 * Tests search method; case where user does have files related to the search. 
	 * Bob has 2 files that are related to the search din, so the search method
	 * in the fileTools class should return an array of size 2. This array will contain the files found.
	 */
	@Test
	void testSearch2() {
		File[] results = ft.search("din", "Bob_Keener");
		assertEquals("Search for User with files related to the search failed", results.length, 2);
	}
	
	
}
