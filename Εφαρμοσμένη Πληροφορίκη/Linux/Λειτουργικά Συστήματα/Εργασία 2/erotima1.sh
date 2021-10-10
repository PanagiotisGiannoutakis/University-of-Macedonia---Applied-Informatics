#! /bin/bash

# This scripts gives some infromations about user, directories, cpu, memory and online users.

echo "Name of the user is: $USER"
echo

echo "Home directory is: $HOME"
echo

echo "Current directory is: $PWD"
echo

echo "The date is: `date +%Y-%m-%d`."
echo

echo "The time is: `date +%H:%M:%S`."
echo

echo "Some informations about cpu: "
lscpu | grep -E "(Architecture|CPU|Model name)"
echo

echo "Some informations about memory: "
cat /proc/meminfo | grep -E "(MemTotal|MemFree|Buffers|Cached|SwapTotal|SwapFree)"
echo

echo "Some info about Operation System: "
lsb_release -a
echo

