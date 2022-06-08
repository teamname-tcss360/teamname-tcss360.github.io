/**
 * TCSS 360
 * 
 * Class represents a view panel for the GUI. Class will occupy jframe with a two main panels.
 * A panel for the drop down menu, and a panel  with the folders for the user to interact with.
 */

package src;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

/**
 * File view panel for the GUI
 * Activated by signing onto a profile
 *
 * @author  Patrick Tibbals
 * @version 1.0
 * @since   2022-05-18
 */

public class FileView {

    /**
     * JFrame field.
     */
    private JFrame frame;

    /**
     * JPanel field (right side with folders).
     */
    private JPanel right = new JPanel();

    /**
     * JPanel field (left side with folder names).
     */
    private JPanel left = new JPanel();

    /**
     * File array of the current file list
     */
    private File[] currentFileList;

    /**
     * Username field
     */
    private String userName;

    /**
     * Current file path
     */
    private String currentFilePath;

    /**
     * File path for file to be deleted
     */
    private String fileToDelete;

    /**
     * Image field for folder and file options
     */
    private Image imageNew;

    /**
     * Instance field for fileTools Class
     * Passing the instance of fileView
     */
    private FileTools fileTools;


    /**
     * Popup menu field
     */
    private final JPopupMenu popupmenu = new JPopupMenu("Edit");

    private String folder;


    /**
     * Getter for userName
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter for frame
     *
     * @return frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Getter for current file list
     *
     * @return currentFileList
     */
    public File[] getCurrentFileList() {
        return currentFileList;
    }

    /**
     * Getter for current file path
     *
     * @return currentFilePath
     */
    public String getCurrentFilePath() {
        return currentFilePath;
    }

    /**
     * Getter for fileTools instance
     *
     * @return fileTools
     */
    public FileTools getFileTools() {
        return fileTools;
    }

    /**
     * Setter for currentFilePath
     *
     * @param filePath
     */
    public void setCurrentFilePath(String filePath) {
        currentFilePath = filePath;
    }

    /**
     * Setter for currentFileList
     *
     * @param fileList
     */
    public void setCurrentFileList(File[] fileList) {
        currentFileList = fileList;
    }

