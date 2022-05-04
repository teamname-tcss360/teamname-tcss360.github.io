import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;

class LogInScreen {

    private JPanel buttonPanel;
    private JPanel logInPanel;

    public void LogInScreen() {

	buttonPanel = createButtonPanel();

    }

    public static JPanel createButtonPanel() {

	JPanel panel = new JPanel();

	JButton signOnBut = new JButton("Sign On");

	panel.add(signOnBut);

	return panel;

    }

    public static JPanel createLogInPanel() {

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
