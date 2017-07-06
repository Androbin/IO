package de.androbin.io.util;

import java.io.*;
import java.util.function.*;

public final class FileWriterUtil {
  private FileWriterUtil() {
  }
  
  public static boolean writeFile( final File file, final String content ) {
    try ( final BufferedWriter writer = new BufferedWriter( new FileWriter( file ) ) ) {
      writer.write( content );
      return true;
    } catch ( final IOException e ) {
      return false;
    }
  }
  
  public static boolean writeFile( final File file, final Consumer<BufferedWriter> consumer ) {
    try ( final BufferedWriter writer = new BufferedWriter( new FileWriter( file ) ) ) {
      consumer.accept( writer );
      return true;
    } catch ( final IOException e ) {
      return false;
    }
  }
}