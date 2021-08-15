package com.nphilip.encryptionGUI.Constants;

public class ConstantVariables {

    // Security Modes
    public static final int SECURITY_MODE_ENCRYPTION = 1;
    public static final int SECURITY_MODE_DECRYPTION = 2;

    // Python security File Path
    public static final String PYTHON_SECURITY_FILE_PATH = "src/com/nphilip/encryptionGUI/Security/Main.py";

    // Error Messages List in GUI
    public static final String[] ERROR_MESSAGES_GUI = {
            "Choose security mode...",              /* Security Mode not selected */
            "Password contains bad characters",     /* Base64 can't encode/decode bad chars */
            "Enter Password",                       /* Password not entered */
            "Path incorrect",                       /* FileNotFoundException occured */
            "Enter path..."                         /* No Path entered */
    };

    // General errors in Software
    public static final String[] GENERAL_ERRORS = {
            "Security mode incorrect",              /* Unexpected error in security mode selection */
            "Files are not readable"                /* Cannot read files */
    };

    // Command to execute SecurityManager.py file
    public static final String PYTHON_SECURITY_FILE_COMMAND = "python " + PYTHON_SECURITY_FILE_PATH;

    // Bad Characters that Base64 can't encode/decode
    public static final String BAD_CHARACTERS = "!\"§$%&/()={}[]*#'~";

    // JOptionPane message dialog Error title
    public static final String JOPTIONPANE_ERROR_TITLE = "ERROR";
}
