package com.carrental.view;

import com.carrental.dao.*;
import com.carrental.model.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {
    private final User currentUser;
    private JTabbedPane tabbedPane;

    public AdminDashboard(User user) {
        this.currentUser = user;
        initComponents();
        setupWindow();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Users", new UserManagementPanel());
        tabbedPane.addTab("Cars", new CarManagementPanel());
        tabbedPane.addTab("Rentals", new RentalManagementPanel());
        add(tabbedPane, BorderLayout.CENTER);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        logoutItem.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        fileMenu.add(logoutItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void setupWindow() {
        setTitle("Admin Dashboard - Welcome " + currentUser.getFullName());
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Inner classes for each tab panel
    class UserManagementPanel extends JPanel {
        private final JTable userTable;
        private final DefaultTableModel tableModel;

        public UserManagementPanel() {
            setLayout(new BorderLayout());
            
            // Table setup
            String[] columns = {"ID", "Username", "Full Name", "Email", "Phone", "Admin"};
            tableModel = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make table non-editable
                }
            };
            userTable = new JTable(tableModel);
            
            // Button panel
            JPanel buttonPanel = new JPanel();
            JButton refreshBtn = new JButton("Refresh");
            refreshBtn.addActionListener(e -> refreshUsers());
            
            JButton addBtn = new JButton("Add User");
            addBtn.addActionListener(e -> showAddUserDialog());
            
            buttonPanel.add(refreshBtn);
            buttonPanel.add(addBtn);
            
            add(new JScrollPane(userTable), BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
            
            refreshUsers();
        }

        private void refreshUsers() {
            tableModel.setRowCount(0); // Clear table
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.getAllUsers();
            
            for (User user : users) {
                tableModel.addRow(new Object[]{
                    user.getUserId(),
                    user.getUsername(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.isAdmin() ? "Yes" : "No"
                });
            }
        }

        private void showAddUserDialog() {
            JDialog dialog = new JDialog(AdminDashboard.this, "Add New User", true);
            dialog.setLayout(new BorderLayout());
            dialog.setSize(400, 350);
            
            // Form panel
            JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JTextField fullNameField = new JTextField();
            JTextField emailField = new JTextField();
            JTextField phoneField = new JTextField();
            JCheckBox adminCheckbox = new JCheckBox("Is Admin");
            
            formPanel.add(new JLabel("Username:"));
            formPanel.add(usernameField);
            formPanel.add(new JLabel("Password:"));
            formPanel.add(passwordField);
            formPanel.add(new JLabel("Full Name:"));
            formPanel.add(fullNameField);
            formPanel.add(new JLabel("Email:"));
            formPanel.add(emailField);
            formPanel.add(new JLabel("Phone:"));
            formPanel.add(phoneField);
            formPanel.add(new JLabel("Admin Privileges:"));
            formPanel.add(adminCheckbox);
            
            // Button panel
            JPanel buttonPanel = new JPanel();
            JButton saveBtn = new JButton("Save");
            saveBtn.addActionListener(e -> {
                // Validation and save logic
                User newUser = new User(
                    usernameField.getText(),
                    new String(passwordField.getPassword()),
                    fullNameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    adminCheckbox.isSelected()
                );
                
                UserDAO userDAO = new UserDAO();
                if (userDAO.addUser(newUser)) {
                    JOptionPane.showMessageDialog(dialog, "User added successfully!");
                    refreshUsers();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Failed to add user", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            
            JButton cancelBtn = new JButton("Cancel");
            cancelBtn.addActionListener(e -> dialog.dispose());
            
            buttonPanel.add(saveBtn);
            buttonPanel.add(cancelBtn);
            
            dialog.add(formPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setVisible(true);
        }
    }

    class CarManagementPanel extends JPanel {
        // Similar implementation for car management
        // ...
    }

    class RentalManagementPanel extends JPanel {
        // Similar implementation for rental management
        // ...
    }
}