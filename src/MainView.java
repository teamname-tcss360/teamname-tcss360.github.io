package src;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;


class MainView implements ActionListener {

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

        ImageIcon iconNew = new ImageIcon("src/resources/new.png");
        Image imageNew = iconNew.getImage();
        Image newImgNew = imageNew.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconNew = new ImageIcon(newImgNew);

        ImageIcon iconOpen = new ImageIcon("src/resources/open.png");
        Image imageOpen = iconOpen.getImage();
        Image newImgOpen = imageOpen.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconOpen = new ImageIcon(newImgOpen);

        ImageIcon iconSave = new ImageIcon("src/resources/save.png");
        Image imageSave = iconSave.getImage();
        Image newImgSave = imageSave.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconSave = new ImageIcon(newImgSave);

        ImageIcon iconExit = new ImageIcon("src/resources/exit.png");
        Image imageExit = iconExit.getImage();
        Image newImgExit = imageExit.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconExit = new ImageIcon(newImgExit);

        ImageIcon iconAbout = new ImageIcon("src/resources/about.png");
        Image imageAbout = iconAbout.getImage();
        Image newImgAbout = imageAbout.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        iconAbout = new ImageIcon(newImgAbout);

        JMenu fileMenu = new JMenu("File");
        JMenu importMenu = new JMenu("Import");
        JMenu exportMenu = new JMenu("Export");

        JMenuItem importMenuItem = new JMenuItem("Import from: ");

        JMenuItem exportMenuItem = new JMenuItem("Export from: ");

        importMenu.add(importMenuItem);
        exportMenu.add(exportMenuItem);

        JMenuItem newMenuItem = new JMenuItem("New", iconNew);
        JMenuItem openMenuItem = new JMenuItem("Open", iconOpen);
        JMenuItem saveMenuItem = new JMenuItem("Save", iconSave);
        JMenuItem exitMenuItem = new JMenuItem("Sign Out", iconExit);
        exitMenuItem.setToolTipText("Sign Out");

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                guiBuilder();
            }
        });

        JMenu editMenu = new JMenu("Edit");

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About", iconAbout);

        aboutMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(importMenu);
        fileMenu.add(exportMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // editMenu.add();
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        frame.setSize(1000, 500);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
