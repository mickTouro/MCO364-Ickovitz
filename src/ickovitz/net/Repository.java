package ickovitz.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
		// deleteCache();
	}

	public ArrayList<String> searchFiles(String keyword) throws IOException {

		ArrayList<String> list = new ArrayList<String>();

		for (File file : directory.listFiles()) {
			String fileText = FileUtils.readFileToString(file);
			if (fileText.contains(keyword)) {
				list.add(file.getName());
			}
		}
		return list;

	}

	public void searchWithinFiles(String keyword) throws IOException {
		String textToDisplay = "\"" + keyword + "\" found in: \n";
		String fileText;
		int index;
		int begIndex;
		int endIndex;
		int length;
		for (File file : directory.listFiles()) {
			fileText = FileUtils.readFileToString(file);
			index = fileText.indexOf(keyword);

			if (index == -1) {
				continue;
			} else {
				textToDisplay += "\n" + file.toString() + ":\n";
				length = fileText.length();

				do {
					if (index <= 50) {
						begIndex = 0;
					} else {
						begIndex = index - 50;
					}

					if (index + 49 >= length) {
						endIndex = length - 1;
					} else {
						endIndex = index + 50;
					}

					textToDisplay += fileText.substring(begIndex, endIndex) + "\n";
					index = fileText.indexOf(keyword, index + 1);

				} while (index != -1);
			}
		}
		System.out.println(textToDisplay);
	}

	public boolean isCached(URL url) throws IOException,
			NoSuchAlgorithmException {
		File md5name = new File(directory + "/" + DemoMD5.MD5(url.toString())
				+ ".txt");

		return md5name.exists();

	}

	public void save(Webpage webpage) throws NoSuchAlgorithmException,
			IOException {
		String md5name = new String(DemoMD5.MD5(webpage.getURL().toString()));
		BufferedWriter out = new BufferedWriter(new FileWriter(
				directory.toString() + "/" + md5name + ".txt"));

		webpage.removeTags();
		out.write(webpage.getTaglessHtml());
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
