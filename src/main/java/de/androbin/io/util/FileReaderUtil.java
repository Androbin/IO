package de.androbin.io.util;

import de.androbin.func.*;
import de.androbin.io.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public final class FileReaderUtil {
  private FileReaderUtil() {
  }
  
  public static <T> T fetch( final String url,
      final DirtyFunction<BufferedReader, T, IOException> func ) throws IOException {
    try ( final BufferedReader reader = new BufferedReader(
        new InputStreamReader( new URL( url ).openStream() ) ) ) {
      return func.apply( reader );
    }
  }
  
  public static String read( final String path ) {
    return readFile( DynamicClassLoader.getPath( path ) );
  }
  
  public static String readFile( final Path file ) {
    return readFileDirty( file, reader -> {
      final StringJoiner joiner = new StringJoiner( "\n" );
      reader.lines().forEachOrdered( joiner::add );
      return joiner.toString();
    } );
  }
  
  public static <T> T readFile( final Path file,
      final DirtyFunction<BufferedReader, T, IOException> func ) throws IOException {
    if ( file == null ) {
      return null;
    }
    
    try ( final BufferedReader reader = Files.newBufferedReader( file ) ) {
      return func.apply( reader );
    }
  }
  
  public static <T> T readFileDirty( final Path file,
      final DirtyFunction<BufferedReader, T, IOException> func ) {
    try {
      return readFile( file, func );
    } catch ( final IOException e ) {
      return null;
    }
  }
  
  public static Scanner scanFile( final Path path ) throws IOException {
    return new Scanner( Files.newBufferedReader( path ) );
  }
}