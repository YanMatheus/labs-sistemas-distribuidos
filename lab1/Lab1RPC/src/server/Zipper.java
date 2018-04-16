package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {

  static final short BUFFER_SIZE = 1024;

  public static File zipFile(String srcFilePath, String outFilePath) throws IOException {
      FileOutputStream fos = new FileOutputStream(outFilePath);
      ZipOutputStream zipOut = new ZipOutputStream(fos);
      File srcFile = new File(srcFilePath);

      zipFile(srcFile, srcFile.getName(), zipOut);
      zipOut.close();
      fos.close();
      return srcFile;
  }

  private static void zipFile(File srcFile, String fileName, ZipOutputStream zipOut) throws IOException {
      if ( srcFile.isHidden() ) return;

      if ( srcFile.isDirectory() ) {
          File[] children = srcFile.listFiles();
          for (File childFile: children)
          zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
          return;
      }

      FileInputStream fis = new FileInputStream(srcFile);
      ZipEntry zipEntry = new ZipEntry(fileName);
      byte[] bytes = new byte[BUFFER_SIZE];
      int length;

      zipOut.putNextEntry(zipEntry);
      while ((length = fis.read(bytes)) >= 0) zipOut.write(bytes, 0, length);
      fis.close();
  }

}
