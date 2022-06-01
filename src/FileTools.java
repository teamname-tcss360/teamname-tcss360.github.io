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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

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
    
    private String currentFilePath;
    private File[] currentFileList;
    
    File[] search(String searchInput, String theUser) {
        searchResults.clear();
        searchResultsCount = 0;
        if (searchInput.equals(null) || searchInput.equals("")) {
            //Nothing to search
        } else {
            currentFilePath = "FileHub/" + theUser;
            currentFileList = new File(currentFilePath).listFiles();
            searchHelper(searchInput);

            File[] temp = new File[searchResultsCount];
            for (int i = 0; i < searchResultsCount; i++) {
                temp[i] = searchResults.get(i);
            }
            currentFileList = temp;
        }
        return currentFileList;
    }

    /**
     * Search the folders and files by keyword
     * @param input keyword to search for
     */
    ArrayList<File> searchResults=new ArrayList<>(20);
    int searchResultsCount = 0;
    void searchHelper(String input){
        //Recurse down folders and files to find occurrences
        for (File file : currentFileList) {
            if(file.isFile() && file.getName().toLowerCase(Locale.ROOT).contains(input)){
                searchResults.add(file);
                searchResultsCount++;
            }else if(!file.isFile()){
                currentFileList = file.listFiles();
                searchHelper(input);
            }
        }
    }
    
    File[] deleteFile(String fileToDelete){
        if(fileToDelete.contains("file")) {
            fileToDelete = fileToDelete.replaceAll("file", "");
            System.out.println(currentFilePath+"\\"+fileToDelete);
            File file = new File(currentFilePath+"\\"+fileToDelete);
            file.delete();
            
            currentFileList = new File(currentFilePath).listFiles();
        }else {
            fileToDelete = fileToDelete.replaceAll("folder", "");
            File file = new File(currentFilePath+"\\"+fileToDelete);
            currentFileList = file.listFiles();
            for (File f : currentFileList) {
                if (f.isFile()){
                    f.delete();
                }else {
                    deleteFile(f.getName());
                }
            }
            file.delete();
        }
        currentFileList = new File(currentFilePath).listFiles();
        return sortFilesFromFolders(currentFileList);
    }
}
