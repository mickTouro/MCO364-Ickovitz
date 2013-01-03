package ickovitz.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class InitiateSpider {

	public static void main(String args[]) throws IOException {

		LinkedBlockingQueue<String> list = new LinkedBlockingQueue<String>();
		
		list.add("http://www.touro.edu");
		
		Repository repository = new Repository("C:/downloadedSites/");
		SpiderOptions options = new SpiderOptions("touro.edu");

		PageSpider pageSpider1 = new PageSpider(repository, list, options);
		PageSpider pageSpider2 = new PageSpider(repository, list, options);
		PageSpider pageSpider3 = new PageSpider(repository, list, options);
		

		pageSpider1.start();
		
		pageSpider2.start();
		pageSpider3.start();

	}
}
