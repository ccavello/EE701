#!/bin/sh

killall java

javac cdcClient.java
echo "cdcClient compliled"

javac cdcServerThread.java
echo "cdcServerThread compliled"

javac cdcServer.java
echo "cdcServer compliled"


java cdcServer &

sleep 1s

#java Client
echo "TYPE java cdcClient cdcpc PORT#"
