package ickovitz.net;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import codeobsessed.MD5.DemoMD5;

public class Webpage {
	
	private String html;
	private URL url;
	private ArrayList<URL> links;
	private Pattern pattern = Pattern.compile("<a.*?href=\"(.+?)\"",
			Pattern.CASE_INSENSITIVE);
	
	public Webpage(String url) throws MalformedURLException{
		this.url = new URL(url);
	}
	public Webpage(URL url){
		this.url = url;
	}
	
	public void extractLinks() throws NoSuchAlgorithmException, UnsupportedEncodingException, MalformedURLException{
		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {

			String link = matcher.group(1);

			if (link.charAt(0) == '/') {
				link = url.toString() + link;
			}

			URL url = new URL(link);

			links.add(url);
		}
	}
	
	public void setHtml(String html) throws NoSuchAlgorithmException, UnsupportedEncodingException, MalformedURLException{
		this.html = html;
		extractLinks();
	}
	
	public void setUrl(URL url){
		this.url = url;
	}
	
	public String getHtml(){
		return html;
	}
	
	public URL getURL(){
		return url;
	}
	
	public ArrayList<URL> getLinks(){
		return links;
	}

}
