package ickovitz.net;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.FileUtils;

import codeobsessed.MD5.DemoMD5;

public class Repository {

	private File directory = new File("C:\\downloadedSites/");

	public Repository(String directory) {
		this.directory = new File(directory);
	}
	
	public Repository(File directory){
		this.directory = directory;
	}

	public boolean isCached(URL url) throws NoSuchAlgorithmException,
			IOException {
		File md5name = new File(DemoMD5.MD5(url.toString()) + ".txt");

		return FileUtils.directoryContains(directory, md5name);

	}

	public void save(Webpage webpage) throws NoSuchAlgorithmException,
			IOException {
		String md5name = new String(DemoMD5.MD5(webpage.getURL().toString())
				+ ".txt");
		BufferedWriter out = new BufferedWriter(new FileWriter(directory
				+ md5name + ".txt"));
		out.write(webpage.getHtml());
		out.close();
	}

	public void deleteCache() throws IOException {
		FileUtils.cleanDirectory(directory);
	}

	public void setDirectory(String directory) {
		this.directory = new File(directory);
	}

}
