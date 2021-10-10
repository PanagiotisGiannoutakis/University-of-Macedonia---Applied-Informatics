#! /bin/bash

# This script download a file from remote computer and uncompress it in some local directory.

#Some variables.
# $1 is the first argument and should have the destination folder of the backup file.
LOCALDIRECTORY=$1
# $2 is the second argument and should have the ip address of the remote computer.
REMOTEIP=$2
# $3 is the third argument and should have the path of the folder that backup file exists.
REMOTEDIRECTORY=$3
# $4 is the fourth argument and should have the name of the backup file.
NAMETAR=$4

mkdir $LOCALDIRECTORY
scp root@$REMOTEIP:$REMOTEDIRECTORY/$NAMETAR $LOCALDIRECTORY
tar -C $LOCALDIRECTORY -xvf $LOCALDIRECTORY/$NAMETAR

