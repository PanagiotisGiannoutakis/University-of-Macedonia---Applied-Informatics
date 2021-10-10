#! /bin/bash

# This script make a backup of a file and send it to another computer.

# Some variables.

BACKUPFILE=$(zenity --entry --text "Give the destination folder of backup file: ");

BACKUPNAME=$(zenity --entry --text "Give the name of the backup file: ");

IPADRRESS=$(zenity --entry --text "Give the ip address of the remote computer: ");

REMOTEDIRECTORY=$(zenity --entry --text "Give the destination folder of the remote computer, that backup file storage: ");

tar -cvzf $BACKUPNAME $BACKUPFILE
scp $BACKUPNAME root@$IPADRRESS:$REMOTEDIRECTORY
