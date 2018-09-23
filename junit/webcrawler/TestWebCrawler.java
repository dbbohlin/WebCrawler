package webcrawler;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TestWebCrawler {

	private WebCrawler crawler;

	@BeforeEach
	void setUp() throws Exception {
		crawler = new WebCrawler();
	}

	@Test
	void testWebCrawlerNullorEmptySiteReturnsNull() {
		try {
			assertEquals(null, crawler.crawl(null));
		} catch (URISyntaxException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testWebCrawlerOpenWebSite() {
		String crawlMap = null;
		try {
			crawlMap = crawler.crawl("http://wiprodigital.com");
		} catch (URISyntaxException | MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println(crawlMap);
	}
	
	@Test
	void testWebCrawlerBadSiteThrowsURISyntaxException() {
		Assertions.assertThrows(URISyntaxException.class, () ->{
					crawler.crawl("http://www. thest.com");
				});
	}
	
	@Test
	void testWebCrawlerBadSiteThrowsMalformedURLException() {
		Assertions.assertThrows(MalformedURLException.class, () ->{
					crawler.crawl("thest.com");
				});
	}
}
