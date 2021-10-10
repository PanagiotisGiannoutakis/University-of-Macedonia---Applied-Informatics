#! /bin/bash

# This script make a backup of a file and send it to another computer.

# Some variables.

echo "Give the destination folder of backup file: "
read BACKUPFILE

echo "Give the name of the backup file: "
read BACKUPNAME

echo "Give the ip address of the remote computer: "
read IPADRRESS

echo "Give the destination folder of the remote computer, that backup file storage: "
read REMOTEDIRECTORY

tar -cvzf $BACKUPNAME $BACKUPFILE
scp $BACKUPNAME root@$IPADRRESS:$REMOTEDIRECTORY
