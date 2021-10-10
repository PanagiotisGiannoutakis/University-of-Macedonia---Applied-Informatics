#!/bin/bash

# This script gives information about a file.

FILENAME="$1"
echo "Properties for $FILENAME:"

# Έλεγχος αν το πρώτο όρισμα που δώθηκε είναι αρχείο.
if [ ­-f $FILENAME ]; then

# Εμφανίζει την πέμπτη στήλη από τα αποτελέσματα της εντολής ls -lh, δηλαδή το μέγεθος του αρχείου.
  echo "Size is $(ls ­-lh $FILENAME | awk '{ print $5 }')"

# Εμφανίζει χαρακτηριστικά του αρχειου αφού πρώτα έχει διαγράψει τον χαρακτήρα :
  echo "Type is $(file $FILENAME | cut -­d":" ­-f2 -­)"

# Εμφανίζει τον αριθμό inode του αρχείου.
  echo "Inode number is $(ls -­i $FILENAME | cut -d " " -­f1 ­-)"

  echo "$(df -­h $FILENAME | grep -­v Mounted | awk '{ print "On",$1", which is mounted as the",$6,"partition."}')"
else
  echo "File does not exist."
fi
