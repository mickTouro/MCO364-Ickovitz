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
	private Pattern p = Pattern.compile("<a href=\"(.+?)\"");
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
					"downloadedSites/" + DemoMD5.MD5(siteName) + ".txt"));
			out.write(siteText);
			out.close();

			findLinks();
			
			for(String s: linksList){
				PageDownloaderThread pdt = new PageDownloaderThread(s);
				pdt.start();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void findLinks() {
		Matcher m = p.matcher(siteText);
		linksList = new ArrayList<String>();
		for (; m.find();) {
			linksList.add(m.group().substring(9, m.group().length() -1));

		}
	}

	public String getSiteText() {
		return siteText;
	}

}
