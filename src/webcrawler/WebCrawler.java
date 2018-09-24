/**
 * 
 */
package webcrawler;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author dbohlin
 *
 */
public class WebCrawler {
	private Set<Element> links;
	private String baseSite = "";
	
	public String crawl(String site) throws URISyntaxException, MalformedURLException {
		if(isSiteNullorEmpty(site)) {
			return null;
		}
		setLinks();
		setBaseSite(site);
		this.crawlSite(site);
		return this.links.stream()
				.map(pageLink -> pageLink.attr("abs:href"))
				.collect(Collectors.joining("\n"))
				.toString();
	}
	
	private void setLinks() {
		if(null == this.links) {
			this.links = new HashSet<Element>();
		}
	}
	private void crawlSite(String link) {
		if(!this.links.contains(link)) {
			try {
				Document doc = Jsoup.connect(link).get();
				Elements pageLinks = doc.select("a[href]");
				this.links = pageLinks.stream()
				.filter(page -> this.isValidSite(page.attr("abs:href")))
				.collect(Collectors.toSet());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void setBaseSite(String site) throws URISyntaxException, MalformedURLException {
		URI uri = new URL(site).toURI();
		this.baseSite = uri.getHost();
	}
	
	private boolean isSiteNullorEmpty(String site) {
		return null == site || site.isEmpty();
	}
	
	private boolean isValidSite(String site) {
		try {
			URI uri = new URL(site).toURI();
			if(uri.getHost().equalsIgnoreCase(this.baseSite)) {
				return true;
			}
		} catch (MalformedURLException | URISyntaxException e) {
			System.out.println("site ("+site+") is not a valid URI");
		}		
		return false;
	}
}
