#!/bin/bash
# This script can clean up files that end up in *.old.
USAGE="Usage: $0 dir1 dir2 dir3 ... dirN"
if [ "$#" == "0" ]; then
        echo "$USAGE"
        exit 1
fi
while (( "$#" )); do
  if [[ "$(ls $1)" == "" ]]; then 
        echo "Empty directory $1, nothing to be done."
  else 
        find $1 -name "*.old" -exec rm -i {} \;
  fi
  shift
done

