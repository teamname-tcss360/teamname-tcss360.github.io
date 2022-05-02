// import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class fileGUI implements ActionListener{

    void guiBuilder(){
        JFrame frame = new JFrame();
        frame.setTitle("File Organizer - " + VersionControl.getVersion());

        JPanel j = new JPanel();
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
        j.setSize(400,400);
        JLabel l = new JLabel();
        l.setSize(400,400);
        JTextArea jta = new JTextArea();
        
	jta.setText(VersionControl.getVersion() +"\n"+"\n"+"\n"+"Jasharn Thiara --- Covid carrier"+"\n"+"\n"+"Michael Theisen ---- Beer connoisseur"+"\n"+"\n"+"Patrick Tibbals ---- Sometimes mildly inteligent"
        +"\n"+"\n"+"Trevor Tomlin ---- The real brains");
	
	jta.setEditable(false);
	j.add(jta);
        f.add(j);
        f.setLocationRelativeTo(null);
        f.setVisible(true);        
    }
}
