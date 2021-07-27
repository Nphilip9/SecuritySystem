package com.nphilip.encryptionGUI;

import com.nphilip.encryptionGUI.Constants.ConstantVariables;
import com.nphilip.encryptionGUI.GraphicalUserInterface.GUI;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        GUI.window(); /* Starts the Application window */
    }

    public static void start(String path, int securityMode, String password, JFrame frame) throws IOException {
        if(!new File(path).isFile()) {
            Stream<Path> walk = Files.walk(Paths.get(path));
            List<String> folderNamesList = walk.map(Path::toString).collect(Collectors.toList());

            String[] pathSplitted = path.split("/");
            String encryptedDir = "/home/nphilip9/Desktop/" + pathSplitted[pathSplitted.length - 1] + "-enc";

            boolean createEncryptionDir = new File(encryptedDir).mkdir();
            boolean createMainDir = new File(encryptedDir + "/" + pathSplitted[pathSplitted.length - 1]).mkdir();

            if (createEncryptionDir && createMainDir) {
                System.out.println("yes");
                for (int i = 1; i <= folderNamesList.size() - 1; i++) {
                    System.out.println("yes");
                    if(!new File(folderNamesList.get(i)).isFile()) {
                        String[] allPathsSplit = folderNamesList.get(i).split("/");
                        String dirPath = encryptedDir + "/" + allPathsSplit[pathSplitted.length + 1];
                        boolean createDir = new File(dirPath).mkdir();
                        System.out.println(Arrays.toString(allPathsSplit) + encryptedDir);
                        if (createDir) {
                            System.out.println("Created");
                        }
                    }
                }
            }
        }

        if(new File(path).canRead()) {
            if (securityMode == ConstantVariables.SECURITY_MODE_ENCRYPTION) {
                callPythonFile(ConstantVariables.SECURITY_MODE_ENCRYPTION, path, password);
            } else if (securityMode == ConstantVariables.SECURITY_MODE_DECRYPTION) {
                callPythonFile(ConstantVariables.SECURITY_MODE_DECRYPTION, path, password);
            } else {
                JOptionPane.showMessageDialog(frame, ConstantVariables.GENERAL_ERRORS[0], ConstantVariables.JOPTIONPANE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, ConstantVariables.GENERAL_ERRORS[1], ConstantVariables.JOPTIONPANE_ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void callPythonFile(int securityMode, String path, String password) {
        Thread pythonThread = new Thread(() -> {
            try {
                Process callPythonFile = Runtime.getRuntime()
                        .exec(ConstantVariables.PYTHON_SECURITY_FILE_COMMAND + " " + securityMode + " " + path + " " + password);

                // Output from Process "callPythonFile"
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(callPythonFile.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(callPythonFile.getErrorStream()));

                // Output from "callPythonFile" Process stored in "output" String-variable
                String output;

                // Read the output from the command
                while ((output = stdInput.readLine()) != null) {
                    System.out.println(output);
                }

                // Read any errors from the attempted command
                while ((output = stdError.readLine()) != null) {
                    System.out.println(output);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pythonThread.start();
    }
}
