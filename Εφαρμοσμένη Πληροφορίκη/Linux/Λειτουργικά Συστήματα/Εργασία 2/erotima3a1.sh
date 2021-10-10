#! /bin/bash

# This script make a backup of a file and send it to another computer.

# Some variables.
# $1 is the first argument and should have the destination folder of backup file.
BACKUPFILE=$1
# $2 is the second argument and should have the name of the backup file.
BACKUPNAME=$2
# $3 is the third argument and should have the ip address of the remote computer.
IPADRRESS=$3
# $4 is the fourth argument and should have the destination folder of the remote computer, that backup file storage.
REMOTEDIRECTORY=$4

tar -cvzf $BACKUPNAME $BACKUPFILE
scp $BACKUPNAME root@$IPADRRESS:$REMOTEDIRECTORY
