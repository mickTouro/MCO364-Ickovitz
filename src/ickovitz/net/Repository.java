package ickovitz.net;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.FileUtils;

import codeobsessed.MD5.DemoMD5;

public class Repository {

	private File directory;

	public Repository(String directory) throws IOException {
		this(new File(directory));
	}

	public Repository(File directory) throws IOException {
		this.directory = directory;
		this.directory.mkdirs();
		deleteCache();
	}

	public boolean isCached(URL url) throws IOException,
			NoSuchAlgorithmException {
		File md5name = new File(directory + "/" + DemoMD5.MD5(url.toString())
				+ ".txt");

		return md5name.exists();

	}

	public void save(Webpage webpage) throws NoSuchAlgorithmException,
			IOException {
		String md5name = new String(DemoMD5.MD5(webpage.getURL().toString())
				);
		BufferedWriter out = new BufferedWriter(new FileWriter(
				directory.toString() + "/" + md5name + ".txt"));
		webpage.removeTags();
		out.write(webpage.getLinklessHtml());
		out.close();
	}

	public String retrieve(URL url) throws NoSuchAlgorithmException,
			IOException {
		String fileName = DemoMD5.MD5(url.toString()) + ".txt";
		return FileUtils.readFileToString(new File(directory + "/" + fileName));
	
	}

	public void deleteCache() throws IOException {
		FileUtils.cleanDirectory(directory);
	}

	public void setDirectory(String directory) {
		this.directory = new File(directory);
	}

	public File getDirectory() {
		return directory;
	}
}
