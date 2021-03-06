package ickovitz.net;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

public class InitiateSpider {

	public static void main(String args[]) throws IOException {

		LinkedBlockingQueue<URL> list = new LinkedBlockingQueue<URL>();

		list.add(new URL("http://en.wikipedia.org/wiki/Ferarri"));

		Repository repository = new Repository("C:/downloadedSites/");
		SpiderOptions options = new SpiderOptions("wikipedia.org");

		PageSpider pageSpider1 = new PageSpider(repository, list, options);
		PageSpider pageSpider2 = new PageSpider(repository, list, options);
		PageSpider pageSpider3 = new PageSpider(repository, list, options);

		pageSpider1.start();
		pageSpider2.start();
		pageSpider3.start();

	}
}
