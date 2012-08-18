package org.sig.crawler.fill_image.console_app;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Làm việc với web, load file về và lưu lại
 * @author haidang-ubuntu
 *
 */
public final class FileLeecher {

	/**
	 * Singleton pattern
	 */
	private static FileLeecher instance_;
	/**
	 * Default size each buffer to read from server
	 */
	private static final int DEFAULT_PER_BUFFER_SIZE = 65536;//1 << 16;

	/**
	 * Singleton pattern
	 */
	private FileLeecher() { }

	/**
	 * Singleton pattern
	 * @return singleton instance
	 */
	public static FileLeecher getInstance() {
		if (instance_ == null) {
			instance_ = new FileLeecher();
		}
		return instance_;
	}

	/** Get URL and write down page content to file
	 * @param iurl URL to leech
	 * @param fileName file name to write to. If exist
	 * @return true if success
	 */
	public boolean leechFile(final String iurl, final String fileName) {
		InputStream reader = null;
		FileOutputStream writer = null;
		boolean result;
		try {
			URL url = new URL(iurl);
			URI uri = new URI(url.getProtocol(),
					url.getUserInfo(),
					url.getHost(),
					url.getPort(),
					url.getPath(),
					url.getQuery(),
					url.getRef());
			url = uri.toURL();
			url.openConnection();
			reader = url.openStream();
			writer = new FileOutputStream(fileName);
			byte[] buffer = new byte[DEFAULT_PER_BUFFER_SIZE];
			int bytesRead = 0;
			while ((bytesRead = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, bytesRead);
				buffer = new byte[DEFAULT_PER_BUFFER_SIZE];
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
