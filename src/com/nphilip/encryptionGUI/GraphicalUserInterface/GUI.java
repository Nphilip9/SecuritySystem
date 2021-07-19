package com.nphilip.encryptionGUI.GraphicalUserInterface;

import com.nphilip.encryptionGUI.Constants.ConstantVariables;
import com.nphilip.encryptionGUI.Main;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class GUI {

    public static void window() {
        JFrame frame = new JFrame("encryption");
        JButton submitButton = new JButton("Submit");
        JButton setPath = new JButton("Set Path");
        JLabel pathText = new JLabel("Path");
        JTextField pathTextField = new JTextField(20);
        JLabel passwordText = new JLabel("Password");
        JTextField passwordTextField = new JTextField(20);
        JCheckBox securityMode1 = new JCheckBox("Encrypt");
        JCheckBox securityMode2 = new JCheckBox("Decrypt");
        JPanel mainPanel = new JPanel();

        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // "SetPath" Button settings
        setPath.setBounds(300, 20, setPath.getPreferredSize().width+20, setPath.getPreferredSize().height);

        // "submitButton" Button settings
        submitButton.setBounds(180, 120, submitButton.getPreferredSize().width, submitButton.getPreferredSize().height);

        // "pathText" Label settings
        pathText.setBounds(43, 2, pathText.getPreferredSize().width, pathText.getPreferredSize().height);

        // "passwordText" Label settings
        passwordText.setBounds(43, 45, passwordText.getPreferredSize().width, passwordText.getPreferredSize().height);

        // "pathTextField" TextField settings
        pathTextField.setBounds((frame.getHeight() / 2) - setPath.getWidth() + 20,20, pathTextField.getPreferredSize().width, pathTextField.getPreferredSize().height);

        // "passwordTextField" TextField settings
        passwordTextField.setBounds((frame.getHeight() / 2) - setPath.getWidth() + 20, 64, passwordTextField.getPreferredSize().width, passwordTextField.getPreferredSize().height);

        // "securityMode1" CheckBox settings
        securityMode1.setBounds(130,85, securityMode1.getPreferredSize().width, securityMode1.getPreferredSize().height);

        // "securityMode2" CheckBox settings
        securityMode2.setBounds(220, 85, securityMode2.getPreferredSize().width, securityMode2.getPreferredSize().height);

        setPath.addActionListener(e -> {
            JFileChooser folder = new JFileChooser();
            folder.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            folder.showOpenDialog(null);
            folder.setVisible(true);
            if(folder.getSelectedFile() != null) {
                pathTextField.setText(folder.getSelectedFile().toString());
            }
        });

        submitButton.addActionListener(e -> {
            if(pathTextField.getText().length() > 0) {
                if (new File(pathTextField.getText()).exists()) {
                    if (passwordTextField.getText().length() > 0) {
                        boolean badCharsCheck = false;
                        for (char ch : passwordTextField.getText().toCharArray()) {
                            if (ConstantVariables.BAD_CHARACTERS.contains(String.valueOf(ch))) {
                                badCharsCheck = true;
                                break;
                            }
                        }
                        if (!badCharsCheck) {
                            if (securityMode1.isSelected()) {
                                try {
                                    Main.start(pathTextField.getText(), ConstantVariables.SECURITY_MODE_ENCRYPTION, passwordText.getText(), frame);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            } else if (securityMode2.isSelected()) {
                                try {
                                    Main.start(pathTextField.getText(), ConstantVariables.SECURITY_MODE_DECRYPTION, passwordText.getText(), frame);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame, ConstantVariables.ERROR_MESSAGES_GUI[0], ConstantVariables.JOPTIONPANE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, ConstantVariables.ERROR_MESSAGES_GUI[1], ConstantVariables.JOPTIONPANE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, ConstantVariables.ERROR_MESSAGES_GUI[2], ConstantVariables.JOPTIONPANE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, ConstantVariables.ERROR_MESSAGES_GUI[3], ConstantVariables.JOPTIONPANE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, ConstantVariables.ERROR_MESSAGES_GUI[4], ConstantVariables.JOPTIONPANE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        });

        securityMode1.addActionListener(e -> {
            if(securityMode1.isSelected() && securityMode2.isSelected()) {
                securityMode2.setSelected(false);
            }
        });

        securityMode2.addActionListener(e -> {
            if(securityMode1.isSelected() && securityMode2.isSelected()) {
                securityMode1.setSelected(false);
            }
        });

        mainPanel.setLayout(null);
        mainPanel.add(pathText);
        mainPanel.add(pathTextField);
        mainPanel.add(passwordText);
        mainPanel.add(passwordTextField);
        mainPanel.add(setPath);
        mainPanel.add(submitButton);
        mainPanel.add(securityMode1);
        mainPanel.add(securityMode2);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
