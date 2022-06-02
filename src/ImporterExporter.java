package src;

/**
 * ImporterExporter class that can export or read
 * settings from a CSV file.
 *
 * @author  Trevor Tomlin
 * @version 1.0
 * @since   2022-05-18
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImporterExporter {

    /***
     * Exports row to file with given headers.
     * If file already exists, it appends the row to file.
     * Also exports to Desktop.
     * @param fileName
     * @param headers
     * @param row
     * @throws ExportException
     * @throws IOException
     */
    public static void exportSettings(String fileName, String[] headers, String[] row) throws src.ExportException, IOException {

        if (headers.length != row.length) {

            throw new src.ExportException("Length of headers does not match length of settings.");

        }

        File file = new File(fileName);

        String userHomeFolder = System.getProperty("user.home")+"\\Desktop";
        File textFile = new File(userHomeFolder, row[0]+".txt");

        String headerLine = arrayToCSV(headers);
        String rowLine = arrayToCSV(row);

        System.out.println(headerLine);
        System.out.println(rowLine);

        boolean fileExists = file.isFile();

        FileWriter writer = new FileWriter(file, fileExists);
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {
            if (!fileExists){
                writer.write(headerLine + "\n");

                writer.write(rowLine + "\n");

            }
            out.write(headerLine +"\n");
            out.write(rowLine + "\n");
            out.close();
            writer.close();
        }

        catch (Exception e) {

            e.printStackTrace();

        }


    }
    /***
     * Takes a string array and converts it to a single string
     * where each value is seperated by a comma.
     * Does not currently check for invalid CSV characters.
     * @param array
     * @return String
     */
    private static String arrayToCSV(String[] array) {

        String result = "";

        for (int i = 0; i < array.length; i++) {

            result += array[i];

            if (i < array.length - 1) result += ",";

        }

        return result;

    }

    /**
     * Imports profile into system and generates home dir for user
     * @param importButton
     * @param r
     * @param logInScreen
     */
    public static void importProfile(JButton importButton , src.Registration r, src.LogInScreen logInScreen){
        try {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            String userHomeFolder = System.getProperty("user.home") + "\\Desktop";
            JFileChooser fileChooser = new JFileChooser(userHomeFolder);
            fileChooser.setFileFilter(filter);
            fileChooser.showOpenDialog(importButton);

            File file = new File(fileChooser.getSelectedFile().toString());
            Scanner scanner = new Scanner(file);

            String temp = scanner.nextLine();
            temp = scanner.nextLine();

            String[] sArr = temp.split(",");

            Boolean tempBool = false;
            if (sArr[3].equals("true")) {
                tempBool = true;
            }

            r.addToList(sArr[0], sArr[1], sArr[2], tempBool);
            Boolean makeFile = true;
            File[] homeDirs = new File("FileHub").listFiles();
            for (File f : homeDirs) {
                if(f.getName().equals(sArr[0])) {
                    makeFile = false;
                    break;
                }
            }
            if(makeFile) {
                new File("FileHub/" + sArr[0]).mkdir();
            }
            logInScreen.changePanel(logInScreen.createButtonPanel());

        } catch (java.io.IOException | src.ExportException ex) {
            ex.printStackTrace();
        }

    }


    /**
     * Imports file and updates the current file list
     * @param fV
     */
    public static void importFile(src.FileView fV){
        FileDialog fd = new FileDialog((java.awt.Frame) null);
        fd.setVisible(true);

        String dir = fd.getDirectory();
        String file = fd.getFile();

        System.out.println(dir);
        System.out.println(file);

        if (file.isEmpty()) return;
        System.out.println(fV.getCurrentFilePath());
        try {
            Files.copy(Paths.get(dir, file), Paths.get(fV.getCurrentFilePath(), file));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"File already exists in location.");
            ex.printStackTrace();
        }
        File[] currentFileList = new File(fV.getCurrentFilePath()).listFiles();
        fV.setCurrentFileList(currentFileList);
    }

    /**
     * Export a file from the system to the desktop location
     * Allowed for use by JButton and MenuItem
     * @param button
     * @param menuButton
     * @param userName
     */
    public static void exportFile(JButton button,JMenuItem menuButton,String userName){
        JFileChooser jFileChooser = new JFileChooser("FileHub/" + userName);
        jFileChooser.setApproveButtonText("Export");
        jFileChooser.setVisible(true);
        if(button.getText().equals("")){
            jFileChooser.showOpenDialog(menuButton);
        }else {
            jFileChooser.showOpenDialog(button);
        }
        File file = jFileChooser.getSelectedFile();

        String userHomeFolder = System.getProperty("user.home") + "\\Desktop";

        try {
            Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(userHomeFolder, file.getName()));
        } catch (IOException ex ) {
            JOptionPane.showMessageDialog(null,"File already exists in location.");
            ex.printStackTrace();
        }
    }

}
