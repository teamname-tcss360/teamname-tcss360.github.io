/**
 * TCSS 360
 *
 * Class represents the Login screen for the program.
 */

package src;

//import javafx.beans.binding.BooleanExpression;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.*;
import java.awt.GridLayout;
import java.net.URL;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Login Screen for GUI.
 * First state loaded after start of program.
 *
 * @author Trevor Tomlin
 * @version 1.0
 * @since 2022-05-18
 */
class LogInScreen {

	/**
	 * LogInScreen instance field
	 */
	private static LogInScreen instance = null;

	/**
	 *  Temp string for export profile
	 */
	private String temp = "";

	/**
	 * Button panel field
	 */
	private JPanel buttonPanel;

	/**
	 * Log in panel field
	 */
	private JPanel logInPanel;

	/**
	 * myFrame field
	 */
	private JFrame myFrame;
	/**
	 * Registration instance field
	 */
	private src.Registration r;

	/**
	 * Gets the current instance, or creates on if one does not exist.
	 * @author Trevor Tomlin
	 * @return instance of LogInScreen
	 * @throws IOException
	 * @throws src.ExportException
	 */
	public static LogInScreen getInstance() throws IOException, src.ExportException {
		if(instance == null) {
			instance = new LogInScreen();

		}
		return instance;
	}

	/**
	 * Private constructor to stop instantiation
	 * @author Trevor Tomlin
	 */
	private LogInScreen(){

	}

	/**
	 * Sets login screen frame to parameter
	 * @author Trevor Tomlin
	 * @param frame
	 */
	public void setFrame(JFrame frame) {
		myFrame = frame;
	}

	/**
	 * Creates panels and registration for login screen.
	 * Must be called before using login screen.
	 * @author Trevor Tomlin
	 * @throws IOException
	 * @throws src.ExportException
	 */
	public void setup() throws IOException, src.ExportException {

		try {

 			r = new src.Registration();

		}

		catch (Exception e) {}

		buttonPanel = createButtonPanel();
		logInPanel = createLogInPanel("","","");

		myFrame.add(buttonPanel);

	}

	/**
	 * Helper Method taht clears panel and shows new state of program.
	 *
	 * @author Trevor Tomlin
	 * @param panel
	 */
	void changePanel(JPanel panel) {

		myFrame.getContentPane().removeAll();
		myFrame.add(panel);
		myFrame.validate();
		myFrame.repaint();

	}

