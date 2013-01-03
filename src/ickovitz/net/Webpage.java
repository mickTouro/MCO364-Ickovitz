package ickovitz.net;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Webpage {

	private String html;
	private String taglessHtml;
	private URL url;
	private ArrayList<URL> links;
	private Pattern linkPattern = Pattern.compile("<a.*?href=\"(.+?)\"",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

	public Webpage(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public Webpage(URL url) {
		this.url = url;
	}

	public void extractLinks() {
		links = new ArrayList<URL>();

		Matcher matcher = linkPattern.matcher(html);
		while (matcher.find()) {

			try {
				String link = matcher.group(1);

				if (link.charAt(0) == '/') {
					link = url.toString() + link;
				} else if (!link.startsWith("http:")) {
					link = url.toString() + "/" + link;
				}

				URL newUrl;
				newUrl = new URL(link);

				links.add(newUrl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeTags() {
		taglessHtml = html.replaceAll("(?i)<script.*?</script>", "");

		taglessHtml = taglessHtml.replaceAll("<(.|\n)*?>", "");
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

	public String getTaglessHtml() {
		return taglessHtml;
	}

	public URL getURL() {
		return url;
	}

	public ArrayList<URL> getLinks() {
		return links;
	}
}
