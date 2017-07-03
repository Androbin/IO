package de.androbin.io.util;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.*;

public final class FileReaderUtil {
  private FileReaderUtil() {
  }
  
  public static String read( final String path ) {
    final URL res = ClassLoader.getSystemResource( path );
    return res == null ? null : read( res );
  }
  
  public static String read( final URL url ) {
    try ( final InputStream stream = url.openStream() ) {
      return read( stream );
    } catch ( final IOException e ) {
      return null;
    }
  }
  
  public static String read( final InputStream stream ) {
    return read( new BufferedReader( new InputStreamReader( stream ) ) );
  }
  
  public static String read( final BufferedReader reader ) {
    final StringJoiner joiner = new StringJoiner( System.lineSeparator() );
    read( reader, line -> joiner.add( line ) );
    return joiner.toString();
  }
  
  public static void read( final BufferedReader reader, final Consumer<String> consumer ) {
    reader.lines().forEachOrdered( consumer );
  }
  
  public static String readFile( final File file ) {
    try ( final BufferedReader reader = new BufferedReader( new FileReader( file ) ) ) {
      return read( reader );
    } catch ( final IOException e ) {
      return null;
    }
  }
  
  public static <T> T readFile( final File file, final Function<BufferedReader, T> f ) {
    try ( final BufferedReader reader = new BufferedReader( new FileReader( file ) ) ) {
      return f.apply( reader );
    } catch ( final IOException e ) {
      return null;
    }
  }
}