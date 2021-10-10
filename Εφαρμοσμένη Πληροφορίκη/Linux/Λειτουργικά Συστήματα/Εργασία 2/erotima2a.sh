#! /bin/bash

# This script make a backup of a file and send it to another computer.

# Some variables.
BACKUPFILE=~/scripts
BACKUPNAME=tarFile.tar.gz
IPADRRESS=192.168.0.11
REMOTEDIRECTORY=/home/virtualguy/scripts

tar -cvzf $BACKUPNAME $BACKUPFILE
scp $BACKUPNAME root@$IPADRRESS:$REMOTEDIRECTORY
