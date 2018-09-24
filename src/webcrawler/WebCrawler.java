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
	private Set<String> links;
	private String baseSite = "";
	private int maxDepth = 1;
	
	public String crawl(String site) throws URISyntaxException, MalformedURLException {
		return this.crawl(site, 1);
	}
	
	public String crawl(String site, int maxDepth) throws URISyntaxException, MalformedURLException {
		if(isSiteNullorEmpty(site)) {
			return null;
		}
		setLinks();
		setBaseSite(site);
		setMaxDepth(maxDepth);
		this.crawlSite(site, 0);
		this.links.add(site);
		return linksToString();

	}
	
	private void crawlSite(String link, int depth) {
		if(!this.links.contains(link) && (depth < this.maxDepth)) {
			try {
				Document doc = Jsoup.connect(link).get();
				Elements pageLinks = doc.select("a[href]");
				++depth;
				for(Element pageLink : pageLinks) {
					String linkSite = pageLink.attr("abs:href");
					if(this.isValidSite(linkSite)) {
						this.links.add(linkSite);
						crawlSite(linkSite, depth);
					}	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void setLinks() {
		if(null == this.links) {
			this.links = new HashSet<String>();
		}
	}
	
	private void setBaseSite(String site) throws URISyntaxException, MalformedURLException {
		URI uri = new URL(site).toURI();
		this.baseSite = uri.getHost();
	}

	private void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
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
	
	private String linksToString() {
		return this.links.stream()
				.collect(Collectors.joining("\n"))
				.toString();
	}
	
	private static String instructions() {
		StringBuilder builder = new StringBuilder();
		builder.append("usage: java -jar webcrawler.jar <WebSite> [depth]\n");
		builder.append("    example: java -jar webcrawler.jar http://wiprodigital.com 2");
		return builder.toString();
	}
	
	public static void main(String[] args) {
		WebCrawler crawler = new WebCrawler();
		
		if(args.length > 0) {
			String site = args[0];
			Integer depth = new Integer(1);
			try {
				URI uri = new URL(site).toURI();
			} catch (MalformedURLException | URISyntaxException e) {
				System.err.println("the site " + args[0] + " is not a valid site");
				System.out.println(WebCrawler.instructions());
			}
			if(args.length > 1) {
				try {
					depth = Integer.parseInt(args[1]);
					if(depth < 1) {
						throw new NumberFormatException();
					}
				} catch (NumberFormatException e) {
					System.err.println("this depth ("+args[1]+") is not a valid integer");
					System.out.println(WebCrawler.instructions());
				}
			}
			try {
				System.out.println(crawler.crawl(site, depth.intValue()));
			} catch (MalformedURLException | URISyntaxException e) {
				System.err.println("Error occurred while trying to crawl " + site + ", Exception: " + e.getLocalizedMessage());
			}
		}else {
			System.out.println(WebCrawler.instructions());
		}
	}
}
