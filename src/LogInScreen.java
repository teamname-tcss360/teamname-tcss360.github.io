/**
 * TCSS 360
 * 
 * Class represents the Login screen for the program.
 */

package src;

import javafx.beans.binding.BooleanExpression;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.*;
import java.awt.GridLayout;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class LogInScreen {
	/**
	 * Login Screen for GUI.
	 * First state loaded after start of program.
	 *
	 * @author Patrick Tibbals
	 * @version 1.0
	 * @since 2022-05-18
	 */
	String temp = "";
	private JPanel buttonPanel;
	private JPanel logInPanel;
	private JFrame myFrame;
	private Registration r = new Registration();

	/**
	 * Constructor that take in a frame and populates the frame with desired
	 * qualities
	 * of the login page.
	 * 
	 * @param frame
	 */
	public LogInScreen(JFrame frame) throws src.ExportException, java.io.IOException {

		myFrame = frame;
		buttonPanel = createButtonPanel();
		logInPanel = createLogInPanel("","","");

		myFrame.add(buttonPanel);

	}

	/**
	 * Helper Method taht clears panel and shows new state of program.
	 * 
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
	 * 
	 * @return
	 */
	private JPanel createButtonPanel() throws src.ExportException, java.io.IOException {

		JPanel panel = new JPanel();
		JPanel userProfileDisplayPanel = new JPanel();
		JPanel signOnButtonPanel = new JPanel();
		JPanel importExportPanel = new JPanel();
		JPanel signOnAndImportExportPanel = new JPanel();

		JButton signOnBut = new JButton("Sign On");
		JButton importButton = new JButton("Import Profile");
		JButton exportButton = new JButton("Export Profile");

		// When user has clicked sign on the state of the program changes
		signOnBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(logInPanel);
			}
		});

		importButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

				} catch (java.io.IOException ex) {
					ex.printStackTrace();
				}

			}
		});

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
								temp = "src/Exports/" + jButton.getText() + ".txt";
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

							} catch (src.ExportException | java.io.IOException ex) {
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

		ImageIcon profileImage = new ImageIcon("src/resources/profile.png");
		Image imageProfile = profileImage.getImage();
		Image newImageProfile = imageProfile.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
		profileImage = new ImageIcon(newImageProfile);

		ArrayList<User> myUserList = r.getMyUserList();
		userProfileDisplayPanel.setLayout(new BorderLayout());
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

		userProfileDisplayPanel.add(userGrid,BorderLayout.CENTER);







		signOnButtonPanel.add(signOnBut);

		importExportPanel.add(importButton);
		importExportPanel.add(exportButton);

		signOnAndImportExportPanel.setLayout(new GridLayout(0, 1));

		signOnAndImportExportPanel.add(signOnButtonPanel);
		signOnAndImportExportPanel.add(importExportPanel);


		panel.setLayout(new BorderLayout());

		panel.add(userProfileDisplayPanel, BorderLayout.CENTER);
		panel.add(signOnAndImportExportPanel, BorderLayout.SOUTH);

		return panel;

	}

	/**
	 * Method creates the login panel after the user has selected sign-on.
	 * 
	 * @return
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

		JButton signOnBut = new JButton("Sign On");
		signOnBut.setSize(20, 20);

		JButton cancelBut = new JButton("Cancel");

		// Action Listener called when user has clicked sign on.
		signOnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Will pass to login successful and get boolean result
				// of whether or not the login was successful.
				if (r.loginSuccessful(userNameField.getText(), emailField.getText(), pwdField.getText())) {
					myFrame.getContentPane().removeAll();
					myFrame.validate();
					myFrame.repaint();
					new FileView(myFrame,userNameField.getText());
				} else {
					JOptionPane.showMessageDialog(null, "Please try entering your information again",
							"Incorrect Email/Username/Password", JOptionPane.ERROR_MESSAGE);
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

		panel.add(signOnBut);
		panel.add(cancelBut);

		return panel;

	}

}
