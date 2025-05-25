package com.carrental.view;

import com.carrental.dao.UserDAO;
import com.carrental.model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * User registration window
 */
public class RegistrationFrame extends JFrame {
    private JTextField txtUsername, txtFullName, txtEmail, txtPhone;
    private JPasswordField txtPassword;
    private JButton btnRegister;

    public RegistrationFrame() {
        initComponents();
        setupWindow();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        txtUsername = new JTextField(20);
        mainPanel.add(txtUsername, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        txtPassword = new JPasswordField(20);
        mainPanel.add(txtPassword, gbc);

        // Full Name
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Full Name:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        txtFullName = new JTextField(20);
        mainPanel.add(txtFullName, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        txtEmail = new JTextField(20);
        mainPanel.add(txtEmail, gbc);

        // Phone
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Phone:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        txtPhone = new JTextField(20);
        mainPanel.add(txtPhone, gbc);

        // Register Button
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(this::handleRegister);
        mainPanel.add(btnRegister, gbc);

        add(mainPanel);
    }

    private void setupWindow() {
        setTitle("Register New User");
        setSize(400, 350);
        setLocationRelativeTo(null);
    }

    private void handleRegister(ActionEvent e) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String fullName = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() || 
            email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill all fields",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(username, password, fullName, email, phone, false);
        UserDAO userDAO = new UserDAO();

        if (userDAO.addUser(user)) {
            JOptionPane.showMessageDialog(this,
                "Registration successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Registration failed. Username or email may already exist.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}