## usar o comando `make` para compilar tudo
## usar `make clean` para apagar todos os `.class` gerados

JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class: ; $(JC) $(JFLAGS) $*.java

CLASSES = \
  TCP/TCPServer \
  TCP/TCPClient \
  TCP/Documento \
  UDP/UDPServer \
  UDP/UDPClient

default: classes

classes: $(CLASSES:=.class)

clean: ; $(RM) $(CLASSES:=.class)

.PHONY: default