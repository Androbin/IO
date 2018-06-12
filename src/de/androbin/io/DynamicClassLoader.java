package de.androbin.io;

import java.net.*;
import java.nio.file.*;
import java.util.*;

public final class DynamicClassLoader extends URLClassLoader {
  private static final Object LOCK = new Object();
  private static DynamicClassLoader classLoader;
  
  static {
    classLoader = new DynamicClassLoader();
  }
  
  private DynamicClassLoader() {
    this( new URL[ 0 ] );
  }
  
  private DynamicClassLoader( final URL[] urls ) {
    super( urls );
  }
  
  public static void addDynamicURL( final URL url ) {
    synchronized ( LOCK ) {
      classLoader.addURL( url );
    }
  }
  
  public static Path getPath( final String path ) {
    final URL url = classLoader.getResource( path );
    
    if ( url == null ) {
      return null;
    }
    
    try {
      return Paths.get( url.toURI() );
    } catch ( final URISyntaxException e ) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static void removeDynamicURL( final URL url ) {
    synchronized ( LOCK ) {
      final List<URL> urls = new ArrayList<>( Arrays.asList( classLoader.getURLs() ) );
      urls.remove( url );
      classLoader = new DynamicClassLoader( urls.toArray( new URL[ urls.size() ] ) );
    }
  }
}