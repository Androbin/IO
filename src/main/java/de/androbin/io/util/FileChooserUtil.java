package de.androbin.io.util;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public final class FileChooserUtil {
  private FileChooserUtil() {
  }
  
  public static JFileChooser createDirectoryChooser( final File dir ) {
    final JFileChooser jfc = new JFileChooser( dir );
    jfc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
    return jfc;
  }
  
  public static JFileChooser createFileChooser( final File dir, final FileFilter filter ) {
    final JFileChooser jfc = new JFileChooser( dir );
    jfc.setFileFilter( filter );
    return jfc;
  }
  
  private static Optional<File> openDialog( final JFileChooser jfc ) {
    return jfc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION
        ? Optional.of( jfc.getSelectedFile() )
        : Optional.empty();
  }
  
  public static Optional<File> openDirectory( final File dir ) {
    return openDialog( createDirectoryChooser( dir ) );
  }
  
  public static Optional<File> openFile( final File dir, final FileFilter filter ) {
    return openDialog( createFileChooser( dir, filter ) );
  }
  
  private static Optional<File> saveDialog( final JFileChooser jfc ) {
    return jfc.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION
        ? Optional.of( jfc.getSelectedFile() )
        : Optional.empty();
  }
  
  public static Optional<File> saveDirectory( final File dir ) {
    return saveDialog( createDirectoryChooser( dir ) );
  }
  
  public static Optional<File> saveFile( final File dir, final FileFilter filter ) {
    return saveDialog( createFileChooser( dir, filter ) );
  }
}