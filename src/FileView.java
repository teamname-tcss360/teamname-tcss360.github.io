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

class FileView{
	
	/**
	 * JFrame field.
	 */
    private JFrame frame;
    
    /**
     * JPanel field (right side with folders).
     */
    private JPanel right = new JPanel();;
    private JPanel left = new JPanel();
    /**
     * String array field to represent folder names. 
     */
    String[] strings = {"Bob","Kitchen","Dining room","Garage","Bedrooms"};
    private File[] currentFileList;
    /**
     * FileView Constructor.
     * @param f
     */
    private String userName;
    private String currentFilePath;

    // Popup for right-clicking file
    final JPopupMenu popupmenu = new JPopupMenu("Edit");

    public FileView(JFrame f, String user){
        userName = user;
        frame = f;
        frame.setBackground(Color.gray);
        frame.setLayout(new BorderLayout());
        currentFilePath = "FileHub/"+userName;

        File userHome = new File(currentFilePath);
        currentFileList = userHome.listFiles();

        JMenuItem delete = new JMenuItem("Delete");
        popupmenu.add(delete);

        frame.add(popupmenu);


        view();
    }
    
    /**
     * Method is called by constructor and creates desired GUI functionality.
     */
    void view(){
        fileList();
        toolBar();
        visualInterpretation();
        frame.validate();
    }
    
