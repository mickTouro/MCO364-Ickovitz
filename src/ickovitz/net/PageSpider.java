package ickovitz.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;

public class PageSpider extends Thread {

	private Webpage webpage;
	private Repository repository;
	private SpiderOptions options;

	public PageSpider(String url, Repository repository, SpiderOptions options) {
		webpage = new Webpage(url);
		this.repository = repository;
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
		try {
			HttpURLConnection httpConnection = (HttpURLConnection) webpage
					.getURL().openConnection();

			InputStream in = httpConnection.getInputStream();
			webpage.setHtml(IOUtils.toString(in));
			in.close();
			repository.save(webpage);

			for (URL s : webpage.getLinks()) {
				if (!repository.isCached(s)
						&& s.toString().contains(options.getSiteNameContains())) {
					PageSpider newSpider = new PageSpider(s.toString(),
							repository, options);
					newSpider.start();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
