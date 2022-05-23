/**
 * TCSS 360
 * 
 * Class represents the Login screen for the program.
 */

package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.GridLayout;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;

class LogInScreen {
	/**
	 * Login Screen for GUI.
	 * First state loaded after start of program.
	 *
	 * @author  Patrick Tibbals
	 * @version 1.0
	 * @since   2022-05-18
	 */
	String temp = "";
	private JPanel buttonPanel;
	private JPanel logInPanel;
	private JFrame myFrame;

	/**
	 * Constructor that take in a frame and populates the frame with desired qualities
	 * of the login page.
	 * @param frame
	 */
	public LogInScreen(JFrame frame) throws src.ExportException , java.io.IOException{

		myFrame = frame;
		buttonPanel = createButtonPanel();
		logInPanel = createLogInPanel();
		myFrame.add(buttonPanel);

	}

	/**
	 * Helper Method taht clears panel and shows new state of program.
	 * @param panel
	 */
	private void changePanel(JPanel panel) {

		myFrame.getContentPane().removeAll();
		myFrame.add(panel);
		myFrame.validate();
		myFrame.repaint();

	}

	/**
	 * Method used to create button panel for our login screen.
	 * @return
	 */
	private JPanel createButtonPanel() throws src.ExportException , java.io.IOException{

		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panelNest = new JPanel();

		JButton signOnBut = new JButton("Sign On");
		JButton importButton = new JButton("Import Button");
		JButton exportButton = new JButton("Export Button");

		//When user has clicked sign on the state of the program changes
		signOnBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(logInPanel);
			}
		});

		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				File file = new File(String.valueOf(fileChooser));

			}
		});


		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFrame frame = new JFrame("Pick a User to Export");
					JPanel jPanel = new JPanel(new GridLayout(0,4));
					Registration r = new Registration();
					ArrayList<User> myUserList = r.getMyUserList();
					for (User user : myUserList) {
						JButton jButton = new JButton(user.getUserName());
						jButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									temp = "src/Exports/"+jButton.getText()+".txt" ;
									String trueOrfalse;
									if(user.getPriveleges() == true){
										trueOrfalse = "true";
									}else{
										trueOrfalse = "false";
									}
									src.ImporterExporter.exportSettings(temp, new String[]{"username", "email", "password"}
											, new String[]{user.getUserName(), user.getPassword(), trueOrfalse});
								} catch (src.ExportException | java.io.IOException ex ) {
									ex.printStackTrace();
								}


							}
						});
						jPanel.add(jButton);
					}
					frame.add(jPanel);
					frame.setLocationRelativeTo(null);
					frame.pack();

					frame.setVisible(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		ImageIcon profileImage = new ImageIcon("src/resources/profile.png");
		Image imageProfile = profileImage.getImage();
		Image newImageProfile = imageProfile.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
		profileImage = new ImageIcon(newImageProfile);

		JLabel signOnLabel = new JLabel();
		JLabel userNameText = new JLabel("Bob");

		panel.setLayout(new BorderLayout());
		panel.add(panel2, BorderLayout.CENTER);

		panel.add(panelNest, BorderLayout.SOUTH);

		panelNest.setLayout(new GridLayout(0, 1));

		panelNest.add(panel3);
		panelNest.add(panel4);

		signOnLabel.setIcon(profileImage);

		panel2.add(signOnLabel);
		panel2.add(userNameText);

		panel3.add(signOnBut);

		panel4.add(importButton);
		panel4.add(exportButton);

		return panel;

	}

	/**
	 * Method creates the login panel after the user has selected sign-on.
	 * @return
	 */
	private JPanel createLogInPanel() {

		JPanel panel = new JPanel(new GridLayout(4, 2));

		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

		JLabel userNameText = new JLabel("Username:");
		JTextField userNameField = new JTextField("Bob_Keener");

		JLabel emailText = new JLabel("Email:");
		JTextField emailField = new JTextField("Bob@bob.com");

		JLabel pwdText = new JLabel("Password:");
		JTextField pwdField = new JTextField("**********");

		JButton signOnBut = new JButton("Sign On");
		signOnBut.setSize(20, 20);

		JButton cancelBut = new JButton("Cancel");

		//Action Listener called when user has clicked sign on.
		signOnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					Registration r = new Registration();
					// Will pass to login successful and get boolean result
					// of whether or not the login was successful.
					if (r.loginSuccesful(userNameField.getText(), pwdField.getText())) {
						//new src.User(userNameField.getText(), emailText.getText(), pwdField.getText(), true);

						myFrame.getContentPane().removeAll();
						myFrame.validate();
						myFrame.repaint();
						new FileView(myFrame);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Action Listener creates previous form if cancel button is clicked.
		cancelBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(buttonPanel);
			}
		});

		panel.add(userNameText);
		panel.add(userNameField);

		panel.add(emailText);
		panel.add(emailField);

		panel.add(pwdText);
		panel.add(pwdField);

		panel.add(signOnBut);
		panel.add(cancelBut);

		return panel;

	}

}
