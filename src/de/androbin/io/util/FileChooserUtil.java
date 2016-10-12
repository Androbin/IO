package de.androbin.io.util;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public final class FileChooserUtil
{
	private FileChooserUtil()
	{
	}
	
	public static File askForDirectoryOpening( final File dir )
	{
		final JFileChooser jfc = createDirectoryChooser( dir );
		return openDialog( jfc );
	}
	
	public static File askForFileOpening( final File dir, final FileFilter filter )
	{
		final JFileChooser jfc = createFileChooser( dir, filter );
		return openDialog( jfc );
	}
	
	public static File askForFileSaving( final File dir, final FileFilter filter )
	{
		final JFileChooser jfc = createFileChooser( dir, filter );
		return saveDialog( jfc );
	}
	
	public static JFileChooser createDirectoryChooser( final File dir )
	{
		final JFileChooser jfc = new JFileChooser( dir );
		jfc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		return jfc;
	}
	
	public static JFileChooser createFileChooser( final File dir, final FileFilter filter )
	{
		final JFileChooser jfc = new JFileChooser( dir );
		jfc.setFileFilter( filter );
		return jfc;
	}
	
	private static File openDialog( final JFileChooser jfc )
	{
		return jfc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ? jfc.getSelectedFile() : null;
	}
	
	private static File saveDialog( final JFileChooser jfc )
	{
		return jfc.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION ? jfc.getSelectedFile() : null;
	}
}
