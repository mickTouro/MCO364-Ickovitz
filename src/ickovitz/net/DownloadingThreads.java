package ickovitz.net;

public class DownloadingThreads {

	public static void main(String args[]){
		PageDownloaderThread pdt = new PageDownloaderThread("http://www.touro.edu");
		pdt.start();
		
	}
}
