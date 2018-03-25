## vide

Aplicação simples **client-server** que utiliza o _Remote Procedure Call_ (RPC) como camada de comunicação entre o cliente e o servidor.
A comunicação seguiu o protocolo **request-reply** através de _sockets_ TCP (protocolo com conexão onde a comunicação é feita por _streams_). <br>
A aplicação define quantos bytes devem ser enviados ou lidos da stream, sem a preocupação do tamanho máximo de pacotes.
- Um par de processos estabelecem uma conexão antes de se comunicarem por uma stream.
- Um processo cliente cria um socket para tentar estabelecer uma ligação com uma porta de um servidor, numa máquina remota.
- O processo servidor cria um "listening" socket associado à porta servidor.
- O client transfere dois objetos (um por vez) para o servidor.
- O servidor desempacota cada objeto recebido.


class | description
:-----|:------------
[`Socket`](https://docs.oracle.com/javase/8/docs/api/java/net/Socket.html) | A (client) socket is an endpoint for communication between two machines.
[`ServerSocket`](https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html) | A server socket waits for requests to come in over the network. It performs some operation based on that request, and then possibly returns a result to the requester.
[`DataOutputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/DataOutputStream.html) | A data output stream lets an application write primitive Java data types to an output stream in a portable way.<br> An application can then use a data input stream to read the data back in.
[`DataInputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/DataInputStream.html) | A data input stream lets an application read primitive Java data types from an underlying input stream in a machine-independent way.<br> An application uses a data output stream to write data that can later be read by a data input stream.
[`ObjectOutputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html) | An ObjectOutputStream writes primitive data types and graphs of Java objects to an OutputStream. The objects can be read (reconstituted) using an ObjectInputStream. Persistent storage of objects can be accomplished by using a file for the stream. If the stream is a network socket stream, the objects can be reconstituted on another host or in another process.<br> Only objects that support the java.io.Serializable interface can be written to streams.
[`ObjectInputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputStream.html) | An ObjectInputStream deserializes primitive data and objects previously written using an ObjectOutputStream.


## build
> (em ../TCP)
```bash
javac TCP/*.java
```

## run
### iniciar o _server_
```bash
java TCP.TCPServer 6789
```

### realizar requisições com o _client_
```bash
java TCP.TCPClient localhost 6789 A
```



## [RFC 793 - Transport Control Protocol](https://tools.ietf.org/html/rfc793)

Streams TCP usam:
- checksums para detectar e rejeitar pacotes corrompidos
- número de sequência para detectar e rejeitar pacotes duplicados
- timeouts e retransmissão para lidar com pacotes perdidos


```
TCP segments are sent as internet datagrams.  The Internet Protocol
header carries several information fields, including the source and
destination host addresses [2].  A TCP header follows the internet
header, supplying information specific to the TCP protocol.  This
division allows for the existence of host level protocols other than TCP.


0                   1                   2                   3
0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|          Source Port          |       Destination Port        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                        Sequence Number                        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Acknowledgment Number                      |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|  Data |           |U|A|P|R|S|F|                               |
| Offset| Reserved  |R|C|S|S|Y|I|            Window             |
|       |           |G|K|H|T|N|N|                               |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|           Checksum            |         Urgent Pointer        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Options                    |    Padding    |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                             data                              |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

                        TCP Header Format
```
