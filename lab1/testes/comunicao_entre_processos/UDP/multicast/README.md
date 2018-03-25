## Exemplo de uso do protocolo multicast

### Sockets Multicast
- usam o UDP Multicast
- permitem envio simultâneo de datagramas _(um único pacote IP)_ para um conjunto de processos
- recebe mensagens endereçadas a um grupo
- **grupos multicasts** são identificados por endereços IPv4 `[244,239].[0,255].[0,255].[0,255]`

- O emissor:
  + precisa conhecer o endereço IP do grupo e a porta do socket
  + não precisa fazer parte do grupo para enviar as mensagens ao grupo
  + não conhece a identidade dos destinatários
  + não conhece o tamanho do grupo


## build
> (em ../UDP)
```bash
javac UDP/multicast/*.java
```

## run
### iniciar o _server_
```bash
java UDP.multicast.Receptor 6789 &
netstat -lntup  ## verificar a execução do processo servidor
# kill -9 <PID> ## finalizar o processo servidor
```

### realizar requisições com o _client_
```bash
java UDP.multicast.Emissor 6789 "meu-user" "minha mensagem"
```
