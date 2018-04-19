#!/bin/bash
# criar o diretório build/ e compilar tudo e gerar os .jar

mkdir -p build

javac -d build/ src/shared/*.java src/server/*.java
javac -d build/ src/shared/*.java src/client/**/*.java

cd build

echo "Main-Class: server.ServerMain" > m-servidor.txt
echo "Main-Class: client.GUI.ClientMain" > m-client.txt

jar cfm Server.jar m-servidor.txt shared/*.class server/*.class
jar cfm Client.jar m-client.txt shared/*.class client/**/*.class

