package de.androbin.io;

import de.androbin.io.util.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.file.FileSystem;
import java.util.*;

public final class DynamicClassLoader extends URLClassLoader {
  private static final Object LOCK = new Object();
  private static DynamicClassLoader classLoader;
  private static Deque<FileSystem> fs;
  
  static {
    classLoader = new DynamicClassLoader();
    fs = new ArrayDeque<>();
    
    bindArchive( DynamicClassLoader.class );
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
  
  public static void bindArchive( final Class<?> clazz ) {
    final URL url = clazz.getResource( clazz.getSimpleName() + ".class" );
    
    if ( !url.toString().startsWith( "jar:" ) ) {
      return;
    }
    
    final URI uri;
    
    try {
      uri = url.toURI();
    } catch ( final URISyntaxException e ) {
      e.printStackTrace();
      return;
    }
    
    final Path root = FileSystemUtil.createRoot( uri );
    useFileSystem( root.getFileSystem() );
  }
  
  public static Path getPath( final String path ) {
    final URI uri = getURI( path );
    
    if ( uri == null ) {
      return null;
    }
    
    try {
      return Paths.get( uri );
    } catch ( final FileSystemNotFoundException ignore ) {
    }
    
    for ( final FileSystem fs : DynamicClassLoader.fs ) {
      try {
        return fs.provider().getPath( uri );
      } catch ( final IllegalArgumentException ignore ) {
      }
    }
    
    return null;
  }
  
  private static URI getURI( final String path ) {
    final URL url = classLoader.getResource( path );
    
    if ( url == null ) {
      return null;
    }
    
    try {
      return url.toURI();
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
  
  public static Closeable useFileSystem( final FileSystem fs ) {
    DynamicClassLoader.fs.push( fs );
    return () -> {
      DynamicClassLoader.fs.remove( fs );
      fs.close();
    };
  }
}