    /**
     * FileView constructor
     *
     * @param f    frame pass from LogInScreen
     * @param user userName from login
     */
    public FileView(JFrame f, String user,String folderLocation) throws IOException {
        userName = user;
        frame = f;
        folder = folderLocation;
        fileTools = new FileTools(this,folder);

        frame.setBackground(Color.gray);
        frame.setLayout(new BorderLayout());

        //Establish current file path and current file list
        currentFilePath = folder + "\\FileHub\\"+userName;
        File userHome = new File(currentFilePath);
        if (isDirectoryEmpty(userHome)) {
            currentFileList = userHome.listFiles();
        }else {
            currentFileList = fileTools.sortFilesFromFolders(userHome.listFiles());
        }
        //Menu to allow right click delete
        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFileList = fileTools.deleteFile(fileToDelete, currentFilePath);
                view();
            }
        });

        popupmenu.add(delete);
        frame.add(popupmenu);

        //Generate the Menubar and toolbar only once
        menuBar();
        toolBar();
        view();
    }
    public boolean isDirectoryEmpty(File directory) throws IOException {
        DirectoryStream<Path> stream = Files.newDirectoryStream(directory.toPath());
        return !stream.iterator().hasNext();
    }
    /**
     * Method is called by constructor and creates desired GUI functionality.
     * Also is used to update the view after actions.
     */
    void view() {
        left.removeAll();
        right.removeAll();
        fileList();
        visualInterpretation();
        frame.validate();

    }

    /**
     * Method creates file list along the left side of the JFrame.
     */
    void fileList() {
        String[] pathArr;
        if(currentFilePath.contains("/")) {
            currentFilePath.replaceAll("/","\\\\");
        }
        pathArr = currentFilePath.split("\\\\");

        left.setLayout(new GridLayout(0, 1));

        //Create Home button which is parent folder
        JButton homeButton = new JButton(pathArr[pathArr.length-1]);
        homeButton.setContentAreaFilled(false);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(currentFilePath);
                int index;
                if(currentFilePath.contains("/")) {
                    currentFilePath.replaceAll("/","\\\\");
                }
                index = currentFilePath.lastIndexOf("\\");

                System.out.println(currentFilePath.substring(index+1));
                if(currentFilePath.substring(index+1).equals(userName)){
                    currentFileList = (new File(currentFilePath).listFiles());
                    currentFileList = fileTools.sortFilesFromFolders(currentFileList);
                    view();
                }else {
                    currentFilePath = currentFilePath.substring(0, index);
                    System.out.println(currentFilePath);
                    currentFileList = new File(currentFilePath).listFiles();
                    currentFileList = fileTools.sortFilesFromFolders(currentFileList);
                    view();
                }
            }
        });
        left.add(homeButton);


        //Generate the Folders within the home directory and create action listeners
        if (!currentFileList.equals(null)) {
            for (int i = 0; i < currentFileList.length; i++) {
                if (currentFileList[i].isFile()) {
                    //Do nothing not a folder
                } else {
                    JButton button = new JButton(currentFileList[i].getName());

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            currentFilePath = currentFilePath + "\\" + button.getText();
                            currentFileList = new File(currentFilePath).listFiles();
                            currentFileList = fileTools.sortFilesFromFolders(currentFileList);
                            view();

                        }
                    });
                    left.add(button);
                }
            }
        }
        frame.add(left, BorderLayout.WEST);
    }


    /**
     * Method populates right side panel with folders (made of labels and images) to add to our JFrame.
     */
    void visualInterpretation() {
        URL url = ClassLoader.getSystemClassLoader().getResource("folder.png");
        URL url2 = ClassLoader.getSystemClassLoader().getResource("file.png");
        ImageIcon icon = new ImageIcon(url);
        ImageIcon icon2 = new ImageIcon(url2);
        right.setLayout(new GridLayout(4, 4));

        for (int i = 0; i < 16; i++) {
            JPanel panel = new JPanel();
            if (i < currentFileList.length) {

                panel.setLayout(new BorderLayout());
                JLabel jLabel = new JLabel(currentFileList[i].getName(), JLabel.CENTER);
                jLabel.setOpaque(false);

                String fileOrFolder;
                if (currentFileList[i].isFile()) {
                    //Add to 2nd list

                    imageNew = icon2.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    fileOrFolder = "file";
                } else {
                    imageNew = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    fileOrFolder = "folder";
                }
                //Create label and add action Listener to "open folder" when clicked
                JLabel imageLabel = new JLabel(new ImageIcon(imageNew), JLabel.CENTER);

                // Name for clicked listener
                imageLabel.setName(currentFileList[i].getName() + fileOrFolder);

                imageLabel.setOpaque(false);
                imageLabel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        // Left click
                        if (e.getButton() == MouseEvent.BUTTON1) {

                            if (e.getComponent().getName().contains("file")) {


                                File f = new File(currentFilePath + "\\" + e.getComponent().getName().replaceAll("file", ""));
                                try {
                                    Desktop.getDesktop().open(f);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                currentFilePath = currentFilePath + "\\" + e.getComponent().getName().replaceAll("folder", "");
                                currentFileList = fileTools.sortFilesFromFolders(new File(currentFilePath).listFiles());
                                view();
                            }
                            // Right click
                        } else {
                            fileToDelete = e.getComponent().getName();
                            MouseEvent globalE = SwingUtilities.convertMouseEvent(e.getComponent(), e, frame);
                            popupmenu.show(frame, globalE.getX() + 15, globalE.getY());
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        panel.setBackground(Color.LIGHT_GRAY);
                        panel.repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        panel.setBackground(null);
                        panel.repaint();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                });
                panel.add(imageLabel, BorderLayout.CENTER);
                panel.add(jLabel, BorderLayout.SOUTH);
            }
            right.add(panel);
        }
        frame.add(right, BorderLayout.CENTER);
    }

    /**
     * Method creates a tool bar and populates it with necessary functions
     */
    void toolBar() {
        JToolBar jtb = fileTools.toolBar();
        frame.add(jtb, BorderLayout.NORTH);
    }

    /**
     * Method creates file menubar
     */
    void menuBar() {
        // Menu's
        MenuGUI jMenuBar = new MenuGUI(this,folder);
        JMenuBar temp = jMenuBar.buildMenuBar();
        frame.setJMenuBar(temp);
    }
}
