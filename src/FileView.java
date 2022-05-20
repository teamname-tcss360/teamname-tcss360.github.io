package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JFrame frame;
    String[] strings = {"Bob","Kitchen","Dining room","Garage","Bedrooms"};
    private JPanel right = new JPanel();;
    
    public FileView(JFrame f){
        frame = f;

        frame.setBackground(Color.gray);
        frame.setLayout(new BorderLayout());

        view();
    }
    void view(){

        fileList();
        toolBar();
        visualInterpretation();
        frame.validate();

    }
    void visualInterpretation(){
        right.setLayout(new GridLayout(4,4));
        for (int i = 1; i < 16; i++) {
            JPanel panel = new JPanel();
            if(i < strings.length) {

                panel.setLayout(new BorderLayout());
                JLabel jLabel = new JLabel(strings[i],JLabel.CENTER);



                ImageIcon icon = new ImageIcon("src\\resources\\folder.png");
                Image imageNew = icon.getImage();
                Image newImgNew = imageNew.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newImgNew);
                JLabel imageLabel = new JLabel(icon,JLabel.CENTER);

                panel.add(imageLabel, BorderLayout.CENTER);
                panel.add(jLabel, BorderLayout.SOUTH);
            }
            right.add(panel);
        }
        frame.add(right,BorderLayout.CENTER);
    }

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
        aToZRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (zToARadioButton.isSelected()) {
					aToZRadioButton.setSelected(false);
				}
				sortList(strings);
				right.removeAll();
				visualInterpretation();
				right.repaint();
				right.validate();
				
			}
		});
        
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

    void fileList(){
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(0,1));

        for (int i = 0; i < strings.length; i++) {
            JButton button = new JButton(strings[i]);
            if(i==0){
                button.setBackground(Color.gray);
            }else {
            }
            left.add(button);
        }
        frame.add(left,BorderLayout.WEST);
    }
    
    void sortList(String[] theString) { 
    	Arrays.sort(theString);
    }
    
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