	/**
	 * Method used to create button panel for the login screen.
	 *
	 * @author Trevor Tomlin
	 * @author Michael Theisen
	 * @return JPanel
	 */
	JPanel createButtonPanel() throws src.ExportException, IOException {

		JPanel panel = new JPanel();
		JPanel userProfileDisplayPanel = new JPanel();
		JPanel creatNewButtonPanel = new JPanel();
		JPanel importExportPanel = new JPanel();
		JPanel signOnAndImportExportPanel = new JPanel();

		JButton createNewProfileButton = new JButton("Create New Profile");
		JButton importButton = new JButton("Import Profile");
		JButton exportButton = new JButton("Export Profile");


		// When user has clicked sign on the state of the program changes
		createNewProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(logInPanel);
			}
		});

		//Import Profile
		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					src.ImporterExporter.importProfile(importButton,r,instance);
			}
		});
		//Export Profile
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame frame = new JFrame("Pick a User to Export");
				JPanel jPanel = new JPanel(new GridLayout(0, 4));
				ArrayList<User> myUserList = r.getMyUserList();
				for (User user : myUserList) {
					JButton jButton = new JButton(user.getUserName());
					jButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								temp = "src/resources/Exports/" + jButton.getText() + ".txt";
								String trueOrfalse;
								if (user.getPriveleges() == true) {
									trueOrfalse = "true";
								} else {
									trueOrfalse = "false";
								}
								src.ImporterExporter.exportSettings(temp,
										new String[] { "Username", "Email", "Password", "Permissions" }, new String[] {
												user.getUserName(), user.getEmail(), user.getPassword(), trueOrfalse });
								frame.dispose();

							} catch (src.ExportException | IOException ex) {
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
			}
		});

		URL url = ClassLoader.getSystemClassLoader().getResource("profile.png");
		//Create the profiles buttons to choose a user
		ImageIcon profileImage = new ImageIcon(url);
		Image imageProfile = profileImage.getImage();
		Image newImageProfile = imageProfile.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		profileImage = new ImageIcon(newImageProfile);

		ArrayList<User> myUserList = r.getMyUserList();

		userProfileDisplayPanel.setLayout(new BorderLayout());

		//Grid for user buttons
		JPanel userGrid = new JPanel(new GridLayout());
		for (User user:myUserList) {
			JButton profileButton = new JButton();
			profileButton.setOpaque(false);
			profileButton.setContentAreaFilled(false);
			profileButton.setBorderPainted(false);

			JPanel userProfilePanel = new JPanel(new BorderLayout());
			JLabel signOnLabel = new JLabel(profileImage,JLabel.CENTER);
			JLabel userNameText = new JLabel(user.getUserName(),JLabel.CENTER);

			userProfilePanel.add(signOnLabel,BorderLayout.CENTER);

			userProfilePanel.add(userNameText,BorderLayout.SOUTH);

			profileButton.add(userProfilePanel);

			profileButton.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					changePanel(createLogInPanel(user.getUserName(),user.getEmail(),user.getPassword()));
				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					profileButton.setBorderPainted(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					profileButton.setBorderPainted(false);
				}
			});

			userGrid.add(profileButton);

		}
		//User Images
		userProfileDisplayPanel.add(userGrid,BorderLayout.CENTER);
		//Create Profile
		creatNewButtonPanel.add(createNewProfileButton);
		//ImportExport
		importExportPanel.add(importButton);
		importExportPanel.add(exportButton);

		signOnAndImportExportPanel.setLayout(new GridLayout(0, 1));

		signOnAndImportExportPanel.add(creatNewButtonPanel);
		signOnAndImportExportPanel.add(importExportPanel);


		panel.setLayout(new BorderLayout());

		panel.add(userProfileDisplayPanel, BorderLayout.CENTER);
		panel.add(signOnAndImportExportPanel, BorderLayout.SOUTH);

		return panel;

	}

	/**
	 * Method creates the login panel after the user has selected sign-on.
	 * 
	 * @author Jasharn Thiara
	 * @return JPanel
	 */
	private JPanel createLogInPanel(String username, String email, String password) {

		JPanel panel = new JPanel(new GridLayout(4, 2));

		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

		JLabel userNameText = new JLabel("Username:");
		JTextField userNameField = new JTextField(username);

		JLabel emailText = new JLabel("Email:");
		JTextField emailField = new JTextField(email);

		JLabel pwdText = new JLabel("Password:");
		JTextField pwdField = new JTextField(password);

		JButton logInBut = new JButton("Log In");
		logInBut.setSize(20, 20);

		JButton cancelBut = new JButton("Cancel");

		// Action Listener called when user has clicked sign on.
		logInBut.addActionListener(new ActionListener() {
			/**
			 * method to check if profile already exists and to make one new if it does not.
			 * @author Michael Theisen
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Will pass to login successful and get boolean result
					// of whether or not the login was successful.
					boolean createProf = false;
					ArrayList<User> mUserList = r.getMyUserList();
					if(mUserList.isEmpty()){
						createProf = true;
					}
					for (User user : mUserList) {
						if (user.getUserName().equals(userNameField.getText())) {
							break;
							//do nothing
						} else {
							createProf = true;
						}
					}
					//System.out.println(createProf);
					if (r.loginSuccessful(userNameField.getText(), emailField.getText(), pwdField.getText())) {
						myFrame.getContentPane().removeAll();
						myFrame.validate();
						myFrame.repaint();
						new FileView(myFrame, userNameField.getText());

					} else if (!(r.loginSuccessful(userNameField.getText(), emailField.getText(), pwdField.getText())) && createProf) {
						//if unsuccessful && profile is already here, make new.
						r.addToList(userNameField.getText(), emailField.getText(), pwdField.getText(), false);


						File file = new File(System.getProperty("user.home") + "\\Desktop\\TEAMNAME-File Explorer\\" + "FileViewer\\" + "FileHub\\"+ userNameField.getText());
						file.mkdir();

						myFrame.getContentPane().removeAll();
						myFrame.validate();
						myFrame.repaint();
						new FileView(myFrame, userNameField.getText());


						// if unsuccessful , AND  not already, then make new.
					} else {
						JOptionPane.showMessageDialog(null, "Please try entering your information again",
								"Incorrect Email/Username/Password", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException ex){
					ex.printStackTrace();
				}

			}
		});

		// Action Listener creates previous form if cancel button is clicked.
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

		panel.add(logInBut);
		panel.add(cancelBut);

		return panel;

	}

}