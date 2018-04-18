The following classes implement a simple client-server program using RMI that displays a message.

RmiServer class
===============
Listens to RMI requests and implements the interface which is used by the client to invoke remote methods.

RmiServerIntf interface
=======================
Defines the interface that is used by the client and implemented by the server.

RmiClient class
===============
This is the client which gets the reference to the remote object and invokes its method to get a message.


server.policy
=============
This file is required on the server to allow TCP/IP communication for the remote registry and for the RMI server.

client.policy
=============
This file is required on the client to connect to RMI Server using TCP/IP.

no.policy
=========
Also if you have a troubles with connecting, try this file for server or client.


----

Before running this subj, we need to make 'Stub' file of interface we used.
For this task we have RMI compiller - 'rmic'


$ javac **/*.java # compilar tudo
$ rmic server.RmiServer # gerar os stubs do server

.
├── client
│   ├── RmiClient.class
│   └── RmiClient.java
├── client.policy
├── no.policy
├── server
│   ├── RmiServer.class
│   ├── RmiServer.java
│   └── RmiServer_Stub.class
├── server.policy
└── shared
    ├── RmiServerIntf.class
    └── RmiServerIntf.java

$ java -Djava.security.policy=server.policy server.RmiServer # exeuctar server
$ java -Djava.security.policy=no.policy client.RmiClient # executar client
