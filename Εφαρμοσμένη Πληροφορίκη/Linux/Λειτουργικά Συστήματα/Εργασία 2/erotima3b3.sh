#! /bin/bash

# This script download a file from remote computer and uncompress it in some local directory.

#Some variables.

LOCALDIRECTORY=$(zenity --entry --text "Give the destination folder of the backup file: ");

REMOTEIP=$(zenity --entry --text "Give the ip address of the remote computer: ");

REMOTEDIRECTORY=$(zenity --entry --text "Give the path of the folder that backup file exists: ");

NAMETAR=$(zenity --entry --text "Give the name of the backup file: ");

mkdir $LOCALDIRECTORY
scp root@$REMOTEIP:$REMOTEDIRECTORY/$NAMETAR $LOCALDIRECTORY
tar -C $LOCALDIRECTORY -xvf $LOCALDIRECTORY/$NAMETAR

