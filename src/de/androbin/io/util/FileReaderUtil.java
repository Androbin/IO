package de.androbin.io.util;

import java.io.*;
import java.util.*;
import java.util.function.*;

public final class FileReaderUtil
{
	private FileReaderUtil()
	{
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
		final Reader reader = new FileReader( file );
		final String data = read( reader );
		reader.close();
		return data;
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
		return joiner.toString();
	}
}
