package ickovitz.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class InitiateSpider {

	public static void main(String args[]) throws IOException{
				
		Repository repository = new Repository("C:\\downloadedSites\\");
		
		try {
			PageSpider pageSpider = new PageSpider("http://www.touro.edu", repository);
			pageSpider.start();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
