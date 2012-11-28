package ickovitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.commons.io.IOUtils;

public class PageDownloaderThread extends Thread {

	private String siteName;
	private String siteText;

	public PageDownloaderThread(String webpage) {
		siteName = webpage;
	}

	public void run() {
		try {
			URL url = new URL(siteName);
			HttpURLConnection httpConnection = (HttpURLConnection) url
					.openConnection();

			InputStream in = httpConnection.getInputStream();
			siteText = IOUtils.toString(in);
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public String getSiteText(){
		return siteText;
	}

}
