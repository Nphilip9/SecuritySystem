import sys
import os
from cryptography.fernet import Fernet

def listPathElements(path):
    """
    Lists the content of a path
    :param path: str
    :return: dirList, fileList
    """
    fileList = []
    dirList = []

    if os.path.isdir(path):
        for root, dirs, files in os.walk(path):
            for file in files:
                fileList.append(os.path.join(root, file))

            for directory in dirs:
                dirList.append(os.path.join(root, directory))
    return dirList, fileList

def encryption(passwd, path, outputFilePath):
    """
    Encrypts a file
    :param outputFilePath: str
    :param path: str
    :param passwd: str
    :return: None
    """
    f = Fernet(passwd)

    with open(path, "rb") as file:
        # read all file data
        file_data = file.read()

    # encrypt data
    encrypted_data = f.encrypt(file_data)

    with open(outputFilePath, "wb") as file:
        file.write(encrypted_data)

def decryption(passwd, path, outputFilePath):
    """
    Decrypts a file
    :param outputFilePath: str
    :param path: str
    :param passwd: str
    :return: None
    """
    f = Fernet(passwd)

    with open(path, "rb") as file:
        # read the encrypted data
        encrypted_data = file.read()

    # decrypt data
    decrypted_data = f.decrypt(encrypted_data)

    # write the original file
    with open(outputFilePath, "wb") as file:
        file.write(decrypted_data)

def generatePassword(passwd):
    """
    Generates a password (with given password) based on the Base64 key requirements
    :param passwd: str
    :return: None
    """
    for i in range(0, 44 - len(passwd) - 1):
        passwd += str(0)
    return passwd

def startSecurityProcess(passwd, path, outputPath, securityMode):
    """
    Starts the Security Process
    :param passwd: str
    :param path: str
    :param outputPath: str
    :param securityMode: int
    :return: None
    """
    if securityMode == 1:
        encryption(generatePassword(passwd) + "=", path, outputPath)
    elif securityMode == 2:
        decryption(generatePassword(passwd) + "=", path, outputPath)
    else:
        quit(-1)