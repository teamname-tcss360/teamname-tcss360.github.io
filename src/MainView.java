package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;


class MainView{

    void guiBuilder() {
        JFrame frame = new JFrame();
        frame.setTitle("File Organizer - " + VersionControl.getVersion());

        try {
            LogInScreen ls = LogInScreen.getInstance();
            ls.setFrame(frame);
            ls.setup();
        } catch (src.ExportException | IOException ex){
            ex.printStackTrace();
        }


        // Menu's
        JMenuBar menuBar = new JMenuBar();

        ImageIcon iconAbout = new ImageIcon("src/resources/about.png");
        Image imageAbout = iconAbout.getImage();
        Image newImgAbout = imageAbout.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconAbout = new ImageIcon(newImgAbout);

       
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About", iconAbout);

        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new src.AboutView();
            }
        });


        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        frame.setSize(1000, 500);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

