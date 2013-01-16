package ickovitz.net;

import java.io.IOException;

public class FindText {

	public static void main(String args[]) throws IOException{
		Repository repo = new Repository("C:/downloadedSites/");
		repo.searchWithinFiles(" was the only ");
	}
}
