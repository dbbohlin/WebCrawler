package webcrawler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestWebCrawler {

	private WebCrawler crawler;

	@BeforeEach
	void setUp() throws Exception {
		crawler = new WebCrawler();
	}

	@Test
	void testWebCrawlerOpenWebSite() {
		crawler.crawl("http://wiprodigital.com");
	}

}