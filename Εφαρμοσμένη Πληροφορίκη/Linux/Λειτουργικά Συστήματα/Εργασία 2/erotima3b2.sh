#! /bin/bash

# This script download a file from remote computer and uncompress it in some local directory.

#Some variables.

echo "Give the destination folder of the backup file: "
read LOCALDIRECTORY

echo "Give the ip address of the remote computer: "
read REMOTEIP

echo "Give the path of the folder that backup file exists: "
read REMOTEDIRECTORY

echo "Give the name of the backup file: "
read NAMETAR

mkdir $LOCALDIRECTORY
scp root@$REMOTEIP:$REMOTEDIRECTORY/$NAMETAR $LOCALDIRECTORY
tar -C $LOCALDIRECTORY -xvf $LOCALDIRECTORY/$NAMETAR

