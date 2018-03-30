## vide
Dois programas (_client_ e _server_) para demonstrar uma RPC que computa a soma de 2 números.
O programa servidor não atende a mais de 1 cliente pois quando a conexão é aceita pelo primeiro,
seu processo fica esperando requisições (de acordo com a assinatura da função _somar_) dele.
Assim, devido à fila de sockets em espera, os demais clientes que estabelecerem uma conexão, não poderão realizar a operação _somar_
até que o anterior feche a conexão.

- a conexão é estabelecida através de **sockets stream** (via TCP) a fim de manter um canal de comunicação entre os dois hosts.

[![Comunicação R-R](./docs/diagrams/sequence-diagram1.png)](./docs/diagrams/sequence-diagram1.mmd)
> diagram build with [MermaidJS](https://mermaidjs.github.io)

```
.
├── client
│   ├── ClientSocketController.java
│   └── TelaPrincipal.java           [main]
└── server
    ├── ConnectionProtocol.java
    ├── InfoLog.java
    └── ServerSocketController.java  [main]

2 directories, 5 files
```

## build & run

```bash
mkdir build && javac -d build src/client/*.java src/server/*.java
cd build
java server.ServerSocketController &
java client.TelaPrincipal &

netstat -t6 ## verificar estado da conexão
```