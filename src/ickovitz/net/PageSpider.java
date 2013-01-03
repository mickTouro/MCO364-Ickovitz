package ickovitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.io.IOUtils;

public class PageSpider extends Thread {

	private Webpage webpage;
	private Repository repository;
	private SpiderOptions options;
	private LinkedBlockingQueue<String> queue;

	public PageSpider(Repository repository, LinkedBlockingQueue<String> queue,
			SpiderOptions options) {
		this.repository = repository;
		this.queue = queue;
		this.options = options;
	}

	public PageSpider(String url, Repository repository)
			throws MalformedURLException {
		webpage = new Webpage(url);
		this.repository = repository;
	}

	public PageSpider(Repository repository) {
		this.repository = repository;
	}

	public void run() {

		String link;
		try {
			while ((link = queue.take()) != null) {

				try {

					webpage = new Webpage(link);
					HttpURLConnection httpConnection = (HttpURLConnection) webpage
							.getURL().openConnection();

					InputStream in = httpConnection.getInputStream();
					webpage.setHtml(IOUtils.toString(in));
					in.close();
					repository.save(webpage);

					for (URL s : webpage.getLinks()) {
						if (!repository.isCached(s)
								&& s.toString().contains(
										options.getSiteNameContains())) {
							queue.add(s.toString());
						}

					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
