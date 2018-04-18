javac shared/*.java rmiserver/*.java rmiclient/*.java

java -Djava.rmi.server.hostname=10.208.200.172 rmiserver.RMIServerMain

java -Djava.security.policy=all.policy rmiclient.RMIClientMain
