> - (c) https://www.youtube.com/watch?v=HbBxO5RXNhU
> - vide o paper [rpcgen Programming Guide - FreeBSD](https://docs.freebsd.org/44doc/psd/22.rpcgen/paper.pdf)
> - [RPC Language Specification](https://docs.oracle.com/cd/E19683-01/816-1435/6m7rrfn9k/index.html)

## pré-requisitos

```bash
## além do programa `make`
sudo apt install rpcbind
rpcinfo ## verificar se o rpcbind foi instalado
```

## build & run

1. Gerar todos os módulos estruturados com ANSI C: `rpcgen -a -C add.x`
2. Inserir a operação no código do server: `vim add_server.c`
```c
int a = argp->a;
int b = argp->b;
printf ("add(%d, %d) foi chamado\n", a, b);
result = a + b;
```

3. Alterar a função _main_: `vim add_client.c`
```c
/* ... na função main */
if (argc < 4) {
  printf ("usage: %s <server_host> <number1> <number2>\n", argv[0]);
  exit (1);
}

host = argv[1];
add_prog_1 (host, atoi(argv[2]), atoi(argv[3]));
```

4. Inserir a leitura das variáveis passadas pela chamada na _main_:
```c
/* add parâmetros específicos */
add_prog_1(char *host, int x, int y)

/* ... após do primeiro #endif */
add_1_arg.a = x;
add_1_arg.b = y;

result_1 = add_1(&add_1_arg, clnt);
if (result_1 == (int *) NULL) {
  clnt_perror (clnt, "call failed");
} else {
  printf ("Resultado = %d\n", *result_1);
}
```

5. Compilar tudo: `make -f Makefile.add`
6. Iniciar servidor em background: `./add_server &`
7. Testar cliente: `./add_client localhost 1234 56789`
8. Apagar tudo: `make -f Makefile.add clean && rm Makefile.add`


---

# da especificação do lab1
### 4. Usando o [rpcgen](https://docs.oracle.com/cd/E19683-01/816-1435/6m7rrfn7f/index.html) para gerar os stubs do Cliente e do Servidor

Conforme já foi mencionado, junto com a biblioteca RPC existe uma ferramenta denominada rpcgen cujo objetivo é gerar, a
partir de um arquivo de definição de interface (**IDF** - Interface Definition File), todos os módulos de software que devem
estar nos lados cliente e servidor, incluindo os stubs (server skeleton).

De um modo geral os passos para geração de uma aplicação cliente/servidor RPC os seguintes passos devem ser
considerados:

1. **Identificar quais funcionalidades devem estar no programa principal e quais sub-rotinas serão acionadas no servidor.** <br>
Essa percepção deve ser sistematizada no arquivo de definição de interface (Interface Definition File)
apresentando na figura acima. Em outras palavras, o programador deve construir seu código usando linguagem C
convencional e, a partir desse código, identificar que funções devem ser ativadas e que parâmetros devem ser
passados para elas. Esses informações devem ser incluídas no arquivo de interface IDF e, a partir dele, gera-se
todos os códigos da figura acima.

2. **Aplicar o utilitário de geração dos módulos cliente e servidor no arquivo IDF gerado.** <br>
No caso do Linux, a ferramenta de geração dos módulos da figura é o rpcgen da SUN Microsystens, considerada um padrão de facto
no mercado. Esse utilitário pressupõe que o arquivo IDF tem um nome com a sufixo `.x`, e com base nele, os
códigos são gerados em linguagem C e estão estruturados de forma que o programador possa alterá-los com o
menor esforço possível.

3. **Modificar os arquivos do cliente e do servidor para que atendam o objetivo desejado para a aplicação.** <br>
Em princípio o programador necessita mexer apenas nesse dois arquivos, inserindo neles as lógicas presentes nas
funções principal e secundárias do código que foi construído no modo convencional.

4. **Compilar os códigos alterados e colocá-los em hosts cliente e servidor.** <br>
No caso do rpcgen da SUN instalado em
máquinas Linux, o rpcgen gera, além dos arquivos mencionados, um arquivo com diretivas de compilação para
ajudar no processo de geração dos binários cliente e servidor. Esse arquivo é um _Makefile.progr_ que deverá ser
utilizado pelo utilitário make.