package com.carrental.view;

import com.carrental.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * User dashboard for customers
 */
public class UserDashboard extends JFrame {
    private final User currentUser;
    private JTabbedPane tabbedPane;

    public UserDashboard(User user) {
        this.currentUser = user;
        initComponents();
        setupWindow();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        
        // Add tabs
        tabbedPane.addTab("Available Cars", new AvailableCarsPanel());
        tabbedPane.addTab("My Rentals", new MyRentalsPanel());
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(this::handleLogout);
        fileMenu.add(logoutItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void setupWindow() {
        setTitle("User Dashboard - Welcome " + currentUser.getFullName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void handleLogout(ActionEvent e) {
        dispose();
        new LoginFrame().setVisible(true);
    }
}

class AvailableCarsPanel extends JPanel {
    public AvailableCarsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Available Cars Panel - To be implemented", JLabel.CENTER), BorderLayout.CENTER);
    }
}

class MyRentalsPanel extends JPanel {
    public MyRentalsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("My Rentals Panel - To be implemented", JLabel.CENTER), BorderLayout.CENTER);
    }
}