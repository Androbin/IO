package de.androbin.util;

import java.io.*;
import java.util.function.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public final class FileUtil
{
	private FileUtil()
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
	
	public static <T> T loadFile( final File file, final Function<FileInputStream, T> f )
	{
		try
		{
			final FileInputStream stream = new FileInputStream( file );
			final T o = f.apply( stream );
			stream.close();
			return o;
		}
		catch ( final IOException e )
		{
			return null;
		}
	}
	
	public static String readFile( final File file ) throws IOException
	{
		final FileReader reader = new FileReader( file );
		final String data = read( reader );
		reader.close();
		return data;
	}
	
	public static String read( final Reader reader ) throws IOException
	{
		return read( new BufferedReader( reader ) );
	}
	
	public static String read( final BufferedReader reader ) throws IOException
	{
		final StringBuilder sb = new StringBuilder();
		reader.lines().forEachOrdered( line ->
		{
			sb.append( System.lineSeparator() );
			sb.append( line );
		} );
		return sb.toString();
	}
}