/**
 * TCSS 360
 * 
 * Class represents a view panel for the GUI. Class will occupy jframe with a two main panels.
 * A panel for the drop down menu, and a panel  with the folders for the user to interact with.
 */

package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;
import java.util.Stack;

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

    public FileView(JFrame f, String user){
        userName = user;
        frame = f;
        frame.setBackground(Color.gray);
        frame.setLayout(new BorderLayout());
        currentFilePath = "FileHub/"+userName;

        File userHome = new File(currentFilePath);
        currentFileList = userHome.listFiles();





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
    void visualInterpretation(){
    	ImageIcon icon = new ImageIcon("src\\resources\\folder.png");
    	ImageIcon icon2 = new ImageIcon("src\\resources\\file.png");
        right.setLayout(new GridLayout(4,4));
        
        for (int i = 0; i < 16; i++) {
            JPanel panel = new JPanel();
            if(i < currentFileList.length) {

                panel.setLayout(new BorderLayout());
                JLabel jLabel = new JLabel(currentFileList[i].getName(),JLabel.CENTER);
                jLabel.setOpaque(false);
                Image imageNew;
                String fileOrFolder;
                if(currentFileList[i].isFile()){
                    imageNew = icon2.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    fileOrFolder = "file";
                }else {
                    imageNew = icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    fileOrFolder = "folder";
                }
                icon = new ImageIcon(imageNew);
                
                //Create label and add action Listener to "open folder" when clicked
                JLabel imageLabel = new JLabel(icon,JLabel.CENTER);

                // Name for clicked listener
                imageLabel.setName(currentFileList[i].getName() + fileOrFolder);

                imageLabel.setOpaque(false);
                imageLabel.addMouseListener(new MouseListener() {
        				@Override
        				public void mouseClicked(MouseEvent e) {
                            if(e.getComponent().getName().contains("file")){
                                //Do nothing for now it is a file not a folder
                            }else {
                                currentFilePath = currentFilePath + "/" + e.getComponent().getName().replaceAll("folder","");
                                currentFileList = new File(currentFilePath).listFiles();
                                left.removeAll();
                                right.removeAll();
                                view();

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
        JButton newFile = new JButton("Import File");
        newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new java.awt.FileDialog((java.awt.Frame) null).setVisible(true);
			}
				
		});
        
        JButton exportFile = new JButton("Export File");
        exportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new java.awt.FileDialog((java.awt.Frame) null).setVisible(true);
			}
		});
        
        JLabel searchLabel = new JLabel("Search");
        JTextField jTextField = new JTextField(25);
        searchLabel.add(jTextField);
        JPanel sortChoices = new JPanel();
        JLabel sortLabel = new JLabel("Sort By: ");
        JRadioButton aToZRadioButton = new JRadioButton("A-Z");
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



        jToolBar.add(newFile);
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

}
