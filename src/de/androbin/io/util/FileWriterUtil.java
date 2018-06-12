package de.androbin.io.util;

import de.androbin.func.*;
import java.io.*;
import java.nio.file.*;

public final class FileWriterUtil {
  private FileWriterUtil() {
  }
  
  private static void allocate( final Path file ) throws IOException {
    Files.createDirectories( file.getParent() );
    
    try {
      Files.createFile( file );
    } catch ( final FileAlreadyExistsException ignore ) {
    }
  }
  
  public static void writeFile( final Path file, final String content ) throws IOException {
    writeFile( file, writer -> writer.write( content ) );
  }
  
  public static void writeFile( final Path file,
      final DirtyConsumer<BufferedWriter, IOException> consumer ) throws IOException {
    allocate( file );
    
    try ( final BufferedWriter writer = Files.newBufferedWriter( file ) ) {
      consumer.accept( writer );
    }
  }
}