    /**
     * Method populates right side panel with folders (made of labels and images) to add to our JFrame.
     */
    Image imageNew;
    void visualInterpretation(){
    	ImageIcon icon = new ImageIcon("src\\resources\\folder.png");
    	ImageIcon icon2 = new ImageIcon("src\\resources\\file.png");
        right.setLayout(new GridLayout(4,4));

        sortFilesFromFolders();
        for (int i = 0; i < 16; i++) {
            JPanel panel = new JPanel();
            if(i < currentFileList.length) {

                panel.setLayout(new BorderLayout());
                JLabel jLabel = new JLabel(currentFileList[i].getName(),JLabel.CENTER);
                jLabel.setOpaque(false);

                String fileOrFolder;
                if(currentFileList[i].isFile()){
                    //Add to 2nd list

                    imageNew = icon2.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    fileOrFolder = "file";
                }else {
                    imageNew = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    fileOrFolder = "folder";
                }
                //Create label and add action Listener to "open folder" when clicked
                JLabel imageLabel = new JLabel(new ImageIcon(imageNew),JLabel.CENTER);

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
                            }
                            else {
                                MouseEvent globalE = SwingUtilities.convertMouseEvent(e.getComponent(), e, frame);
                                popupmenu.show(frame, globalE.getX()+15, globalE.getY());
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
        frame.add(right,BorderLayout.CENTER);
    }

    /**
     * Method creates a tool bar and populates it with necessary functions
     */
    void toolBar(){
        JToolBar jToolBar = new JToolBar();
        JButton importFile = new JButton("Import File");
        importFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog((java.awt.Frame) null);
                fd.setVisible(true);

                String dir = fd.getDirectory();
                String file = fd.getFile();

                System.out.println(dir  );
                System.out.println(file);

                if (file.isEmpty()) return;
                System.out.println(currentFilePath);
                try {
                    Files.copy(Paths.get(dir, file), Paths.get(currentFilePath, file));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                currentFileList = new File(currentFilePath).listFiles();
                left.removeAll();
                right.removeAll();
                view();
            }
		});
        
        JButton exportFile = new JButton("Export File");

        exportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser("FileHub/"+userName);
                jFileChooser.setVisible(true);
                jFileChooser.showOpenDialog(exportFile);

                File file = jFileChooser.getSelectedFile();

                String userHomeFolder = System.getProperty("user.home")+"\\Desktop";

                try {
                    Files.copy(Paths.get(file.getAbsolutePath()),Paths.get(userHomeFolder,file.getName()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
		});
        
        JLabel searchLabel = new JLabel("Search");
        JTextField jTextField = new JTextField(25);
        searchLabel.add(jTextField);
        JPanel sortChoices = new JPanel();
        JLabel sortLabel = new JLabel("Sort By: ");
        JRadioButton aToZRadioButton = new JRadioButton("A-Z");
        aToZRadioButton.setSelected(true);
        JRadioButton zToARadioButton = new JRadioButton("Z-A");
        
        // When the A-Z button is clicked the array of strings will be sorted and then the form will be updated
        // and create the buttons in the new order.
        aToZRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (zToARadioButton.isSelected()) {
					zToARadioButton.setSelected(false);
				}
				sortList(strings);
				right.removeAll();
				visualInterpretation();
				right.repaint();
				right.validate();
				
			}
		});
        
        // When the Z-A button is clicked the array of strings will be sorted and then reversed
        // the form will then be reshown in the desired order.
        zToARadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (aToZRadioButton.isSelected()) {
					aToZRadioButton.setSelected(false);
				}
				reverseSort(strings);
				right.removeAll();
				visualInterpretation();
				right.repaint();
				right.validate();
				
			}
		});
        
        JRadioButton dateRadioButton = new JRadioButton("Date");
        sortChoices.add(sortLabel);
        sortChoices.add(aToZRadioButton);
        sortChoices.add(zToARadioButton);
        sortChoices.add(dateRadioButton);


        ImageIcon icon = new ImageIcon("src\\resources\\magnifying-glass.png");
        Image imageNew = icon.getImage();
        Image newImgNew = imageNew.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImgNew);
        searchLabel.setIcon(icon);



        jToolBar.add(importFile);
        jToolBar.add(exportFile);
        jToolBar.add(searchLabel);
        jToolBar.add(jTextField);
        jToolBar.add(sortChoices);
        frame.add(jToolBar,BorderLayout.PAGE_START);

    }
    
    /**
     * Method creates file list along the left side of the JFrame.
     */
    void fileList(){

        left.setLayout(new GridLayout(0,1));
        JButton homeButton = new JButton(userName);

        homeButton.setContentAreaFilled(false);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFilePath = "FileHub/"+userName;
                currentFileList = new File(currentFilePath).listFiles();
                left.removeAll();
                right.removeAll();
                view();
            }
        });


        left.add(homeButton);
        if(!currentFileList.equals(null)) {
            for (int i = 0; i < currentFileList.length; i++) {
                if(currentFileList[i].isFile()){
                    //Do nothing not a folder
                }else {
                    JButton button = new JButton(currentFileList[i].getName());

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            currentFilePath = currentFilePath + "/" + button.getText();
                            currentFileList = new File(currentFilePath).listFiles();
                            left.removeAll();
                            right.removeAll();
                            view();

                        }
                    });


                    left.add(button);
                }
            }
        }
        frame.add(left,BorderLayout.WEST);
    }
    
    /**
     * Helper method to organize methods and sort List.
     * @param theString
     */
    void sortList(String[] theString) { 
    	Arrays.sort(theString);
    }
    
    /**
     * Helper method to organize methods and reverse sort List.
     * @param theString
     */
    void reverseSort(String[] theString) {
    	Stack<String >temp = new Stack<String>();
    	Arrays.sort(theString);
    	for (int i = 0; i < theString.length; i++) {
    		temp.push(theString[i]);
    	}
    	int j = 0;
    	while (!temp.isEmpty()) {
    		theString[j] = temp.pop();
    		j++;
    	}
    }

    void sortFilesFromFolders(){
        Arrays.sort( currentFileList, (a, b) -> {
            // do your comparison here returning -1 if a is before b, 0 if same, 1 if a is after b
            if (a.isFile() && !b.isFile()) {
                return 1;
            } else if (a.isFile() && b.isFile()) {
                return a.compareTo(b);
            }else if (!a.isFile() && !b.isFile()) {
                return a.compareTo(b);
            }else {
                return -1;
            }
        });
    }
}
