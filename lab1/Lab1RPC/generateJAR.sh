#!/bin/bash
# criar o diretório build/ e compilar tudo
# e criar o diretório dist/ e gerar os .jar

mkdir -p build

javac -d build/ src/shared/*.java src/server/*.java
javac -d build/ src/shared/*.java src/client/**/*.java

cd build

echo "Main-Class: server.MultiThreadedServer" > m-servidor.txt
echo "Main-Class: client.GUI.ClientController" > m-client.txt

cd ..
mkdir -p dist

jar cvfm dist/Server.jar build/m-servidor.txt build/shared/*.class build/server/*.class
jar cvfm dist/Client.jar build/m-client.txt build/shared/*.class build/client/**/*.class

