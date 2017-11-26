package de.androbin.io;

import java.io.*;
import javax.swing.filechooser.FileFilter;

public final class ExtensionFileFilter extends FileFilter {
  public final String format;
  
  public ExtensionFileFilter( final String format ) {
    this.format = format.toLowerCase();
  }
  
  @ Override
  public boolean accept( final File file ) {
    if ( !file.isAbsolute() || file.isHidden() ) {
      return false;
    }
    
    if ( file.isDirectory() ) {
      return true;
    }
    
    if ( file.isFile() ) {
      final String name = file.getName().toLowerCase();
      return name.endsWith( "." + format );
    }
    
    return false;
  }
  
  @ Override
  public String getDescription() {
    return format.toUpperCase();
  }
}