#!/bin/bash
#set -x
wc -l /etc/passwd | cut -d ' ' -f 1
cat /etc/passwd | cut -d ':' -f 1,7
