/**
 * 
 */
package webcrawler;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author dbohlin
 *
 */
public class WebCrawler {
	private HashSet<String> links;
	private String baseSite = "";
	
	public void webCrawler() {
		this.links = new HashSet<String>();
	}
	
	public String crawl(String site) throws URISyntaxException, MalformedURLException {
		if(isSiteNullorEmpty(site)) {
			return null;
		}
		setBaseSite(site);
		StringBuilder builder = new StringBuilder();
		
		return builder.toString();
	}
		
	private void setBaseSite(String site) throws URISyntaxException, MalformedURLException {
		URI uri = new URL(site).toURI();
		this.baseSite = uri.getHost();
	}
	
	private boolean isSiteNullorEmpty(String site) {
		return null == site || site.isEmpty();
	}

}
