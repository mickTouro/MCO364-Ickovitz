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
	private URL homePage;
	private ArrayList<URL> links;
	private Pattern linkPattern = Pattern.compile("<a.*?href=\"(.+?)\"",
			Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

	public Webpage(URL url) {
		this.url = url;
		findHomePage();
	}

	public Webpage(String url) throws MalformedURLException {
		this(new URL(url));

	}

	public void findHomePage() {
		char[] chars = url.toString().toCharArray();
		String validUrl = "";
		for (int index = 0; index < chars.length; index++) {
			validUrl += chars[index];
			if (validUrl.matches(".*\\.(com|info|net|org|edu|gov)")) {
				try {
					homePage = new URL(validUrl);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public void extractLinks() {
		links = new ArrayList<URL>();

		Matcher matcher = linkPattern.matcher(html);
		while (matcher.find()) {

			try {
				String link = matcher.group(1);

				if (link.charAt(0) == '/') {
					link = homePage + link;
				} else if (link.charAt(0) == '#') {
					continue;

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
		taglessHtml = html.replaceAll("(?s)(?i)(?m)<script.*?</script>", "");
		taglessHtml = taglessHtml.replaceAll("(?s)(?i)(?m)<style.*?</style>", "");
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
	
	public URL getHomePage() {
		return homePage;
	}
}
