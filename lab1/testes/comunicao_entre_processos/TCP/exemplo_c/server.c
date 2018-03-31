#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/tcp.h>
#include <arpa/inet.h>

#define SERV_TCP_PORT 8000 // server's port number
#define MAX_SIZE 80 // buffer max size

int main(int argc, char *argv[])
{
  int sockfd, port;
  struct sockaddr_in cli_addr, serv_addr;
  char buffer[MAX_SIZE];

  // command line: server [port_number]
  if(argc == 2)
    sscanf(argv[1], "%d", &port); // read the port number if provided
  else
    port = SERV_TCP_PORT;

  // open a TCP socket (an Internet stream socket)
  if ( (sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0 ) {
    perror("can't open stream socket");
    exit(1);
  }

  // bind the local address, so that the cliend can send to server
  bzero((char *) &serv_addr, sizeof(serv_addr));
  serv_addr.sin_family = AF_INET;
  serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
  serv_addr.sin_port = htons(port);

  if ( bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0 ) {
    perror("can't bind local address");
    exit(2);
  }

  // listen to the socket
  listen(sockfd, 5);

  for (;;) {
    // wait for a connection from a client; this is an iterative server
    int clilen = sizeof(cli_addr);
    int newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);

    fprintf(stdout, "connection accept with socket port %hu\n", cli_addr.sin_port);
    if (newsockfd < 0) perror("can't bind local address");

    // read a message from the client (receive)
    // int len = read(newsockfd, buffer, MAX_SIZE);
    int len = recv(newsockfd, buffer, MAX_SIZE, 0);
    // make sure it's a proper string
    buffer[len] = 0;
    printf("receive %d bytes: '%s'\n", len, buffer);

    #ifdef PERSIST
    while (1);
    #endif

    close(newsockfd);
  }
}
