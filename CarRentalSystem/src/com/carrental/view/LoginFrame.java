package com.carrental.view;

import com.carrental.dao.UserDAO;
import com.carrental.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Login window for the Car Rental System.
 * Handles user authentication and role-based redirection.
 */
public class LoginFrame extends JFrame {
    
    // UI Components
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    /**
     * Constructs the login window
     */
    public LoginFrame() {
        initComponents();
        setupWindow();
    }

    /**
     * Initializes and positions all UI components
     */
    private void initComponents() {
        // Main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username field
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtUsername = new JTextField(20);
        mainPanel.add(txtUsername, gbc);

        // Password field
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtPassword = new JPasswordField(20);
        mainPanel.add(txtPassword, gbc);

        // Login button
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2; // Span 2 columns
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this::handleLogin);
        mainPanel.add(btnLogin, gbc);

        // Register button
        gbc.gridx = 0; gbc.gridy = 3;
        btnRegister = new JButton("Register New User");
        btnRegister.addActionListener(e -> new RegistrationFrame().setVisible(true));
        mainPanel.add(btnRegister, gbc);

        add(mainPanel);
    }

    /**
     * Configures window properties
     */
    private void setupWindow() {
        setTitle("Car Rental System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
    }

    /**
     * Handles login button click event
     * @param e The action event
     */
    private void handleLogin(ActionEvent e) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        // Input validation
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password");
            return;
        }

        // Authenticate user
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            showError("Invalid username or password");
            return;
        }

        // Redirect based on role
        dispose(); // Close login window
        if (user.isAdmin()) {
            new AdminDashboard(user).setVisible(true);
        } else {
            new UserDashboard(user).setVisible(true);
        }
    }

    /**
     * Displays an error message dialog
     * @param message The error message to display
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, 
            message, 
            "Login Error", 
            JOptionPane.ERROR_MESSAGE);
    }
        public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread-safe GUI operations
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel for native appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                
                // Create and display the login window
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
                
            } catch (Exception ex) {
                // Handle any initialization errors
                JOptionPane.showMessageDialog(
                    null,
                    "Failed to initialize application: " + ex.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE
                );
                System.err.println("Application initialization failed:");
                ex.printStackTrace();
            }
        });
    }
}

