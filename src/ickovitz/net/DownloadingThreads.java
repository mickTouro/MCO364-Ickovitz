package ickovitz.net;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class DownloadingThreads {

	public static void main(String args[]) throws IOException{
		FileUtils.cleanDirectory(new File("C:\\downloadedSites"));
		PageDownloaderThread pdt = new PageDownloaderThread("http://www.touro.edu");
		pdt.start();
		
	}
}
