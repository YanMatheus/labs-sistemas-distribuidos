import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFileServer {

  public final static int SOCKET_PORT = 4444;
  public final static String DIR_TO_SEND = "/home/victor/Área de Trabalho/rpc-cpp";

  public static void main(String[] args) throws IOException {

    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;

    try {
        Zipper.zipFile(DIR_TO_SEND, DIR_TO_SEND + ".zip");
    } catch (IOException ex) {
        ex.printStackTrace();
        System.exit(1);
    }

    try {

      servsock = new ServerSocket(SOCKET_PORT);
      System.out.println("Waiting...");

      try {

        sock = servsock.accept();
        System.out.println("Accepted connection : " + sock);
        File myFile = new File(DIR_TO_SEND + ".zip");
        byte[] mybytearray = new byte[ (int)myFile.length() ];
        fis = new FileInputStream(myFile);
        bis = new BufferedInputStream(fis);
        bis.read(mybytearray, 0, mybytearray.length);
        os = sock.getOutputStream();
        System.out.printf("Seding '%s' (%d bytes)%n", myFile.getName(), myFile.length());
        os.write(mybytearray, 0, mybytearray.length);
        os.flush();
        System.out.println("Done.");
        
        if ( myFile.delete() )
            System.out.printf("Deleted '%s'%n", myFile.getName());

      } finally {
        if (bis != null) bis.close();
        if (os != null) os.close();
        if (sock !=null) sock.close();
      }

    } finally {
      if (servsock != null) servsock.close();
    }
  }

}
