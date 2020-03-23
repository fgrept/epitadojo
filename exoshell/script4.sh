#!/bin/bash
#set -x
wc -l /etc/passwd | cut -d ' ' -f 1
for line in $(cat /etc/passwd | cut -d ':' -f 1,7); do
	echo $line
done
