#!/bin/sh

killall java

javac ServerThread.java
echo "ServerThread compliled"

javac Server.java
echo "Server compliled"

javac Client.java
echo "Client compliled"

java Server &

sleep 1s

java Client
