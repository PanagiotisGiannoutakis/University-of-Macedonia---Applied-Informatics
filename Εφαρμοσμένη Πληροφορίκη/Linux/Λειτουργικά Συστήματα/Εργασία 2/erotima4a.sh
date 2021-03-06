#!/bin/bash

# This script does a very simple test for checking disk space.

# Με την πρώτη σωλήνωση, εμφανίζονται όλες οι πληροφορίες για τον σκληρό δίσκο του συστήματος. Με την δεύτερη σωλήνωση παίρνουμε την πέμπτη στήλη από τα αποτελέσματα που έβγαλε η προηγούμενη εντολή. Στην συνέχεια κρατάμε μόνο τις γραμμές που περιέχουν το αλφαριθμητικό % και στην επόμενη σωλήνωση κρατάμε όσες γραμμές δεν έχουν το αλφαριθμητικό "Use". Έπειτα εμφανίζουμε τα αποτελέσματα σε φθίνουσα σειρά και μετά επιλέγουμε το τελευταίο στοιχείο από τα αποτελέσματα των μέχρι τώρα συνδιασμένων εντολών. Τέλος αφού έχουμε πάρει τον μεγαλύτερο αριθμό, που στην ουσία είναι το διαμέρισμα με την πιο γεμάτη χωρητικότητα, αφαιρούμε τον χαρακτήρα του ποσοστού για να μείνει καθαρός ο αριθμός.
space=`df ­-h | awk '{print $5}' | grep % | grep ­-v Use | sort ­-n | tail ­-1 | cut -­d "%" -f1 ­-`

# 80 είναι το νούμερο που επιλέγει ο χρήστης ότι είναι το όριο για να θεωρηθεί ένας σκληρός δίσκος γεμάτος.
alertvalue="80"
if [ "$space" ­ge "$alertvalue" ]; then

# Αν το νούμερο της πρώτης εντολής είναι μεγαλύτερο ή ίσο με το όριο που τέθηκε προηγουμένως, τότε στέλνει ένα email με προειδοποίηση.
  echo "At least one of my disks is nearly full!" | mail ­s "daily diskcheck" root
else

# Αλλιώς, στέλνει ένα email καθησυχασμού.
  echo "Disk space normal" | mail ­s "daily diskcheck" root
fi
