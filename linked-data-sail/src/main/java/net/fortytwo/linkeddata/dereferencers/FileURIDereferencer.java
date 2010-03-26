/*
 * $URL: https://ripple.googlecode.com/svn/trunk/ripple-linkeddata/src/main/java/net/fortytwo/linkeddata/sail/FileURIDereferencer.java $
 * $Revision: 6 $
 * $Author: parcour $
 *
 * Copyright (C) 2007-2010 Joshua Shinavier
 */


package net.fortytwo.linkeddata.dereferencers;

import net.fortytwo.ripple.RippleException;
import net.fortytwo.flow.rdf.RDFUtils;
import net.fortytwo.linkeddata.Dereferencer;
import org.openrdf.rio.RDFFormat;
import org.restlet.resource.Representation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class FileURIDereferencer implements Dereferencer
{
	public Representation dereference( final String uri ) throws RippleException
	{
		return new FileRepresentation( uri );
	}

	private class FileRepresentation extends Representation
	{
		private InputStream inputStream;

		public FileRepresentation( final String uri ) throws RippleException
		{
			RDFFormat format = RDFUtils.guessRdfFormat( uri, null );
			/*if ( null == format )
			{
				throw new RippleException( "could not guess format for URI: " + uri );
			}*/
			setMediaType( RDFUtils.findMediaType( format ) );
 
			try
			{
				inputStream = new FileInputStream( uri.substring( 5 ) );
			}

			catch ( IOException e )
			{
				throw new RippleException( e );
			}
		}

		public ReadableByteChannel getChannel() throws IOException {
			return null;  //To change body of implemented methods use File | Settings | File Templates.
		}

		public InputStream getStream() throws IOException {
			return inputStream;
		}

		public void write(OutputStream outputStream) throws IOException {
			//To change body of implemented methods use File | Settings | File Templates.
		}

		public void write(WritableByteChannel writableByteChannel) throws IOException {
			//To change body of implemented methods use File | Settings | File Templates.
		}
	}
}
