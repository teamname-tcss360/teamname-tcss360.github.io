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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    ;

    /**
     * JPanel field (left side with names).
     */
    private JPanel left = new JPanel();

    /**
     * String array field to represent folder names.
     */

    private File[] currentFileList;

    public String getUserName() {
        return userName;
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * FileView Constructor.
     *
     * @param f
     */
    private String userName;
    private String currentFilePath;
    private String fileToDelete;
    private Image imageNew;

    public File[] getCurrentFileList() {
        return currentFileList;
    }

    public String getCurrentFilePath(){
        return currentFilePath;
    }
    public void setCurrentFilePath(String filePath){
        currentFilePath = filePath;
    }
    public void setCurrentFileList(File[] fileList){
        currentFileList = fileList;
    }
    /**
     * Helper class instance
     */
    private FileTools fileTools = new FileTools(this);


    // Popup for right-clicking file
    private final JPopupMenu popupmenu = new JPopupMenu("Edit");

    public FileView(JFrame f, String user) {
        userName = user;
        frame = f;
        frame.setBackground(Color.gray);
        frame.setLayout(new BorderLayout());
        currentFilePath = "FileHub/" + userName;

        File userHome = new File(currentFilePath);
        currentFileList = userHome.listFiles();

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

        menuBar();
        toolBar();

        currentFileList = fileTools.sortFilesFromFolders(currentFileList);
        view();
    }
    /**
     * Method is called by constructor and creates desired GUI functionality.
     */
    void view() {
        left.removeAll();
        right.removeAll();
        fileList();
        visualInterpretation();
        frame.validate();
    }

    /**
     * Method populates right side panel with folders (made of labels and images) to add to our JFrame.
     */

    void visualInterpretation() {
        ImageIcon icon = new ImageIcon("src\\resources\\folder.png");
        ImageIcon icon2 = new ImageIcon("src\\resources\\file.png");
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


                                File f = new File(currentFilePath + "/" + e.getComponent().getName().replaceAll("file", ""));
                                try {
                                    Desktop.getDesktop().open(f);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            } else {
                                currentFilePath = currentFilePath + "/" + e.getComponent().getName().replaceAll("folder", "");
                                currentFileList = new File(currentFilePath).listFiles();
                                view();
                            }
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
                    public void mouseReleased(MouseEvent e) {}

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
        frame.add(jtb,BorderLayout.NORTH);
    }
    
    /**
     * Method creates file menubar
     */
    void menuBar() {
    	// Menu's
        MenuGUI jMenuBar = new MenuGUI(this);
        JMenuBar temp = jMenuBar.buildMenuBar();
        frame.setJMenuBar(temp);
    }
    /**
     * Method creates file list along the left side of the JFrame.
     */
    void fileList() {

        left.setLayout(new GridLayout(0, 1));
        JButton homeButton = new JButton(userName);

        homeButton.setContentAreaFilled(false);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFilePath = "FileHub/" + userName;
                currentFileList = new File(currentFilePath).listFiles();
                currentFileList = fileTools.sortFilesFromFolders(currentFileList);
                view();
            }
        });


        left.add(homeButton);
        if (!currentFileList.equals(null)) {
            for (int i = 0; i < currentFileList.length; i++) {
                if (currentFileList[i].isFile()) {
                    //Do nothing not a folder
                } else {
                    JButton button = new JButton(currentFileList[i].getName());

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            currentFilePath = currentFilePath + "/" + button.getText();
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

}
