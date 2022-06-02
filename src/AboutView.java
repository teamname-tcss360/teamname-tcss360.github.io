package src;

import javax.swing.*;
import java.awt.*;

public class AboutView {

    public AboutView(){
        viewBuilder();
    }
    void viewBuilder(){
        JFrame f = new JFrame();
        f.setTitle("About the builders");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setSize(400, 400);

        JPanel j = new JPanel();
        j.setLayout(new GridLayout(0, 1));
        j.setSize(400, 200);
        JLabel verison = new JLabel("Version - " + VersionControl.getVersion());
        JLabel user = new JLabel("This application is registered to: Bob Keener");
        JLabel providedBy = new JLabel(
                "<html>This application provided by:<br><br>Michael Theisen ------ Brother in mead<br>Jasharn Thiara ------- Covid carrier<br>Trevor Tomlin -------- The real brains<br>Patrick Tibbals ------- Sometimes mildly inteligent<br></html>");

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
