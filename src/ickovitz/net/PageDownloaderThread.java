package ickovitz.net;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
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
			downloadSiteText();

			findLinks();

			for (String s : linksList) {
				PageDownloaderThread pdt = new PageDownloaderThread(s);
				pdt.start();
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void downloadSiteText() throws MalformedURLException, IOException,
			NoSuchAlgorithmException, UnsupportedEncodingException {
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
	}

	public void findLinks() throws NoSuchAlgorithmException, IOException {
		Matcher matcher = pattern.matcher(siteText);
		linksList = new ArrayList<String>();
		File directory = new File("C:\\downloadedSites/");
		while (matcher.find()) {

			String link = matcher.group(1);

			if (link.charAt(0) == '/') {
				link = siteName + link;
			}

			File md5name = new File(DemoMD5.MD5(link) + ".txt");

			if (!FileUtils.directoryContains(directory, md5name)) {
				linksList.add(link);
			}
		}
	}
	
	public ArrayList<String> getLinksList(){
		return linksList;
	}

	public String getSiteText() {
		return siteText;
	}

}
