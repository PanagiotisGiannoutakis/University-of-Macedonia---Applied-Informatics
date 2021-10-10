#!/bin/bash 

# This script can clean up files that end up in ~.old. 

# Αποθηκεύει στην μεταβλητή το αλφαριθμητικό παρακάτω που περιέχει το όνομα του σεναρίου μαζί με τα ορίσματα του.
USAGE="Usage: $0 dir1 dir2 dir3 ... dirN" 

# Αν δεν υπήρξε κανένα όρισμα, τότε δίνει λανθασμένη έξοδο και εμφανίζει το όνομα του σεναρίου.
if [ "$#" == "0" ]; then 
        echo "$USAGE" 
        exit 1 
fi 

# Όσο υπάρχουν ορίσματα για επεξεργασία (με την δομή επανάληψης, κάθε όρισμα επεξεργάζεται σειριακά), ελέγχει αν είναι κένο και τότε εμφανίζει ενημερωτικό μήνυμα. Ειδάλως, ψάχνει να βρει αρχείο με κατάληξη .old και το διαγράφει.
while (( "$#" )); do 
  if [[ "$(ls $1)" == "" ]]; then 
        echo "Empty directory $1, nothing to be done." 
  else 
        find $1 ­-type f -­a ­-name '*.old' -­exec rm ­-f {} \; 
  fi 
  shift 
done
