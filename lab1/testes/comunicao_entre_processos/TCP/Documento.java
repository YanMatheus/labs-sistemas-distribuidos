package TCP;

import java.net.*;
import java.io.*;

public class Documento implements Serializable {

  private static final long serialVersionUID = 1L;
  String titulo, autor;

  public Documento(String titulo, String autor) {
    this.titulo = titulo;
    this.autor = autor;
  }

  @Override
  public String toString() {
    return titulo + " - " + autor;
  }

}
