package de.androbin.io;

import java.io.*;
import javax.swing.filechooser.FileFilter;
import jdk.nashorn.internal.objects.annotations.*;

public final class FormatFileFilter extends FileFilter
{
	private final String description;
	private final String ending;
						 
	public FormatFileFilter( final String format )
	{
		description = format.toUpperCase();
		ending = "." + format.toLowerCase();
	}
	
	@ Override
	public boolean accept( final File file )
	{
		return file.isAbsolute() && !file.isHidden() && file.isDirectory() || file.isFile() && file.getName().toLowerCase().endsWith( getEnding() );
	}
	
	@ Override
	public String getDescription()
	{
		return description;
	}
	
	@ Getter
	public String getEnding()
	{
		return ending;
	}
}