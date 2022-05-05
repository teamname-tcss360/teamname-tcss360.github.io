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

		JButton signOnBut = new JButton("Sign On");

		signOnBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePanel(logInPanel);
			}
		});

		panel.add(signOnBut);

		return panel;

    }

    private JPanel createLogInPanel() {

		JPanel panel = new JPanel(new GridLayout(4, 2));

		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 400, 100));

		JLabel userNameText = new JLabel("Username:");
		JTextField userNameField = new JTextField("Bob");

		JLabel emailText = new JLabel("Email:");
		JTextField emailField = new JTextField("Bob@bob.com");

		JLabel pwdText = new JLabel("Password:");
		JTextField pwdField = new JTextField("Bob");

		JButton signOnBut = new JButton("Sign On");
		JButton cancelBut = new JButton("Cancel");

		signOnBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myFrame.getContentPane().removeAll();
				myFrame.validate();
				myFrame.repaint();
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
