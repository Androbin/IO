package de.androbin.io.util;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.file.FileSystem;
import java.util.*;

public final class FileSystemUtil {
  private FileSystemUtil() {
  }
  
  @ SuppressWarnings( "resource" )
  public static Path createRoot( final URI uri ) {
    final FileSystem fs;
    
    try {
      fs = FileSystems.newFileSystem( uri, Collections.singletonMap( "create", "true" ) );
    } catch ( final IOException e ) {
      e.printStackTrace();
      return null;
    }
    
    return fs.getRootDirectories().iterator().next();
  }
}