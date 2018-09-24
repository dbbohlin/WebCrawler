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
 * The crawler should be limited to one domain. Given a starting URL – say [http://wiprodigital.com](http://wiprodigital.com) - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.
 *
 * The output should be a simple structured site map (this does not need to be a traditional XML sitemap - just some sort of output to reflect what your crawler has discovered) showing links to other pages under the same domain, links to external URLs, and links to static content such as images for each respective page.
 *
 */
public class WebCrawler {
	private Set<String> links;
	private Set<String> outSideLinks;
	private Set<String> staticLinks;
	private String baseSite = "";
	private int maxDepth = 1;
	
	/**
	 * Callable function for crawling a web site, depth is set to only the first page
	 * 
	 * @param site
	 * @return
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
	public String crawl(String site) throws URISyntaxException, MalformedURLException {
		return this.crawl(site, 1);
	}
	
	/**
	 * Callable function for crawling a web site and the depth desired
	 * 
	 * @param site
	 * @param maxDepth
	 * @return
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
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
	
	/**
	 * Recursive function to find all links
	 * 
	 * @param link
	 * @param depth
	 */
	private void crawlSite(String link, int depth) {
		if(depth < this.maxDepth) {
			try {
				Document doc = Jsoup.connect(link).get();
				Elements pageLinks = doc.select("a[href]");
				Elements srcLinks = doc.select("[src]");
				++depth;
				for(Element srcLink : srcLinks) {
					String srcString;
					if(srcLink.tagName().equals("img")) {
						srcString = String.format("%s: <%s> %sx%s (%s)", srcLink.tagName(), srcLink.attr("abs:src"), srcLink.attr("width"), srcLink.attr("height"), trim(srcLink.attr("alt"), 20));
					}else {
						srcString = String.format("%s: <%s>", srcLink.tagName(), srcLink.attr("abs:src"));
					}
					this.staticLinks.add(srcString);
				}
				for(Element pageLink : pageLinks) {
					String linkSite = pageLink.attr("abs:href");
					if(this.isValidSite(linkSite)) {
						this.links.add(linkSite);
						crawlSite(linkSite, depth);
					}else {
						this.outSideLinks.add(linkSite);
					}
				}
			} catch (IOException e) {
				System.out.println("An error occurred parsing the link ("+link+"), Exception: "+e.getLocalizedMessage());
			}
			
		}
	}
	
	/**
	 * Instantiates the HashSets
	 * 
	 */
	private void setLinks() {
		if(null == this.links) {
			this.links = new HashSet<String>();
		}
		if(null == this.outSideLinks) {
			this.outSideLinks = new HashSet<String>();
		}
		if(null == this.staticLinks) {
			this.staticLinks = new HashSet<String>();
		}
	}
	
	/**
	 * Verifies the base site and sets the domain's host for verification 
	 * 
	 * @param site
	 * @throws URISyntaxException
	 * @throws MalformedURLException
	 */
	private void setBaseSite(String site) throws URISyntaxException, MalformedURLException {
		URI uri = new URL(site).toURI();
		this.baseSite = uri.getHost();
	}

	/**
	 * Sets the maxDepth field to the maxDepth parameter
	 * 
	 * @param maxDepth
	 */
	private void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	
	/**
	 * Checks if site string is empty or null
	 * 
	 * @param site
	 * @return
	 */
	private boolean isSiteNullorEmpty(String site) {
		return null == site || site.isEmpty();
	}
	
	/**
	 * Checks if the site is the same domain as the hosting domain
	 * 
	 * @param site
	 * @return
	 */
	private boolean isValidSite(String site) {
		try {
			URI uri = new URL(site).toURI();
			if(uri.getHost().equalsIgnoreCase(this.baseSite)) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("site ("+site+") is not a valid URI, Exception: "+e.getLocalizedMessage());
		}		
		return false;
	}
	
	/**
	 * Returns the data as a string. Data is separated by tag lines with :
	 * 
	 * @return
	 */
	private String linksToString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Internal Links:\n");
		builder.append(this.links.stream()
				.collect(Collectors.joining("\n"))
				.toString());
		builder.append("\nMedia/Static Links: \n");
		builder.append(this.staticLinks.stream()
				.collect(Collectors.joining("\n"))
				.toString());
		builder.append("\nOutSide Links:\n");
		builder.append(this.outSideLinks.stream()
				.collect(Collectors.joining("\n"))
				.toString());
		return builder.toString();
	}
	
	/**
	 * Trims the string to the size indicated or returns the string if it is smaller that the size
	 * 
	 * @param str
	 * @param size
	 * @return
	 */
	private String trim(String str, int size) {
		if(str.length() > size) {
			return str.substring(0, size -1);
		}
		return str;
	}
	
	/**
	 * Simple instructions on how to run the jar file from the command line
	 * 
	 * @return
	 */
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
