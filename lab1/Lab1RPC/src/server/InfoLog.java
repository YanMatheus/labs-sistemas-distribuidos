package server;

/**
 * Wrapper para deixar um padrão
 * nas mensagens de saídas geradas
 * pela aplicação servidora.
 */
public class InfoLog {

  static void printToStdout(String fmt, Object... values) {
    System.out.printf("*** " + fmt + '\n', values);
  }

  static void printToStderr(String fmt, Object... values) {
    System.err.printf("*** " + fmt + '\n', values);
  }

}
