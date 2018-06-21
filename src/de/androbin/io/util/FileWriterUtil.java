package de.androbin.io.util;

import de.androbin.func.*;
import java.io.*;
import java.nio.file.*;

public final class FileWriterUtil {
  private FileWriterUtil() {
  }
  
  public static void writeFile( final Path file, final String content ) throws IOException {
    writeFile( file, writer -> writer.write( content ) );
  }
  
  public static void writeFile( final Path file,
      final DirtyConsumer<BufferedWriter, IOException> consumer ) throws IOException {
    Files.createDirectories( file.getParent() );
    
    try ( final BufferedWriter writer = Files.newBufferedWriter( file,
        StandardOpenOption.CREATE,
        StandardOpenOption.TRUNCATE_EXISTING ) ) {
      consumer.accept( writer );
    }
  }
}