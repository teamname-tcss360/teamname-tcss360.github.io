package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

class LogInScreen {

	private JPanel buttonPanel;
	private JPanel logInPanel;
	private JFrame myFrame;

	public LogInScreen(JFrame frame) {

		myFrame = frame;
		buttonPanel = createButtonPanel();
		logInPanel = createLogInPanel();
		myFrame.add(buttonPanel);

	}

	private void changePanel(JPanel panel) {

		myFrame.getContentPane().removeAll();
		myFrame.add(panel);
		myFrame.validate();
		myFrame.repaint();

	}

	private JPanel createButtonPanel() {

		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panelNest = new JPanel();

		JButton signOnBut = new JButton("Sign On");
		JButton importButton = new JButton("Import Button");
		JButton exportButton = new JButton("Export Button");

		signOnBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(logInPanel);
			}
		});

		// importButton.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// changePanel(?????????);
		// }
		// });

		// exportButton.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// changePanel(??????????);
		// }
		// });

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

		signOnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myFrame.getContentPane().removeAll();
				myFrame.validate();
				myFrame.repaint();
				new FileView(myFrame);
			}
		});
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
