#!/bin/bash
#set -x
for VILLE in {Paris,Londres,Montrouge,Kremlin-Bicêtre}; do
	echo -n "$VILLE "
        echo -n $VILLE | wc -m
done
