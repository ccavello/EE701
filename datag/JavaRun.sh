#!/bin/sh

killall java

javac cdcServerThread.java
echo "cdcServerThread compliled"

javac cdcServer.java
echo "cdcServer compliled"

javac cdcClient.java
echo "cdcClient compliled"

java cdcServer &

sleep 1s

#java Client
echo "TYPE java cdcClient HOST PORT#"
