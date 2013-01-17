package ickovitz.net;

import java.io.IOException;

public class FindText {

	public static void main(String args[]) throws IOException {
		Repository repo = new Repository("C:/downloadedSites/");
		System.out.println(repo.searchFiles("car", "steering", "wheel", "tire",
				"engine", "lights", "bumper").toString());
		// repo.searchWithinFiles("automatic transmission");
	}
}
