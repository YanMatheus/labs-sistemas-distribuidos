## vide

Aplicação simples **client-server** que utiliza o _Remote Procedure Call_ (RPC) como camada de comunicação entre o cliente e o servidor.
A comunicação seguiu o protocolo **request-reply** através de _sockets_ UDP (protocolo sem conexão onde a comunicação é feita por um item de dados chamado _datagram_).
- Um processo cliente envia uma mensagem para um nó remoto e recebe em resposta a mesma mensagem com um sufixo adicionado pelo processo servidor.
- O processo servidor fica esperando mensagens numa porta.

```
Internet Adrress = 138.37.94.248
+----------------+                   +----------------+
|               ::                   ::               |
|   (socket)~~~~::~~~~~~[message]~~~~::~~~~~(socket)  |
|               ::                   ::               |
|    client     ::                   ::    server     |
|               ::                   ::               |
+----------------+                   +----------------+
                                     Internet Address = 138.37.88.249
```

class | description
:-----|:------------
[`DatagramSocket`](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramSocket.html) | Represents a socket for sending and receiving datagram packets.<br> A datagram socket is the sending or receiving point for a packet delivery service. Each packet sent or received on a datagram socket is individually addressed and routed. Multiple packets sent from one machine to another may be routed differently, and may arrive in any order.
[`DatagramPacket`](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramPacket.html) | Represents a datagram packet.<br> Are used to implement a connectionless packet delivery service. Each message is routed from one machine to another based solely on information contained within that packet. Multiple packets sent from one machine to another might be routed differently, and might arrive in any order. Packet delivery is not guaranteed.
[`InetAddress`](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) | Represents an Internet Protocol (IP) address. <br> An IP address is either a 32-bit or 128-bit unsigned number used by IP, a lower-level protocol on which protocols like UDP and TCP are built.


## build
> (em ../UDP)
```bash
javac UDP/*.java
```

## run
### iniciar o _server_
```bash
java UDP.UDPServer 6789 &
netstat -lntup  ## verificar a execução do processo servidor
# kill -9 <PID> ## finalizar o processo servidor
```

### realizar requisições com o _client_
```bash
java UDP.UDPClient 6789 localhost "minha mensagem"
```

> demo da versão sem o tratamento da mensagem
[![asciicast-old-demo](https://asciinema.org/a/ySdNfB3mNcBZswqhVU4QoZ6CW.png)](https://asciinema.org/a/ySdNfB3mNcBZswqhVU4QoZ6CW)



## [RFC 768 - User Datagram Protocol](https://tools.ietf.org/html/rfc768)

```
Format
------

0      7 8     15 16    23 24    31
+--------+--------+--------+--------+
|     Source      |   Destination   |
|      Port       |      Port       |
+--------+--------+--------+--------+
|                 |                 |
|     Length      |    Checksum     |
+--------+--------+--------+--------+
|
|          data octets ...
+---------------- ...

    User Datagram Header Format


Fields
------

Source Port is an optional field, when meaningful, it indicates the port
of the sending  process,  and may be assumed  to be the port  to which a
reply should  be addressed  in the absence of any other information.  If
not used, a value of zero is inserted.

~

Destination  Port has a meaning  within  the  context  of  a  particular
internet destination address.

Length  is the length  in octets  of this user datagram  including  this
header  and the data.   (This  means  the minimum value of the length is
eight.)

Checksum is the 16-bit one's complement of the one's complement sum of a
pseudo header of information from the IP header, the UDP header, and the
data,  padded  with zero octets  at the end (if  necessary)  to  make  a
multiple of two octets.

The pseudo  header  conceptually prefixed to the UDP header contains the
source  address,  the destination  address,  the protocol,  and the  UDP
length.   This information gives protection against misrouted datagrams.
This checksum procedure is the same as is used in TCP.

0      7 8     15 16    23 24    31
+--------+--------+--------+--------+
|          source address           |
+--------+--------+--------+--------+
|        destination address        |
+--------+--------+--------+--------+
|  zero  |protocol|   UDP length    |
+--------+--------+--------+--------+

If the computed  checksum  is zero,  it is transmitted  as all ones (the
equivalent  in one's complement  arithmetic).   An all zero  transmitted
checksum  value means that the transmitter  generated  no checksum  (for
debugging or for higher level protocols that don't care).
```
