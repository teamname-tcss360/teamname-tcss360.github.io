package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {

    private src.FileView fileView;
    public MenuGUI(src.FileView fv) {
        fileView = fv;
        buildMenuBar();
    }

    JMenuBar buildMenuBar(){
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
                fileView.getFrame().dispose();
                MainView m = new MainView();
                m.guiBuilder();
            }
        });

        JMenu editMenu = new JMenu("Edit");

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About", iconAbout);

        aboutMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new src.AboutView();

            }
        });

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
        return menuBar;
    }



}

