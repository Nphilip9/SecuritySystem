import os
import sys
import SecurityManager

def main():
    # Security Mode
    securityMode = sys.argv[1]

    # Dir/File Path
    path = sys.argv[2]

    # Security Password
    password = sys.argv[3]

    folderList, fileList = SecurityManager.listPathElements(path)
    path = path.replace("\\", "/")

    folderSecurityModeDescriptor = ""

    if securityMode == "1":
        folderSecurityModeDescriptor = "-enc"
        os.mkdir(path + folderSecurityModeDescriptor)
    elif securityMode == "2":
        folderSecurityModeDescriptor = "-dec"
        os.mkdir(path + folderSecurityModeDescriptor)
    else:
        quit(-1)

    for folder in folderList:
        pathToAppend = folder.replace("\\", "/").replace(path, "")
        mainPathLength = len(path.split("/")) - 1
        outputPath = path
        outputPath = outputPath.replace(outputPath.split("/")[mainPathLength], outputPath.split("/")[mainPathLength] + folderSecurityModeDescriptor).replace("\\", "/") + pathToAppend
        os.mkdir(outputPath)
        print(outputPath)

    for file in fileList:
        pathToAppend = file.replace("\\", "/").replace(path, "")
        mainPathLength = len(path.split("/")) - 1
        outputPath = path
        outputPath = outputPath.replace(outputPath.split("/")[mainPathLength], outputPath.split("/")[mainPathLength] + folderSecurityModeDescriptor).replace("\\", "/") + pathToAppend
        SecurityManager.startSecurityProcess(password, path, file, int(securityMode))
        print(outputPath)

if __name__ == '__main__':
    main()