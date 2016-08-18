package de.androbin.io.util;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public final class FileChooserUtil
{
	private FileChooserUtil()
	{
	}
	
	public static File askForFileOpening( final File dir, final FileFilter filter )
	{
		final JFileChooser jfc = createFileChooser( dir, filter );
		return jfc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION ? jfc.getSelectedFile() : null;
	}
	
	public static File askForFileSaving( final File dir, final FileFilter filter )
	{
		final JFileChooser jfc = createFileChooser( dir, filter );
		return jfc.showSaveDialog( null ) == JFileChooser.APPROVE_OPTION ? jfc.getSelectedFile() : null;
	}
	
	public static JFileChooser createFileChooser( final File dir, final FileFilter filter )
	{
		final JFileChooser jfc = new JFileChooser( dir );
		jfc.setFileFilter( filter );
		return jfc;
	}
}