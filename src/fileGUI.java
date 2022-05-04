import java.awt.*;
import javax.swing.*;

import javafx.scene.chart.Axis;

import java.awt.LayoutManager;
import java.awt.event.*;


class fileGUI implements ActionListener{

    void guiBuilder(){
        JFrame frame = new JFrame();
        frame.setTitle("File Organizer - " + VersionControl.getVersion());

        //JPanel j = new JPanel();
        //JPanel j = LogInScreen.createButtonPanel();
	JPanel j = LogInScreen.createLogInPanel();
	// Menu's
        JMenuBar menuBar = new JMenuBar();

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenu = new JMenuItem("About");
        aboutMenu.addActionListener(this);
        
        helpMenu.add(aboutMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
        

        frame.setSize(800,800);
        
	frame.add(j);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame f = new JFrame();
        f.setTitle("About the builders");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setSize(400,400);



        JPanel j = new JPanel();
        j.setLayout(new GridLayout(0,1));
        j.setSize(400,400);
        JLabel verison = new JLabel("Version - "+VersionControl.getVersion());        
        JLabel user = new JLabel("This app is registered to: Bob Keener");
        JLabel providedBy = new JLabel("<html>This app provided by:<br>Michael Theisen ------ Brother in mead<br>Jasharn Thiara ------- Covid carrier<br>Trevor Tomlin -------- The real brains<br>Patrick Tibbals ------- Sometimes mildly inteligent<br></html>");
        
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
}
