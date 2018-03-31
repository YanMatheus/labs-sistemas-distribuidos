#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <netdb.h>

#define SERV_TCP_PORT 8000 // server's port
#define MAX_SIZE 80 // buffer max size

int main(int argc, char *argv[])
{
  char *serv_host = "localhost";
  char my_message[MAX_SIZE] = {0};
  int sockfd, port;
  struct sockaddr_in serv_addr;
  struct hostent *host_ptr;

  // command line: client <80-bytes-message> [host [port]]
  if (argc < 2) {
    perror("params: <80-bytes-message> [host [port]]");
    exit(1);
  }

  strncpy(my_message, argv[1], MAX_SIZE);
  port = SERV_TCP_PORT;

  if (argc == 3)
    serv_host = argv[2]; // read the host if provided
  else if (argc == 4)
    sscanf(argv[3], "%d", &port); // read the port if provided

  // get the address of the host
  if ( (host_ptr = gethostbyname(serv_host)) == NULL ) {
   perror("gethostbyname error");
   exit(2);
  }

  if ( host_ptr->h_addrtype !=  AF_INET ) {
   perror("unknown address type");
   exit(3);
  }

  bzero((char *) &serv_addr, sizeof(serv_addr));
  serv_addr.sin_family = AF_INET;
  serv_addr.sin_addr.s_addr = ( (struct in_addr *)host_ptr->h_addr_list[0] )->s_addr;
  serv_addr.sin_port = htons(port);


  // open a TCP socket
  if ( (sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0 ) {
   perror("can't open stream socket");
   exit(4);
  }

  // connect to the server
  if ( connect(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0 ) {
   perror("can't connect to server");
   exit(5);
  }

  fprintf(stdout, "connect with '%s'\n", host_ptr->h_name);

  // write a message to the server (send)
  // int bytes_sent = write(sockfd, my_message, strlen(my_message));
  int bytes_sent = send(sockfd, my_message, strlen(my_message), 0);
  fprintf(stdout, "sended this %d bytes to server: '%s'\n", bytes_sent, my_message);

  #ifdef PERSIST
  while (1);
  #endif

  close(sockfd);
}
