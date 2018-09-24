package webcrawler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
			assertNotNull(crawler.crawl("http://wiprodigital.com"));
		} catch (URISyntaxException | MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testWebCrawlerBadSiteThrowsURISyntaxException() {
		assertThrows(URISyntaxException.class, () ->{
					crawler.crawl("http://www. thest.com");
				});
	}
	
	@Test
	void testWebCrawlerBadSiteThrowsMalformedURLException() {
		assertThrows(MalformedURLException.class, () ->{
					crawler.crawl("thest.com");
				});
	}
	
	@Test
	void testWebCrawlerCrawlWiprodigitalReturnsString() {
		String expectedReturn = "https://wiprodigital.com/events/wipro-announces-platinum-sponsorship-at-ciab-febraban-2018/\n" + 
				"https://wiprodigital.com/who-we-are#wdteam_meetus\n" + 
				"https://wiprodigital.com/privacy-policy\n" + 
				"https://wiprodigital.com/join-our-team#wdcareers_jobs\n" + 
				"https://wiprodigital.com/2018/09/18/sound-design-from-the-ears-of-a-motion-designer/\n" + 
				"https://wiprodigital.com/#CascontntAggr\n" + 
				"https://wiprodigital.com/#AllcontntAggr\n" + 
				"https://wiprodigital.com/?s=&post_type[]=cases\n" + 
				"https://wiprodigital.com/events/wipro-digital-designit-at-idc-european-digital-executive-summit-2018/\n" + 
				"https://wiprodigital.com/cases/in24/\n" + 
				"https://wiprodigital.com/cases/increasing-customer-value-through-iot-for-jcb-india/\n" + 
				"https://wiprodigital.com/news/pacs-european-trend-study-digital-industrial-revolution-with-predictive-maintenance/\n" + 
				"https://wiprodigital.com/get-in-touch#wddi-contact\n" + 
				"https://wiprodigital.com/?s=&post_type[]=post\n" + 
				"https://wiprodigital.com/who-we-are#wdteam_leaders\n" + 
				"https://wiprodigital.com/?s=&post_type[]=events\n" + 
				"https://wiprodigital.com/events/teresa-brazen-senior-director-cooper-at-design-thinking-2018/\n" + 
				"http://wiprodigital.com\n" + 
				"https://wiprodigital.com/who-we-are/#wdteam-vid\n" + 
				"https://wiprodigital.com/join-our-team#wdwork-vid\n" + 
				"https://wiprodigital.com/2018/08/28/continuous-iteration-not-preparation/\n" + 
				"https://wiprodigital.com/what-we-think\n" + 
				"https://wiprodigital.com/news/new-survey-highlights-leadership-crisis-digital-transformation/\n" + 
				"https://wiprodigital.com/#EvtcontntAggr\n" + 
				"https://wiprodigital.com/2018/08/09/turning-data-into-consumer-growth/\n" + 
				"https://wiprodigital.com/#NewcontntAggr\n" + 
				"https://wiprodigital.com\n" + 
				"https://wiprodigital.com/what-we-do#wdwork_cases\n" + 
				"https://wiprodigital.com/news/wipro-named-global-leader-digital-transformation-strategy-consulting/\n" + 
				"https://wiprodigital.com/cases/case-study-speaking-from-the-heart/\n" + 
				"https://wiprodigital.com/news/wipro-digital-acquire-cooper-leader-ux-interaction-design-expand-designits-capabilities/\n" + 
				"https://wiprodigital.com/#InscontntAggr\n" + 
				"https://wiprodigital.com/cases/a-design-system-to-enable-future-growth/\n" + 
				"https://wiprodigital.com/what-we-do#wdwork_partners\n" + 
				"https://wiprodigital.com/cases/progressive-metering-a-utilitys-strategic-move-into-predictive-planning/\n" + 
				"https://wiprodigital.com/what-we-do#work-three-circles-row\n" + 
				"https://wiprodigital.com/events/wipro-digital-is-a-platinum-sponsor-at-springone-platform-2018/\n" + 
				"https://wiprodigital.com/who-we-are\n" + 
				"https://wiprodigital.com/news/vendor-profile-digital-cx-iot-by-pac-2018/\n" + 
				"https://wiprodigital.com/events/nate-clinton-managing-director-cooper-at-sf-design-week-2018/\n" + 
				"https://wiprodigital.com/get-in-touch\n" + 
				"https://wiprodigital.com/2018/09/20/the-emergence-of-intelligent-mobile-apps/\n" + 
				"https://wiprodigital.com/?s=&post_type[]=news\n" + 
				"https://wiprodigital.com/what-we-do\n" + 
				"https://wiprodigital.com/get-in-touch#wddi-locations\n" + 
				"https://wiprodigital.com/case-study-redefining-newborn-care-for-volusense/\n" + 
				"https://wiprodigital.com/designit-approach\n" + 
				"https://wiprodigital.com/2018/08/07/making-financial-advice-affordable-to-the-masses-in-the-age-of-digitalisation/\n" + 
				"https://wiprodigital.com/2018/08/30/rethinking-service-blueprints-for-agile-delivery/\n" + 
				"https://wiprodigital.com/designit-approach/\n" + 
				"https://wiprodigital.com/join-our-team\n" + 
				"https://wiprodigital.com/news/the-state-of-martech-study/\n" + 
				"https://wiprodigital.com/events/join-wipro-digital-for-two-iot-panels-at-world-economic-forum-wef/\n" + 
				"https://wiprodigital.com/join-our-team#wdcareers_team";

		try {
			String returnString = crawler.crawl("http://wiprodigital.com");
			assertEquals(expectedReturn, returnString);
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testWebCrawlerCrawlWiprodigitalDepth1_ReturnsString() {
		String expectedReturn = "https://wiprodigital.com/events/wipro-announces-platinum-sponsorship-at-ciab-febraban-2018/\n" + 
				"https://wiprodigital.com/who-we-are#wdteam_meetus\n" + 
				"https://wiprodigital.com/privacy-policy\n" + 
				"https://wiprodigital.com/join-our-team#wdcareers_jobs\n" + 
				"https://wiprodigital.com/2018/09/18/sound-design-from-the-ears-of-a-motion-designer/\n" + 
				"https://wiprodigital.com/#CascontntAggr\n" + 
				"https://wiprodigital.com/#AllcontntAggr\n" + 
				"https://wiprodigital.com/?s=&post_type[]=cases\n" + 
				"https://wiprodigital.com/events/wipro-digital-designit-at-idc-european-digital-executive-summit-2018/\n" + 
				"https://wiprodigital.com/cases/in24/\n" + 
				"https://wiprodigital.com/cases/increasing-customer-value-through-iot-for-jcb-india/\n" + 
				"https://wiprodigital.com/news/pacs-european-trend-study-digital-industrial-revolution-with-predictive-maintenance/\n" + 
				"https://wiprodigital.com/get-in-touch#wddi-contact\n" + 
				"https://wiprodigital.com/?s=&post_type[]=post\n" + 
				"https://wiprodigital.com/who-we-are#wdteam_leaders\n" + 
				"https://wiprodigital.com/?s=&post_type[]=events\n" + 
				"https://wiprodigital.com/events/teresa-brazen-senior-director-cooper-at-design-thinking-2018/\n" + 
				"http://wiprodigital.com\n" + 
				"https://wiprodigital.com/who-we-are/#wdteam-vid\n" + 
				"https://wiprodigital.com/join-our-team#wdwork-vid\n" + 
				"https://wiprodigital.com/2018/08/28/continuous-iteration-not-preparation/\n" + 
				"https://wiprodigital.com/what-we-think\n" + 
				"https://wiprodigital.com/news/new-survey-highlights-leadership-crisis-digital-transformation/\n" + 
				"https://wiprodigital.com/#EvtcontntAggr\n" + 
				"https://wiprodigital.com/2018/08/09/turning-data-into-consumer-growth/\n" + 
				"https://wiprodigital.com/#NewcontntAggr\n" + 
				"https://wiprodigital.com\n" + 
				"https://wiprodigital.com/what-we-do#wdwork_cases\n" + 
				"https://wiprodigital.com/news/wipro-named-global-leader-digital-transformation-strategy-consulting/\n" + 
				"https://wiprodigital.com/cases/case-study-speaking-from-the-heart/\n" + 
				"https://wiprodigital.com/news/wipro-digital-acquire-cooper-leader-ux-interaction-design-expand-designits-capabilities/\n" + 
				"https://wiprodigital.com/#InscontntAggr\n" + 
				"https://wiprodigital.com/cases/a-design-system-to-enable-future-growth/\n" + 
				"https://wiprodigital.com/what-we-do#wdwork_partners\n" + 
				"https://wiprodigital.com/cases/progressive-metering-a-utilitys-strategic-move-into-predictive-planning/\n" + 
				"https://wiprodigital.com/what-we-do#work-three-circles-row\n" + 
				"https://wiprodigital.com/events/wipro-digital-is-a-platinum-sponsor-at-springone-platform-2018/\n" + 
				"https://wiprodigital.com/who-we-are\n" + 
				"https://wiprodigital.com/news/vendor-profile-digital-cx-iot-by-pac-2018/\n" + 
				"https://wiprodigital.com/events/nate-clinton-managing-director-cooper-at-sf-design-week-2018/\n" + 
				"https://wiprodigital.com/get-in-touch\n" + 
				"https://wiprodigital.com/2018/09/20/the-emergence-of-intelligent-mobile-apps/\n" + 
				"https://wiprodigital.com/?s=&post_type[]=news\n" + 
				"https://wiprodigital.com/what-we-do\n" + 
				"https://wiprodigital.com/get-in-touch#wddi-locations\n" + 
				"https://wiprodigital.com/case-study-redefining-newborn-care-for-volusense/\n" + 
				"https://wiprodigital.com/designit-approach\n" + 
				"https://wiprodigital.com/2018/08/07/making-financial-advice-affordable-to-the-masses-in-the-age-of-digitalisation/\n" + 
				"https://wiprodigital.com/2018/08/30/rethinking-service-blueprints-for-agile-delivery/\n" + 
				"https://wiprodigital.com/designit-approach/\n" + 
				"https://wiprodigital.com/join-our-team\n" + 
				"https://wiprodigital.com/news/the-state-of-martech-study/\n" + 
				"https://wiprodigital.com/events/join-wipro-digital-for-two-iot-panels-at-world-economic-forum-wef/\n" + 
				"https://wiprodigital.com/join-our-team#wdcareers_team";
		try {
			String returnString = crawler.crawl("http://wiprodigital.com", 1);
			assertEquals(expectedReturn, returnString);
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testWebCrawlerCrawlLoadImpactDepth2_ReturnsString() {
		String expectedReturn = "https://support.loadimpact.com/4.0/test-scripting/environment-variables/\n" + 
				"http://support.loadimpact.com/\n" + 
				"https://support.loadimpact.com/4.0/test-running/\n" + 
				"https://support.loadimpact.com/4.0/test-running/cloud-execution/\n" + 
				"https://support.loadimpact.com/4.0/result-analysis/insights-url-table/\n" + 
				"https://support.loadimpact.com/4.0/how-to-tutorials/load-testing-with-postman-collections/\n" + 
				"https://support.loadimpact.com/4.0/getting-started/load-impact-trial-guide/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/load-test-ramping-configurations/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/\n" + 
				"https://support.loadimpact.com/4.0/result-analysis/insights-filters/\n" + 
				"https://support.loadimpact.com/4.0/integrations/\n" + 
				"https://support.loadimpact.com/4.0/getting-started/\n" + 
				"https://support.loadimpact.com/4.0/how-to-tutorials/how-to-do-browser-recording/\n" + 
				"https://support.loadimpact.com/4.0/integrations/jenkins-integration/\n" + 
				"https://support.loadimpact.com/4.0/how-to-tutorials/\n" + 
				"https://support.loadimpact.com/4.0/getting-started/hello-world/\n" + 
				"https://support.loadimpact.com/4.0/frequently-asked-questions/why-should-i-filter-domains/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/intro/\n" + 
				"https://support.loadimpact.com/4.0/how-to-tutorials/how-to-convert-har-to-k6-test/\n" + 
				"https://support.loadimpact.com/4.0/team-project-management/\n" + 
				"https://support.loadimpact.com/4.0/getting-started/sample-script/\n" + 
				"https://support.loadimpact.com/4.0/result-analysis/insights-analysis-view/\n" + 
				"https://support.loadimpact.com/4.0/team-project-management/projects/\n" + 
				"https://support.loadimpact.com/4.0/frequently-asked-questions/what-ip-addresses-are-used-by-load-impact/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/test-structure-breakdown/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/checks/\n" + 
				"https://support.loadimpact.com/4.0/team-project-management/organizations/\n" + 
				"https://support.loadimpact.com/\n" + 
				"https://support.loadimpact.com/4.0/test-running/local-on-premise-execution/\n" + 
				"https://support.loadimpact.com/4.0/getting-started/next-steps/\n" + 
				"https://support.loadimpact.com/4.0/result-analysis/insights-overview/\n" + 
				"https://support.loadimpact.com/4.0/pricing/pricing-faq/\n" + 
				"https://support.loadimpact.com/4.0/migrating-lua-to-js/migration-overview/\n" + 
				"https://support.loadimpact.com/4.0/integrations/circleci-integration/\n" + 
				"https://support.loadimpact.com/4.0/frequently-asked-questions/\n" + 
				"https://support.loadimpact.com/4.0/team-project-management/adding-team-members/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/test-configuration-options/\n" + 
				"https://support.loadimpact.com/4.0/\n" + 
				"https://support.loadimpact.com/4.0/pricing/\n" + 
				"https://support.loadimpact.com/4.0/getting-started/load-test-preparations/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/modules-imports/\n" + 
				"https://support.loadimpact.com/4.0/result-analysis/insights-performance-overview/\n" + 
				"https://support.loadimpact.com/4.0/getting-started/product-overview/\n" + 
				"https://support.loadimpact.com/4.0/migrating-lua-to-js/lua-to-js/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/handling-dynamic-tokens-in-k6-scripts/\n" + 
				"https://support.loadimpact.com/4.0/test-running/logging-into-cloud-service-from-k6/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/thresholds/\n" + 
				"https://support.loadimpact.com/4.0/result-analysis/insights-break-down-tree/\n" + 
				"https://support.loadimpact.com/4.0/how-to-tutorials/how-to-open-firewall-to-load-impact-only/\n" + 
				"https://support.loadimpact.com/4.0/#\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/tags/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/custom-metrics/\n" + 
				"https://support.loadimpact.com/4.0/migrating-lua-to-js/\n" + 
				"https://support.loadimpact.com/4.0/result-analysis/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/http-authentication/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/test-setup-teardown-life-cycle/\n" + 
				"https://support.loadimpact.com/4.0/test-scripting/main-function/";
		try {
			String returnString = crawler.crawl("https://support.loadimpact.com/4.0/", 2);
			assertEquals(expectedReturn, returnString);
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
