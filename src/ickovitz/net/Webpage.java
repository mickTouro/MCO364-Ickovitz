package ickovitz.net;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Webpage {

	private String html;
	private String linklessHtml;
	private URL url;
	private ArrayList<URL> links;
	private Pattern pattern = Pattern.compile("<a.*?href=\"(.+?)\"",
			Pattern.CASE_INSENSITIVE);

	public Webpage(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Webpage(URL url) {
		this.url = url;
	}

	public void extractLinks() {
		links = new ArrayList<URL>();

		Matcher matcher = pattern.matcher(html);
		while (matcher.find()) {

			try {
				String link = matcher.group(1);

				if (link.charAt(0) == '/') {
					link = url.toString() + link;
				}

				URL newUrl;

				newUrl = new URL(link);

				links.add(newUrl);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void removeLinks(){
		linklessHtml = html.replaceAll("(?s)<.*?>", "\n");
		
	}
	
	public void setHtml(String html) {
		this.html = html;
		extractLinks();
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getHtml() {
		return html;
	}
	
	public String getLinklessHtml(){
		return linklessHtml;
	}

	public URL getURL() {
		return url;
	}

	public ArrayList<URL> getLinks() {
		return links;
	}

}
