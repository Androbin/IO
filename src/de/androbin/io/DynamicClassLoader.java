package de.androbin.io;

import java.net.*;
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
  
  public static URLClassLoader get() {
    return classLoader;
  }
  
  public static void removeDynamicURL( final URL url ) {
    synchronized ( LOCK ) {
      final List<URL> urls = Arrays.asList( classLoader.getURLs() );
      urls.remove( url );
      classLoader = new DynamicClassLoader( urls.toArray( new URL[ urls.size() ] ) );
    }
  }
}