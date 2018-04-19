===================
COMPILAR E EXECUTAR
===================

mkdir -p build
javac -d build src/**/*.java

cd build

java -Djava.rmi.server.hostname=127.0.0.1 server.ServerMain 6789

java -Djava.security.policy=../all.policy client.ClientMain 127.0.0.1 6789 user1


====================
PROBLEMAS CONHECIDOS
====================

- se um processo usuário sair sem avisar,
  o broadcast gera uma exceção pois enviará uma mensagem para um referência inexistente.
  Assim, todo a conexão fica comprometida (é preciso reiniciar o server);

- clientes não sabem quando o processo servidor finaliza.
