#! /bin/bash

# This script download a file from remote computer and uncompress it in some local directory.

#Some variables.
LOCALDIRECTORY=~/unTarFiles
REMOTEIP=192.168.1.3
REMOTEDIRECTORY=/home/virtualguy/scripts
NAMETAR=tarFile.tar.gz


mkdir $LOCALDIRECTORY
scp root@$REMOTEIP:$REMOTEDIRECTORY/$NAMETAR $LOCALDIRECTORY
tar -C $LOCALDIRECTORY -xvf $LOCALDIRECTORY/$NAMETAR

