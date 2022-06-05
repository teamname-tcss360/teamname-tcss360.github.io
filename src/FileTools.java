/**
 * TCSS 360
 * 
 * Class will represent File objects in the TeamName project.
 * The class will have a constructor that will read from the list of files and
 * create an object for each file taking in File Path and File name as parameters.
 */
package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * This class will create the JToolBar and contained action listeners for toolbar buttons
 *
 * @author  Jasharn Thiara
 * @author  Patrick Tibbals
 * @version 1.0
 * @since   2022-05-24
 */

public class FileTools {
	
    /**
     *  Current file path for storage during manipulation
     */
    private String currentFilePath;
    /**
     * Current file list for storage during manipulation
     */
    private File[] currentFileList;
    /**
     * Storage for search results
     */
    private ArrayList<File> searchResults=new ArrayList<>(20);
    /**
     *  Counts the search results to send correct length File[]
     */
    private int searchResultsCount = 0;
    /**
     *  Storage for the instance of FileView class
     */
    private src.FileView fileView;

    /**
     * Constructor for FileTools class
     * @param fV current fileView instance
     */
    public FileTools(src.FileView fV){
        fileView = fV;
    }

    /**
     * Creates the JToolBar
     * @return jToolBar
     */
    public JToolBar toolBar(){
        JToolBar jToolBar = new JToolBar();

        //Import button
        JButton importFile = new JButton("Import File");
        importFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                src.ImporterExporter.importFile(fileView);
                fileView.setCurrentFileList(sortFilesFromFolders(fileView.getCurrentFileList()));
                fileView.view();

            }
        });

        //Export button
        JButton exportFile = new JButton("Export File");
        exportFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                src.ImporterExporter.exportFile(exportFile,null,fileView.getUserName());
            }
        });

        //Search text field
        JTextField jTextField = new JTextField(25);
        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileView.setCurrentFileList(search(jTextField.getText().toLowerCase(Locale.ROOT), fileView.getUserName()));
                jTextField.setText("");
                fileView.view();
            }
        });

        //Search button
        JLabel searchLabel = new JLabel("Search");

        JButton searchButton = new JButton();

        URL url = ClassLoader.getSystemClassLoader().getResource("magnifying-glass.png");
        ImageIcon icon = new ImageIcon(url);
        Image imageNew = icon.getImage();
        Image newImgNew = imageNew.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImgNew);


        searchLabel.setIcon(icon);
        searchButton.add(searchLabel);
        searchButton.setBorder(null);
        searchButton.setOpaque(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileView.setCurrentFileList(search(jTextField.getText().toLowerCase(Locale.ROOT), fileView.getUserName()));
                jTextField.setText("");
                fileView.view();
            }
        });

        //Sort buttons
        JPanel sortChoices = new JPanel();
        JLabel sortLabel = new JLabel("Sort By: ");
        JRadioButton aToZRadioButton = new JRadioButton("A-Z");
        aToZRadioButton.setSelected(true);
        JRadioButton zToARadioButton = new JRadioButton("Z-A");

        // When the A-Z button is clicked the array of Files will be sorted and then the form will be updated
        // and create the buttons in the new order.
        aToZRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (zToARadioButton.isSelected()) {
                    zToARadioButton.setSelected(false);
                }
                fileView.setCurrentFileList(sortFilesFromFolders(fileView.getCurrentFileList()));
                fileView.view();
            }
        });

        // When the Z-A button is clicked the array of Files will be sorted and then reversed
        // the form will then be shown in the desired order.
        zToARadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (aToZRadioButton.isSelected()) {
                    aToZRadioButton.setSelected(false);
                }
                fileView.setCurrentFileList(reverseSortFilesFromFolders(fileView.getCurrentFileList()));
                fileView.view();
            }
        });

        sortChoices.add(sortLabel);
        sortChoices.add(aToZRadioButton);
        sortChoices.add(zToARadioButton);
        jToolBar.add(importFile);
        jToolBar.add(exportFile);
        jToolBar.add(searchButton);
        jToolBar.add(jTextField);
        jToolBar.add(sortChoices);
        return jToolBar;

    }


	/**
     * Helper method to organize methods and sort File List.
     */
<<<<<<< HEAD
    public File[] sortFilesFromFolders(File[] theFileList) {
=======
    File[] sortFilesFromFolders(File[] theFileList) {

>>>>>>> e5686c069c2c938b417a9bd90edb8f73f665f992
        Arrays.sort(theFileList, (a, b) -> {
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
    public File[] reverseSortFilesFromFolders(File[] theFileList) {
        Arrays.sort(theFileList, (a, b) -> {
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

    /**
     * Uses the userName and search keyword to send the correct directory location
     * to the recursive search method.
     * @param searchInput search keyword
     * @param theUser users directory to search in
     * @return the file list of search results
     */
    public File[] search(String searchInput, String theUser) {
        searchResults.clear();
        searchResultsCount = 0;
        if (searchInput.equals(null) || searchInput.equals("")) {
            //Nothing to search
        } else {
            currentFilePath = System.getProperty("user.home") + "\\Desktop\\TEAMNAME-File Explorer\\" + "FileViewer\\" + "FileHub\\" + theUser;
            currentFileList = new File(currentFilePath).listFiles();
            //Call to recursive method
            searchHelper(searchInput);
            //Generate new File[] from search result arraylist
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

    /**
     * Used to delete the desired file or directory
     * @param fileToDelete name of file to delete
     * @param filePath current file path
     * @return updated File[]
     */

    File[] deleteFile(String fileToDelete, String filePath){
    	currentFilePath = filePath;
    	//If File delete file
        if(fileToDelete.contains("file")) {
            fileToDelete = fileToDelete.replaceAll("file", "");
            System.out.println(currentFilePath+"\\"+fileToDelete);
            File file = new File(currentFilePath+"\\"+fileToDelete);
            file.delete();
            currentFileList = new File(currentFilePath).listFiles();
        }else {
            //File is a directory, loop through items and recurse
            deleteFolder(fileToDelete, currentFilePath);

        }
        currentFileList = new File(currentFilePath).listFiles();
        return sortFilesFromFolders(currentFileList);
    }
    void deleteFolder(String fileToDelete, String filePath){
        //Recurse down folders and files to find occurrences
        String recursiveFilePath;
        if(fileToDelete.contains("folder")){
            recursiveFilePath = filePath+"\\"+fileToDelete.replaceAll("folder","");
        }else{
            recursiveFilePath = filePath+"\\"+fileToDelete.replaceAll("file","");

        }
        File file = new File(recursiveFilePath);
        currentFileList = file.listFiles();
        if(currentFileList != null && currentFileList.length != 0) {
            for (File f : currentFileList) {
                if (f.isFile()) {
                    f.delete();
                } else {
                    deleteFolder(f.getName(), recursiveFilePath);
                }
            }
            file.delete();
        } else {
            file.delete();
        }
    }
}
