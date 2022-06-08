package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;


/**
 * Menu GUI creates the JMenuBar for the fileView Class
 */
public class MenuGUI {
    /**
     * Instance of fileView for use in MenuBar actions
     */
    private src.FileView fileView;

    private String folder;

    /**
     * Constructor for MenuGUI that also starts the buildMenuBar method
     * @param fv current instance of fileView class
     */
    public MenuGUI(src.FileView fv,String folderLocation) {
        fileView = fv;
        folder = folderLocation;
        buildMenuBar();
    }

    /**
     * Builds the MenuBar for fileView
     * @return JMenuBar
     */
    JMenuBar buildMenuBar(){
        // Menu's
        JMenuBar menuBar = new JMenuBar();
        URL url = ClassLoader.getSystemClassLoader().getResource("new.png");
        ImageIcon iconNew = new ImageIcon(url);
        Image imageNew = iconNew.getImage();
        Image newImgNew = imageNew.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconNew = new ImageIcon(newImgNew);

        URL url2 = ClassLoader.getSystemClassLoader().getResource("exit.png");
        ImageIcon iconExit = new ImageIcon(url2);
        Image imageExit = iconExit.getImage();
        Image newImgExit = imageExit.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconExit = new ImageIcon(newImgExit);

        URL url3 = ClassLoader.getSystemClassLoader().getResource("about.png");
        ImageIcon iconAbout = new ImageIcon(url3);
        Image imageAbout = iconAbout.getImage();
        Image newImgAbout = imageAbout.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconAbout = new ImageIcon(newImgAbout);
        JMenu fileMenu = new JMenu("File");


        JMenuItem importMenuItem = new JMenuItem("Import file");
        importMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                src.ImporterExporter.importFile(fileView);
                fileView.setCurrentFileList(fileView.getFileTools().sortFilesFromFolders(fileView.getCurrentFileList()));
                fileView.view();
            }
        });
        JMenuItem exportMenuItem = new JMenuItem("Export file");

        exportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                src.ImporterExporter.exportFile(new JButton(""),exportMenuItem, fileView.getUserName(),folder);
            }
        });

        JMenuItem newMenu = new JMenu("New");
      //  JMenuItem openMenuItem = new JMenuItem("Open", iconOpen);
      //  JMenuItem saveMenuItem = new JMenuItem("Save", iconSave);
        JMenuItem exitMenuItem = new JMenuItem("Sign Out", iconExit);
        exitMenuItem.setToolTipText("Sign Out");

        JMenuItem newRoomMenuItem = new JMenuItem("New Folder");
        newRoomMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomName = JOptionPane.showInputDialog("Folder Name");
                File file = new File(fileView.getCurrentFilePath()+"\\"+roomName);
                file.mkdir();
                File[] currentFileList = new File(fileView.getCurrentFilePath()).listFiles();
                fileView.setCurrentFileList(fileView.getFileTools().sortFilesFromFolders(currentFileList));
                fileView.view();
            }
        });


        JMenuItem newItemMenuItem = new JMenuItem("New Item");
        newItemMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = JOptionPane.showInputDialog("Item Name");
                File file = new File(fileView.getCurrentFilePath()+"\\"+itemName);
                file.mkdir();
                File notes = new File(file.getPath()+"\\"+itemName+" Notes");
                File manual = new File(file.getPath()+"\\"+itemName+" Manual");
                File warranty = new File(file.getPath()+"\\"+itemName+" Warranty");
                notes.mkdir();
                manual.mkdir();
                warranty.mkdir();

                File[] currentFileList = file.listFiles();
                fileView.setCurrentFilePath(file.getPath());
                fileView.setCurrentFileList(fileView.getFileTools().sortFilesFromFolders(currentFileList));
                fileView.view();

            }
        });

        newMenu.setIcon(iconNew);
        newMenu.add(newRoomMenuItem);
        newMenu.add(newItemMenuItem);


        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileView.getFrame().dispose();
                MainView m = new MainView();
                m.guiBuilder(folder);
            }
        });

        JMenu editMenu = new JMenu("Edit");

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About", iconAbout);

        aboutMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new src.AboutView();

            }
        });

        fileMenu.add(newMenu);
       // fileMenu.add(openMenuItem);
       // fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(importMenuItem);
        fileMenu.add(exportMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // editMenu.add();
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        return menuBar;
    }



}

