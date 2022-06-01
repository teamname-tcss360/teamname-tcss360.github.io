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
    /**
     * FileView Constructor.
     *
     * @param f
     */
    private String userName;
    private String currentFilePath;
    private String fileToDelete;
    
    /**
     * Helper class instance
     */
    private FileTools fileTools = new FileTools();


    // Popup for right-clicking file
    final JPopupMenu popupmenu = new JPopupMenu("Edit");

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
                currentFileList = fileTools.deleteFile(fileToDelete);
                left.removeAll();
                right.removeAll();
                view();
            }
        });

        popupmenu.add(delete);

        frame.add(popupmenu);

        menuBar();
        currentFileList = fileTools.sortFilesFromFolders(currentFileList);
        view();
    }


    /**
     * Method is called by constructor and creates desired GUI functionality.
     */
    void view() {
        fileList();
        toolBar();
        visualInterpretation();
        frame.validate();
    }

    /**
     * Method populates right side panel with folders (made of labels and images) to add to our JFrame.
     */
    Image imageNew;

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
                                left.removeAll();
                                right.removeAll();
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
                        //TODO
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        panel.setBackground(null);
                        panel.repaint();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub

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
        JToolBar jToolBar = new JToolBar();
        JButton importFile = new JButton("Import File");

        importFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog((java.awt.Frame) null);
                fd.setVisible(true);

                String dir = fd.getDirectory();
                String file = fd.getFile();

                System.out.println(dir);
                System.out.println(file);

                if (file.isEmpty()) return;
                System.out.println(currentFilePath);
                try {
                    Files.copy(Paths.get(dir, file), Paths.get(currentFilePath, file));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                currentFileList = new File(currentFilePath).listFiles();
                currentFileList = fileTools.sortFilesFromFolders(currentFileList);
                left.removeAll();
                right.removeAll();
                view();
            }
        });

        JButton exportFile = new JButton("Export File");

        exportFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser("FileHub/" + userName);
                jFileChooser.setVisible(true);
                jFileChooser.showOpenDialog(exportFile);

                File file = jFileChooser.getSelectedFile();

                String userHomeFolder = System.getProperty("user.home") + "\\Desktop";

                try {
                    Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(userHomeFolder, file.getName()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JLabel searchLabel = new JLabel("Search");
        JButton searchButton = new JButton();
        JTextField jTextField = new JTextField(25);

        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFileList = fileTools.search(jTextField.getText().toLowerCase(Locale.ROOT), userName);
                jTextField.setText("");
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
            	currentFileList = fileTools.search(jTextField.getText().toLowerCase(Locale.ROOT), userName);
            	jTextField.setText("");
            	left.removeAll();
                right.removeAll();
                view();
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
                currentFileList = fileTools.sortFilesFromFolders(currentFileList);
                left.removeAll();
                right.removeAll();
                view();
            }
        });

        // When the Z-A button is clicked the array of Files will be sorted and then reversed
        // the form will then be reshown in the desired order.
        zToARadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (aToZRadioButton.isSelected()) {
                    aToZRadioButton.setSelected(false);
                }
                currentFileList = fileTools.reverseSortFilesFromFolders(currentFileList);
                left.removeAll();
                right.removeAll();
                view();
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
        frame.add(jToolBar, BorderLayout.PAGE_START);

    }
    
    /**
     * Method creates file menubar
     */
    void menuBar() {
 
    	// Menu's
        JMenuBar menuBar = new JMenuBar();

        ImageIcon iconNew = new ImageIcon("src/resources/new.png");
        Image imageNew = iconNew.getImage();
        Image newImgNew = imageNew.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconNew = new ImageIcon(newImgNew);

        ImageIcon iconOpen = new ImageIcon("src/resources/open.png");
        Image imageOpen = iconOpen.getImage();
        Image newImgOpen = imageOpen.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconOpen = new ImageIcon(newImgOpen);

        ImageIcon iconSave = new ImageIcon("src/resources/save.png");
        Image imageSave = iconSave.getImage();
        Image newImgSave = imageSave.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconSave = new ImageIcon(newImgSave);

        ImageIcon iconExit = new ImageIcon("src/resources/exit.png");
        Image imageExit = iconExit.getImage();
        Image newImgExit = imageExit.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconExit = new ImageIcon(newImgExit);

        ImageIcon iconAbout = new ImageIcon("src/resources/about.png");
        Image imageAbout = iconAbout.getImage();
        Image newImgAbout = imageAbout.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconAbout = new ImageIcon(newImgAbout);
    	 JMenu fileMenu = new JMenu("File");
         JMenu importMenu = new JMenu("Import");
         JMenu exportMenu = new JMenu("Export");

         JMenuItem importMenuItem = new JMenuItem("Import from: ");
    	 JMenuItem exportMenuItem = new JMenuItem("Export from: ");

         importMenu.add(importMenuItem);
         exportMenu.add(exportMenuItem);

         JMenuItem newMenuItem = new JMenuItem("New", iconNew);
         JMenuItem openMenuItem = new JMenuItem("Open", iconOpen);
         JMenuItem saveMenuItem = new JMenuItem("Save", iconSave);
         JMenuItem exitMenuItem = new JMenuItem("Sign Out", iconExit);
         exitMenuItem.setToolTipText("Sign Out");

         exitMenuItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 frame.dispose();
                 MainView m = new MainView();
                 m.guiBuilder();
             }
         });

         JMenu editMenu = new JMenu("Edit");

         JMenu helpMenu = new JMenu("Help");
         JMenuItem aboutMenuItem = new JMenuItem("About", iconAbout);

         aboutMenuItem.addActionListener(new ActionListener() {
        	 
        	 @Override
        	 public void actionPerformed(ActionEvent e) {
        		 JFrame f = new JFrame();
        		 f.setTitle("About the builders");
        		 f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        		 f.setSize(400, 400);

        		 JPanel j = new JPanel();
        		 j.setLayout(new GridLayout(0, 1));
        		 j.setSize(400, 200);
        		 JLabel verison = new JLabel("Version - " + VersionControl.getVersion());
        		 JLabel user = new JLabel("This application is registered to: Bob Keener");
        		 JLabel providedBy = new JLabel(
                     "<html>This application provided by:<br><br>Michael Theisen ------ Brother in mead<br>Jasharn Thiara ------- Covid carrier<br>Trevor Tomlin -------- The real brains<br>Patrick Tibbals ------- Sometimes mildly inteligent<br></html>");

        		 verison.setHorizontalAlignment(JLabel.CENTER);
        		 user.setHorizontalAlignment(JLabel.CENTER);
        		 providedBy.setHorizontalAlignment(JLabel.CENTER);

        		 j.add(verison);
        		 j.add(user);
        		 j.add(providedBy);

        		 f.add(j);
        		 f.setLocationRelativeTo(null);
        		 f.setVisible(true);
        	 }
         });

         fileMenu.add(newMenuItem);
         fileMenu.add(openMenuItem);
         fileMenu.add(saveMenuItem);
         fileMenu.addSeparator();
         fileMenu.add(importMenu);
         fileMenu.add(exportMenu);
         fileMenu.addSeparator();
         fileMenu.add(exitMenuItem);

         // editMenu.add();
         helpMenu.add(aboutMenuItem);

         menuBar.add(fileMenu);
         menuBar.add(editMenu);
         menuBar.add(helpMenu);

         helpMenu.add(aboutMenuItem);
         menuBar.add(helpMenu);
         frame.setJMenuBar(menuBar);
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
                left.removeAll();
                right.removeAll();
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
                            left.removeAll();
                            right.removeAll();
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
