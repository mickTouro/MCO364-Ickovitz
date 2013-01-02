package ickovitz.net;

import java.util.ArrayList;

public class SpiderOptions {
	
	private String siteNameContains;
	private ArrayList<String> siteTextContains;
	

	public SpiderOptions(String siteNameContains, ArrayList<String> siteTextContains){
		this.siteNameContains = siteNameContains;
		this.siteTextContains = siteTextContains;
	}

	public SpiderOptions(String siteNameContains){
		this.siteNameContains = siteNameContains;
	}
	
	public String getSiteNameContains() {
		return siteNameContains;
	}

	public void setSiteNameContains(String siteNameContains) {
		this.siteNameContains = siteNameContains;
	}

	public ArrayList<String> getSiteTextContains() {
		return siteTextContains;
	}

	public void setSiteTextContains(ArrayList<String> siteTextContains) {
		this.siteTextContains = siteTextContains;
	}
}
