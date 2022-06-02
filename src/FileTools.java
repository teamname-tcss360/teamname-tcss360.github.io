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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Filez utility class 
 * Program named Filez instead of file to avoid overriding original file class.
 *
 * @author  Jasharn Thiara
 * @author  Patrick Tibbals
 * @version 1.0
 * @since   2022-05-24
 */

public class FileTools {
    private String currentFilePath;
    private File[] currentFileList;
    private ArrayList<File> searchResults=new ArrayList<>(20);
    private int searchResultsCount = 0;
    private src.FileView fileView;

    public FileTools(src.FileView fV){
        fileView = fV;
    }

    public JToolBar toolBar(){
        JToolBar jToolBar = new JToolBar();
        JButton importFile = new JButton("Import File");

        importFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                src.ImporterExporter.importFile(fileView);
                fileView.setCurrentFileList(sortFilesFromFolders(fileView.getCurrentFileList()));
                fileView.view();

            }
        });

        JButton exportFile = new JButton("Export File");

        exportFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                src.ImporterExporter.exportFile(exportFile,fileView.getUserName());
            }
        });

        JLabel searchLabel = new JLabel("Search");
        JButton searchButton = new JButton();
        JTextField jTextField = new JTextField(25);

        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileView.setCurrentFileList(search(jTextField.getText().toLowerCase(Locale.ROOT), fileView.getUserName()));
                jTextField.setText("");
                fileView.view();
            }
        });

        ImageIcon icon = new ImageIcon("src\\resources\\magnifying-glass.png");
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
        // the form will then be reshown in the desired order.
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
    
    File[] deleteFile(String fileToDelete, String filePath){
    	currentFilePath = filePath;
    	
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
                    deleteFile(f.getName(), currentFilePath);
                }
            }
            file.delete();
        }
        currentFileList = new File(currentFilePath).listFiles();
        return sortFilesFromFolders(currentFileList);
    }
}
