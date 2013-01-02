package ickovitz.net;

import java.io.IOException;
import java.net.MalformedURLException;

public class InitiateSpider {

	public static void main(String args[]) throws IOException {

		Repository repository = new Repository("C:/downloadedSites/");
		SpiderOptions options = new SpiderOptions("touro.edu");

		PageSpider pageSpider = new PageSpider("http://www.touro.edu",
				repository, options);
		pageSpider.start();

	}
}
