package de.androbin.io.util;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.*;

public final class FileReaderUtil
{
	private FileReaderUtil()
	{
	}
	
	public static <T> T loadFile( final File file, final Function<FileInputStream, T> f )
	{
		try ( final FileInputStream stream = new FileInputStream( file ) )
		{
			return f.apply( stream );
		}
		catch ( final IOException e )
		{
			return null;
		}
	}
	
	public static String readFile( final File file ) throws IOException
	{
		try ( final Reader reader = new FileReader( file ) )
		{
			return read( reader );
		}
	}
	
	public static String read( final URL url ) throws IOException
	{
		return read( url.openStream() );
	}
	
	public static String read( final InputStream stream ) throws IOException
	{
		return read( new InputStreamReader( stream ) );
	}
	
	public static String read( final Reader reader ) throws IOException
	{
		return read( new BufferedReader( reader ) );
	}
	
	public static String read( final BufferedReader reader ) throws IOException
	{
		final StringJoiner joiner = new StringJoiner( System.lineSeparator() );
		reader.lines().forEachOrdered( line ->
		{
			joiner.add( line );
		} );
		reader.close();
		return joiner.toString();
	}
}
