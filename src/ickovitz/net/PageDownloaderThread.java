package ickovitz.net;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import codeobsessed.MD5.DemoMD5;

public class PageDownloaderThread extends Thread {

	private String siteName;
	private String siteText;
	private Pattern pattern = Pattern.compile("<a.*?href=\"(.+?)\"",
			Pattern.CASE_INSENSITIVE);
	private ArrayList<String> linksList;

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

			BufferedWriter out = new BufferedWriter(new FileWriter(
					"C:\\downloadedSites/" + DemoMD5.MD5(siteName) + ".txt"));
			out.write(siteText);
			out.close();

			findLinks();

			for (String s : linksList) {
				PageDownloaderThread pdt = new PageDownloaderThread(s);
				pdt.start();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void findLinks() {
		Matcher m = pattern.matcher(siteText);
		linksList = new ArrayList<String>();
		while (m.find()) {
			
			String link = m.group(1);
			
			if(link.charAt(0) == '/'){
				link = siteName + link;
			}
			
			String md5name = DemoMD5.MD5(link) + ".txt";
			
			if(md5name. )
			linksList.add(link);

		}
	}

	public String getSiteText() {
		return siteText;
	}

}
