import sys
import os
from cryptography.fernet import Fernet

# Security Mode
securityMode = sys.argv[1]

# Dir/File Path
path = sys.argv[2]

# Security Password
password = sys.argv[3]

def listPathElements():
    fileList = []
    dirList = []

    if os.path.isdir(path):
        for root, dirs, files in os.walk(path):
            for file in files:
                fileList.append(os.path.join(root, file))

            for directory in dirs:
                dirList.append(os.path.join(root, directory))
    return fileList, dirList

def encryption(passwd):
    if os.path.isfile(path):
        f = Fernet(passwd)

        with open(path, "rb") as file:
            # read all file data
            file_data = file.read()

        # encrypt data
        encrypted_data = f.encrypt(file_data)

        with open(path, "wb") as file:
            file.write(encrypted_data)
    else:
        print("Not a file")

def decryption(passwd):
    if os.path.isfile(path):
        f = Fernet(passwd)

        with open(path, "rb") as file:
            # read the encrypted data
            encrypted_data = file.read()

        # decrypt data
        decrypted_data = f.decrypt(encrypted_data)

        # write the original file
        with open(path, "wb") as file:
            file.write(decrypted_data)
    else:
        print("Not a file")

# Generates a password (with given password) based on the Base64 key requirements
def generatePassword(finalPassword):
    for i in range(0, 44 - len(finalPassword) - 1):
        finalPassword += str(0)
    return finalPassword

def main():
    passwd = bytes(generatePassword(password) + "=", "ascii")

    if securityMode == "1":
        encryption(passwd)
    else:
        decryption(passwd)

main()
