package ru.qixi.api.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;

/**
 * @author QiXi
 * @mail qixi@mail.ru
 * @site http://qixi.ru
 **/


public class StreamUtils{

	public static final int IO_BUFFER_SIZE = 8 * 1024;

	private static final int END_OF_STREAM = -1;


	public static final String readFully(final InputStream pInputStream) throws IOException {
		final StringWriter writer = new StringWriter();
		final char[] buf = new char[IO_BUFFER_SIZE];
		try {
			final Reader reader = new BufferedReader(new InputStreamReader(pInputStream, "UTF-8"));
			int read;
			while ((read = reader.read(buf)) != END_OF_STREAM) {
				writer.write(buf, 0, read);
			}
		} finally {
			close(pInputStream);
		}
		return writer.toString();
	}


	public static final void close(final Closeable pCloseable) {
		if (pCloseable != null) {
			try {
				pCloseable.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public static final boolean copyAndClose(final InputStream pInputStream, final OutputStream pOutputStream) {
		IStreamProgress progress = new StreamProgress();
		return copyInProgerssAndClose(pInputStream, pOutputStream, progress);
	}


	public static final boolean copyInProgerssAndClose(final InputStream pInputStream, final OutputStream pOutputStream, final IStreamProgress pProgress) {
		try {
			copyInProgerss(pInputStream, pOutputStream, pProgress, END_OF_STREAM);
			return true;
		}
		catch (final IOException e) {
			return false;
		}
		finally {
			close(pInputStream);
			close(pOutputStream);
		}
	}

	
	public static final void copyInProgerss(final InputStream pInputStream, final OutputStream pOutputStream, final IStreamProgress pProgress) throws IOException {
		copyInProgerss(pInputStream, pOutputStream, pProgress, END_OF_STREAM);
	}
	
	
	public static final void copyInProgerss(final InputStream pInputStream, final OutputStream pOutputStream, IStreamProgress pProgress, final int pByteLimit) throws IOException {
		
		if(pByteLimit == END_OF_STREAM) {
			final byte[] buf = new byte[IO_BUFFER_SIZE];
			int read;
			while((read = pInputStream.read(buf)) != END_OF_STREAM) {
				pOutputStream.write(buf, 0, read);

				if (!pProgress.onStreamProgressChanged(read)){
					pOutputStream.close();
				}
			}
		} else {
			final byte[] buf = new byte[IO_BUFFER_SIZE];
			final int bufferReadLimit = Math.min((int)pByteLimit, IO_BUFFER_SIZE);
			long pBytesLeftToRead = pByteLimit;

			int read;
			while((read = pInputStream.read(buf, 0, bufferReadLimit)) != END_OF_STREAM) {
				if(pBytesLeftToRead > read) {
					pOutputStream.write(buf, 0, read);
					pBytesLeftToRead -= read;

					if (!pProgress.onStreamProgressChanged(read)){
						pOutputStream.close();
					}
				} else {
					pOutputStream.write(buf, 0, (int) pBytesLeftToRead);

					if (!pProgress.onStreamProgressChanged((int)pBytesLeftToRead)){
						pOutputStream.close();
					}
					break;
				}
			}
		}
		pOutputStream.flush();
	}

	
}
