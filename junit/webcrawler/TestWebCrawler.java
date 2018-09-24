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
	
	String expectedReturnDepth1 = "Internal Links:\n" + 
			"https://wiprodigital.com/events/wipro-announces-platinum-sponsorship-at-ciab-febraban-2018/\n" + 
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
			"https://wiprodigital.com/join-our-team#wdcareers_team\n" + 
			"Media/Static Links: \n" + 
			"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/bootstrap.min.js>\n" + 
			"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/mason.js>\n" + 
			"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo.png> x (Digital Transformat)\n" + 
			"img: <https://dc.ads.linkedin.com/collect/?pid=225025&fmt=gif> 1x1 ()\n" + 
			"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wiprodigital.js>\n" + 
			"script: <https://js.hsforms.net/forms/v2.js>\n" + 
			"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo.png> x (wipro digital)\n" + 
			"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/inview.js>\n" + 
			"script: <https://js.hs-scripts.com/621527.js>\n" + 
			"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wdhome.js>\n" + 
			"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/jquery.slicknav.min.js>\n" + 
			"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/modernizr.min.js>\n" + 
			"script: <https://s17776.pcdn.co/wp-includes/js/jquery/jquery.js?ver=1.12.4>\n" + 
			"script: <https://s17776.pcdn.co/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.4.1>\n" + 
			"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/jquery-1.12.0.min.js>\n" + 
			"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo-dk.png> x (wipro digital)\n" + 
			"script: <https://s17776.pcdn.co/wp-includes/js/wp-embed.min.js?ver=4.9.7>\n" + 
			"script: <https://s17776.pcdn.co/wp-content/plugins/wp-scroll-depth/js/jquery-scrolldepth/jquery.scrolldepth.min.js?ver=4.9.7>\n" + 
			"OutSide Links:\n" + 
			"https://twitter.com/wiprodigital\n" + 
			"https://www.linkedin.com/company/wipro-digital\n" + 
			"https://www.facebook.com/WiproDigital/";

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

		try {
			String returnString = crawler.crawl("http://wiprodigital.com");
			assertEquals(expectedReturnDepth1, returnString);
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testWebCrawlerCrawlWiprodigitalDepth1_ReturnsString() {
		try {
			String returnString = crawler.crawl("http://wiprodigital.com", 1);
			assertEquals(expectedReturnDepth1, returnString);
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testWebCrawlerCrawlWiprodigitalDepth2_ReturnsString() {
		String expectedReturn = "Internal Links:\n" + 
				"https://wiprodigital.com/2018/07/26/caas-102-getting-started-with-content-as-a-service/\n" + 
				"https://wiprodigital.com/events/wipro-announces-platinum-sponsorship-at-ciab-febraban-2018/\n" + 
				"https://wiprodigital.com/who-we-are#wdteam_meetus\n" + 
				"https://wiprodigital.com/2018/06/14/the-big-thing-missing-from-collision-2018-is-whats-troubling-about-the-startup-world/\n" + 
				"https://wiprodigital.com/what-we-do/#masthead\n" + 
				"https://wiprodigital.com/#CascontntAggr\n" + 
				"https://wiprodigital.com/cases/increasing-customer-value-through-iot-for-jcb-india/\n" + 
				"https://wiprodigital.com#CascontntAggr\n" + 
				"https://wiprodigital.com/tag/robo-advice/\n" + 
				"https://wiprodigital.com/get-in-touch#wddi-contact\n" + 
				"https://wiprodigital.com/partners/biz2credit/\n" + 
				"https://wiprodigital.com/join-our-team#wdwork-vid\n" + 
				"https://wiprodigital.com/join-our-team/#masthead\n" + 
				"https://wiprodigital.com/2018/08/28/continuous-iteration-not-preparation/\n" + 
				"https://wiprodigital.com/\n" + 
				"https://wiprodigital.com/join-our-team/#wdcareers_team\n" + 
				"https://wiprodigital.com#EvtcontntAggr\n" + 
				"https://wiprodigital.com/tag/ux/\n" + 
				"https://wiprodigital.com/#EvtcontntAggr\n" + 
				"https://wiprodigital.com/2017/06/01/a-crisis-in-digital-transformation-a-survey-of-400-us-senior-executives/\n" + 
				"https://wiprodigital.com/what-we-do/#work-three-circles-row\n" + 
				"https://wiprodigital.com/#NewcontntAggr\n" + 
				"https://wiprodigital.com/join-our-team/#wdcareers_jobs\n" + 
				"https://wiprodigital.com/tag/intelligent-interactions/\n" + 
				"https://wiprodigital.com#NewcontntAggr\n" + 
				"https://wiprodigital.com/2017/02/21/whitepaper-introducing-aws-to-a-large-fs-organisation/\n" + 
				"https://wiprodigital.com/what-we-do#wdwork_cases\n" + 
				"https://wiprodigital.com/tag/motion-design/\n" + 
				"https://wiprodigital.com/news/wipro-named-global-leader-digital-transformation-strategy-consulting/\n" + 
				"https://wiprodigital.com/cases/case-study-speaking-from-the-heart/\n" + 
				"https://wiprodigital.com/#InscontntAggr\n" + 
				"https://wiprodigital.com/cases/a-design-system-to-enable-future-growth/\n" + 
				"https://wiprodigital.com/partners/xebia-labs/\n" + 
				"https://wiprodigital.com/cases/progressive-metering-a-utilitys-strategic-move-into-predictive-planning/\n" + 
				"https://wiprodigital.com/cases/digital-transformation-a-case-study-of-technological-intervention/\n" + 
				"https://wiprodigital.com/tag/intelligent-app/\n" + 
				"https://wiprodigital.com/tag/ecommerce/\n" + 
				"https://wiprodigital.com/what-we-do#work-three-circles-row\n" + 
				"https://wiprodigital.com/events/wipro-digital-is-a-platinum-sponsor-at-springone-platform-2018/\n" + 
				"https://wiprodigital.com/who-we-are\n" + 
				"https://wiprodigital.com/news/vendor-profile-digital-cx-iot-by-pac-2018/\n" + 
				"https://wiprodigital.com/tag/agile-delivery/\n" + 
				"https://wiprodigital.com/tag/blueprints/\n" + 
				"https://wiprodigital.com/get-in-touch\n" + 
				"https://wiprodigital.com/?s=&post_type[]=news\n" + 
				"https://wiprodigital.com/tag/ux-design/\n" + 
				"https://wiprodigital.com/tag/experimenting/\n" + 
				"https://wiprodigital.com/tag/insights/\n" + 
				"https://wiprodigital.com/what-we-do\n" + 
				"https://wiprodigital.com/designit-approach\n" + 
				"https://wiprodigital.com/partners/apporbit/\n" + 
				"https://wiprodigital.com/2018/08/07/making-financial-advice-affordable-to-the-masses-in-the-age-of-digitalisation/\n" + 
				"https://wiprodigital.com/tag/cmo/\n" + 
				"https://wiprodigital.com/2018/08/30/rethinking-service-blueprints-for-agile-delivery/\n" + 
				"https://wiprodigital.com/who-we-are/#wdteam_leaders\n" + 
				"https://wiprodigital.com/partners/scalearc/\n" + 
				"https://wiprodigital.com/tag/digital-transformation/\n" + 
				"https://wiprodigital.com/join-our-team/#wdwork-vid\n" + 
				"https://wiprodigital.com/tag/marketing-technology/\n" + 
				"https://wiprodigital.com/tag/data/\n" + 
				"https://wiprodigital.com/people/jayraj-nair/\n" + 
				"https://wiprodigital.com/events/join-wipro-digital-for-two-iot-panels-at-world-economic-forum-wef/\n" + 
				"https://wiprodigital.com/what-we-do/#wdwork_cases\n" + 
				"https://wiprodigital.com/2017/12/05/making-business-transformation-a-smash-hit/\n" + 
				"https://wiprodigital.com/tag/financial-planning/\n" + 
				"https://wiprodigital.com/privacy-policy\n" + 
				"https://wiprodigital.com/join-our-team#wdcareers_jobs\n" + 
				"https://wiprodigital.com/2018/09/18/sound-design-from-the-ears-of-a-motion-designer/\n" + 
				"https://wiprodigital.com/tag/mobile-apps/\n" + 
				"https://wiprodigital.com/#AllcontntAggr\n" + 
				"https://wiprodigital.com/?s=&post_type[]=cases\n" + 
				"https://wiprodigital.com/events/wipro-digital-designit-at-idc-european-digital-executive-summit-2018/\n" + 
				"https://wiprodigital.com#AllcontntAggr\n" + 
				"https://wiprodigital.com/cases/in24/\n" + 
				"https://wiprodigital.com/2018/09/05/invest-in-brains/\n" + 
				"https://wiprodigital.com/news/pacs-european-trend-study-digital-industrial-revolution-with-predictive-maintenance/\n" + 
				"https://wiprodigital.com/tag/fintechs/\n" + 
				"https://wiprodigital.com/partners/episerver/\n" + 
				"https://wiprodigital.com/?s=&post_type[]=post\n" + 
				"https://wiprodigital.com/tag/sound-design/\n" + 
				"https://wiprodigital.com/who-we-are#wdteam_leaders\n" + 
				"https://wiprodigital.com/?s=&post_type[]=events\n" + 
				"https://wiprodigital.com/events/teresa-brazen-senior-director-cooper-at-design-thinking-2018/\n" + 
				"https://wiprodigital.com/tag/design/\n" + 
				"https://wiprodigital.com/tag/martech/\n" + 
				"http://wiprodigital.com\n" + 
				"https://wiprodigital.com/who-we-are/#wdteam-vid\n" + 
				"https://wiprodigital.com/2017/08/08/rise-mobile-banking-defining-success-customer-led-revolution/\n" + 
				"https://wiprodigital.com/tag/apis/\n" + 
				"https://wiprodigital.com/who-we-are/#wdteam_meetus\n" + 
				"https://wiprodigital.com/partners/datastax/\n" + 
				"https://wiprodigital.com/what-we-think\n" + 
				"https://wiprodigital.com/news/new-survey-highlights-leadership-crisis-digital-transformation/\n" + 
				"https://wiprodigital.com/2018/08/09/turning-data-into-consumer-growth/\n" + 
				"https://wiprodigital.com/tag/banking-customer/\n" + 
				"https://wiprodigital.com\n" + 
				"https://wiprodigital.com/2018/06/19/3-conversations-around-innovation-and-entrepreneurship-from-collision-2018/\n" + 
				"https://wiprodigital.com/tag/testing/\n" + 
				"https://wiprodigital.com/get-in-touch/#wddi-contact\n" + 
				"https://wiprodigital.com/2018/07/24/optimising-digital-experiences-start-with-these-2-golden-rules/\n" + 
				"https://wiprodigital.com/tag/optimisation/\n" + 
				"https://wiprodigital.com/news/wipro-digital-acquire-cooper-leader-ux-interaction-design-expand-designits-capabilities/\n" + 
				"https://wiprodigital.com/2016/09/01/do-we-need-fewer-apps-and-more-experiences/\n" + 
				"https://wiprodigital.com/what-we-do#wdwork_partners\n" + 
				"https://wiprodigital.com/partners/adobe/\n" + 
				"https://wiprodigital.com/partners/cisco/\n" + 
				"https://wiprodigital.com/events/nate-clinton-managing-director-cooper-at-sf-design-week-2018/\n" + 
				"https://wiprodigital.com/2018/09/20/the-emergence-of-intelligent-mobile-apps/\n" + 
				"https://wiprodigital.com#InscontntAggr\n" + 
				"https://wiprodigital.com/get-in-touch#wddi-locations\n" + 
				"https://wiprodigital.com/tag/financial-advice/\n" + 
				"https://wiprodigital.com/2018/09/13/17-things-to-follow-for-designers-who-want-to-understand-how-tech-will-shape-our-future/\n" + 
				"https://wiprodigital.com/case-study-redefining-newborn-care-for-volusense/\n" + 
				"https://wiprodigital.com/tag/builditwiprodigital/\n" + 
				"https://wiprodigital.com/2018/07/19/caas-101-the-history-and-concepts-behind-content-as-a-service/\n" + 
				"https://wiprodigital.com/partners/maxicaster/\n" + 
				"https://wiprodigital.com/designit-approach/\n" + 
				"https://wiprodigital.com/partners/aws/\n" + 
				"https://wiprodigital.com/who-we-are/#masthead\n" + 
				"https://wiprodigital.com/partners/cloudvelox/\n" + 
				"https://wiprodigital.com/join-our-team\n" + 
				"https://wiprodigital.com/cases/in24-bankside/\n" + 
				"https://wiprodigital.com/cases/in24-insure/\n" + 
				"https://wiprodigital.com/partners/microsoft/\n" + 
				"https://wiprodigital.com/2018/08/16/digital-transformation-reach-your-destination-faster-with-a-forms-management-platform/\n" + 
				"https://wiprodigital.com/news/the-state-of-martech-study/\n" + 
				"https://wiprodigital.com/join-our-team#wdcareers_team\n" + 
				"https://wiprodigital.com/what-we-do/#wdwork_partners\n" + 
				"Media/Static Links: \n" + 
				"iframe: <https://player.vimeo.com/video/110058046?html5=1&title=0&byline=0&portrait=0&autoplay=0>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/06/Author-Tim.jpeg> x (Timothy France-mass)\n" + 
				"script: <https://s17776.pcdn.co/wp-content/plugins/advanced-responsive-video-embedder/public/arve.min.js?ver=8.9.6>\n" + 
				"script: <https://js.hsforms.net/forms/v2.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/callme5-1024x574.png> 1024x574 ()\n" + 
				"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/designit_logo.png> x (Get In Touch – Wipr)\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/isotope.pkgd.min.js>\n" + 
				"script: <https://s17776.pcdn.co/wp-content/plugins/wp-scroll-depth/js/jquery-scrolldepth/jquery.scrolldepth.min.js?ver=4.9.7>\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wdblog.js>\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/dicontact.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/09/Author-Cameron-Winchester.jpg> x (Cameron Winchester)\n" + 
				"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo.png> x (Digital Transformat)\n" + 
				"iframe: <https://player.vimeo.com/video/130310711?title=0&byline=0&portrait=0>\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wiprodigital.js>\n" + 
				"iframe: <https://player.vimeo.com/video/173961044?title=0&byline=0&portrait=0>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/callme7-1024x286.png> 1024x286 ()\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/06/Case-Study-A-Design-System-to-Enable-Future-Growth-1-1024x714.png> 1024x714 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/plugins/ajax-load-more/core/dist/js/ajax-load-more.min.js?ver=3.5.1>\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wdpost.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/04/Events-Nate-Clinton-Managing-Director-Cooper-at-SF-Design-Week-2018-300x183.jpg> 300x183 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-includes/js/jquery/jquery.js?ver=1.12.4>\n" + 
				"iframe: <https://www.youtube.com/embed/P_Oh7HizY5I?rel=0&controls=0&showinfo=0>\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/jquery-1.12.0.min.js>\n" + 
				"iframe: <https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/61873180&color=ff5500>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/06/Insights-PACs-European-Trend-Study-Digital-Industrial-Revolution-with-Predictive-Maintenance-1-1024x425.png> 1024x425 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wow.min.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/01/Author-Andy-Coghlan.jpg> x (Andy Coghlan)\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/bootstrap.min.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/03/Events-Wipro-Digital-at-Design-Thinking-2018-1-300x187.png> 239x149 ()\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/08/Wipro-Digital-Cookies-05-28-18.png> 1241x2962 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/mason.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/08/Paul-Harrison-Rethinking-Service-Blueprints-for-Agile-Delivery-1.png> 824x558 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wdcareers-new.js?ver=1528824044>\n" + 
				"iframe: <https://player.vimeo.com/video/279286339?title=0&byline=0&portrait=0>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/volusensemonitormontage-1024x512.png> 1024x512 ()\n" + 
				"script: <https://js.hs-scripts.com/621527.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/08/Paul-Harrison-Rethinking-Service-Blueprints-for-Agile-Delivery-3.jpg> 3202x2584 ()\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/callme10-1024x574.png> 1024x574 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wdhome.js>\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wdwork.js>\n" + 
				"iframe: <https://player.vimeo.com/video/279286694?title=0&byline=0&portrait=0>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/callme1-1024x574.png> 1024x574 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/modernizr.min.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/designit_logo.png> x (Designit)\n" + 
				"script: <https://s17776.pcdn.co/wp-includes/js/wp-embed.min.js?ver=4.9.7>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/callme8-1024x574.png> 1024x574 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/wdteam.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/09/Author-Rishabh-Srivastava.jpg> x (Shankar Kumarasamy)\n" + 
				"img: <https://dc.ads.linkedin.com/collect/?pid=225025&fmt=gif> 1x1 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/nice-select.min.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/06/Case-Study-A-Design-System-to-Enable-Future-Growth-2-1024x797.png> 1024x797 ()\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/08/Author-Paul-Harrison.jpg> x (Paul Harrison)\n" + 
				"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo.png> x (Get In Touch – Wipr)\n" + 
				"iframe: <https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/103198955&color=ff5500>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/Screen-Shot-2016-03-30-at-1.09.01-PM-1024x176.png> 1024x176 ()\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/08/Case-Study-Progressive-Metering-A-Utility’s-Strategic-Move-into-Predictive-Planning-1.png> 814x525 (Case Study - Progre)\n" + 
				"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo.png> x (wipro digital)\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/inview.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/callme2.gif> 1200x673 ()\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/06/Author-George-I.jpg> x (George Ioannou)\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/callme12-1024x574.png> 1024x574 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/scripts/jquery.slicknav.min.js>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2016/03/callme6-1024x574.png> 1024x574 ()\n" + 
				"script: <https://s17776.pcdn.co/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.4.1>\n" + 
				"img: <https://s17776.pcdn.co/wp-content/themes/wiprodigital/images/logo-dk.png> x (wipro digital)\n" + 
				"img: <https://s17776.pcdn.co/wp-content/uploads/2018/08/Paul-Harrison-Rethinking-Service-Blueprints-for-Agile-Delivery-2.jpg> 4168x2422 ()\n" + 
				"OutSide Links:\n" + 
				"https://twitter.com/andycoghlan\n" + 
				"http://www.pac-online.com/blog\n" + 
				"https://twitter.com/davidhussman/status/1020482836825018371?ref_src=twsrc%5Etfw\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fa-design-system-to-enable-future-growth%2F\n" + 
				"https://www.eventbrite.com/e/conversations-with-machines-tickets-44811242633\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fjoin-wipro-digital-for-two-iot-panels-at-world-economic-forum-wef%2F&title=Join Wipro Digital for two IoT panels at World Economic Forum (WEF)&summary=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fjoin-wipro-digital-for-two-iot-panels-at-world-economic-forum-wef%2F&source=wiprodigital.com\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-digital-is-a-platinum-sponsor-at-springone-platform-2018%2F&hashtags=wiprodigital\n" + 
				"https://www.callme.dk/\n" + 
				"https://www.linkedin.com/in/andycoghlan\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fcase-study-speaking-from-the-heart%2F\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F28%2Fcontinuous-iteration-not-preparation%2F\n" + 
				"http://www.ciab.org.br/\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F09%2Fturning-data-into-consumer-growth%2F\n" + 
				"mailto:rabeb@ruderfinn.com\n" + 
				"https://twitter.com/hashtag/springone\n" + 
				"https://www.pac-online.com/wipro-digital-new-way-working\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fteresa-brazen-senior-director-cooper-at-design-thinking-2018%2F\n" + 
				"https://twitter.com/madhu7173\n" + 
				"https://twitter.com/timfrancemassey\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fthe-state-of-martech-study%2F&title=New MarTech Study Shows Marketers May be Overestimating Ability to Execute and Deliver Results from Their Investments&summary=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fthe-state-of-martech-study%2F&source=wiprodigital.com\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-digital-designit-at-idc-european-digital-executive-summit-2018%2F&title=Wipro Digital & Designit at IDC European Digital Executive Summit 2018&summary=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-digital-designit-at-idc-european-digital-executive-summit-2018%2F&source=wiprodigital.com\n" + 
				"https://www.linkedin.com/in/georgeioannou\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fcase-study-redefining-newborn-care-for-volusense%2F&title=VoluSense: Redefining newborn care&summary=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fcase-study-redefining-newborn-care-for-volusense%2F&source=wiprodigital.com\n" + 
				"https://twitter.com/nclinton\n" + 
				"https://www.pac-online.com/\n" + 
				"https://developers.google.com/analytics/devguides/collection/analyticsjs/cookie-usage\n" + 
				"https://twitter.com/wiprodigital\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F07%2Fmaking-financial-advice-affordable-to-the-masses-in-the-age-of-digitalisation%2F&hashtags=wiprodigital\n" + 
				"https://www.wipro.com/newsroom/press-releases/2018/new-martech-study-shows-marketers-may-be-overestimating-ability-to-execute-and-deliver-results-from-their-investments/\n" + 
				"https://www.linkedin.com/company/pierre-audoin-consultants?trk=company_logo\n" + 
				"https://www.linkedin.com/company/wipro-digital\n" + 
				"https://www.gartner.com/smarterwithgartner/2017-2018-gartner-cmo-spend-survey/\n" + 
				"https://www.ericsson.com/en/press-releases/2015/6/ericsson-mobility-report-70-percent-of-worlds-population-using-smartphones-by-2020\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-named-global-leader-digital-transformation-strategy-consulting%2F&title=Wipro Named  Global Leader in Digital Transformation Strategy and Consulting&summary=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-named-global-leader-digital-transformation-strategy-consulting%2F&source=wiprodigital.com\n" + 
				"https://twitter.com/WiproDigital\n" + 
				"https://www.pac-online.com/users/kholzhauserpac-onlinecom\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fnate-clinton-managing-director-cooper-at-sf-design-week-2018%2F&hashtags=wiprodigital\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fvendor-profile-digital-cx-iot-by-pac-2018%2F&hashtags=wiprodigital\n" + 
				"mailto:purnima.burman@wipro.com\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-digital-acquire-cooper-leader-ux-interaction-design-expand-designits-capabilities%2F&hashtags=wiprodigital\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F09%2F18%2Fsound-design-from-the-ears-of-a-motion-designer%2F&title=Sound Design: From the Ears of a Motion Designer&summary=https%3A%2F%2Fwiprodigital.com%2F2018%2F09%2F18%2Fsound-design-from-the-ears-of-a-motion-designer%2F&source=wiprodigital.com\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-announces-platinum-sponsorship-at-ciab-febraban-2018%2F&hashtags=wiprodigital\n" + 
				"https://www.linkedin.com/in/kiran-minnasandram-8616a76\n" + 
				"https://www.linkedin.com/in/rajramadas/\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fpacs-european-trend-study-digital-industrial-revolution-with-predictive-maintenance%2F&hashtags=wiprodigital\n" + 
				"http://www.theverge.com/2015/10/7/9455159/skype-sound-design-computer-audio-branding-longform\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F09%2Fturning-data-into-consumer-growth%2F&hashtags=wiprodigital\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fprogressive-metering-a-utilitys-strategic-move-into-predictive-planning%2F\n" + 
				"https://idcdigitalsummit.com/\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fnew-survey-highlights-leadership-crisis-digital-transformation%2F&hashtags=wiprodigital\n" + 
				"https://medium.designit.com/designers-toolkit-motion-design-storytelling-141b2fb28db4\n" + 
				"https://www.cooper.com/services\n" + 
				"https://www.linkedin.com/in/surbhat/\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F28%2Fcontinuous-iteration-not-preparation%2F&hashtags=wiprodigital\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F07%2Fmaking-financial-advice-affordable-to-the-masses-in-the-age-of-digitalisation%2F\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-named-global-leader-digital-transformation-strategy-consulting%2F&hashtags=wiprodigital\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F09%2F20%2Fthe-emergence-of-intelligent-mobile-apps%2F&title=The Emergence of Intelligent Mobile Apps&summary=https%3A%2F%2Fwiprodigital.com%2F2018%2F09%2F20%2Fthe-emergence-of-intelligent-mobile-apps%2F&source=wiprodigital.com\n" + 
				"https://marketing.adobe.com/resources/help/en_US/whitepapers/cookies/cookies_analytics.html\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fpacs-european-trend-study-digital-industrial-revolution-with-predictive-maintenance%2F\n" + 
				"http://www.cxp.fr/\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-digital-acquire-cooper-leader-ux-interaction-design-expand-designits-capabilities%2F\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fcase-study-redefining-newborn-care-for-volusense%2F\n" + 
				"mailto:dpo-europe@wipro.com\n" + 
				"https://twitter.com/ritupg\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-digital-designit-at-idc-european-digital-executive-summit-2018%2F&hashtags=wiprodigital\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-named-global-leader-digital-transformation-strategy-consulting%2F\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fthe-state-of-martech-study%2F&hashtags=wiprodigital\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F09%2F18%2Fsound-design-from-the-ears-of-a-motion-designer%2F&hashtags=wiprodigital\n" + 
				"https://designthinkingusa.iqpc.com\n" + 
				"https://twitter.com/kiran_minnas\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F07%2Fmaking-financial-advice-affordable-to-the-masses-in-the-age-of-digitalisation%2F&title=Making Financial Advice Affordable to the Masses in the Age of Digitalisation&summary=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F07%2Fmaking-financial-advice-affordable-to-the-masses-in-the-age-of-digitalisation%2F&source=wiprodigital.com\n" + 
				"https://www.linkedin.com/in/muniswamybharathi\n" + 
				"https://twitter.com/RajRamadas\n" + 
				"https://s17776.pcdn.co/wp-content/uploads/2018/08/Paul-Harrison-Rethinking-Service-Blueprints-for-Agile-Delivery-3.jpg\n" + 
				"https://engagecustomer.com/turning-data-into-consumer-growth/\n" + 
				"https://www.linkedin.com/in/jeremy-leach-6707963\n" + 
				"https://twitter.com/georgeioannou\n" + 
				"https://www.linkedin.com/in/rituparna-ghosh/\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F28%2Fcontinuous-iteration-not-preparation%2F&title=Continuous iteration, not preparation&summary=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F28%2Fcontinuous-iteration-not-preparation%2F&source=wiprodigital.com\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fpacs-european-trend-study-digital-industrial-revolution-with-predictive-maintenance%2F&title=PAC’s European Trend Study: Digital Industrial Revolution with Predictive Maintenance&summary=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fpacs-european-trend-study-digital-industrial-revolution-with-predictive-maintenance%2F&source=wiprodigital.com\n" + 
				"https://www.linkedin.com/in/alexbeal\n" + 
				"https://twitter.com/mrjfleach\n" + 
				"https://tools.google.com/dlpage/gaoptout\n" + 
				"https://www.aboutcookies.org/page/2/\n" + 
				"https://www.linkedin.com/in/alexander-kalinovsky-61a2862/\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F30%2Frethinking-service-blueprints-for-agile-delivery%2F\n" + 
				"https://www.linkedin.com/in/reynoldsalex\n" + 
				"mailto:info-uk@pac-online.com\n" + 
				"http://designit.com/\n" + 
				"http://www.sec.gov\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fnate-clinton-managing-director-cooper-at-sf-design-week-2018%2F&title=Nate Clinton, Managing Director, Designit at SF Design Week 2018&summary=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fnate-clinton-managing-director-cooper-at-sf-design-week-2018%2F&source=wiprodigital.com\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F09%2F20%2Fthe-emergence-of-intelligent-mobile-apps%2F&hashtags=wiprodigital\n" + 
				"https://www.martechadvisor.com/articles/collaboration-project-management/the-four-hottest-martech-skills-for-2018/\n" + 
				"mailto:m.milojevic@pac-online.com\n" + 
				"https://twitter.com/sudkes\n" + 
				"https://legal.twitter.com/ads-terms/us.html#twitterconversiontrackingprogramt%26cs\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fjoin-wipro-digital-for-two-iot-panels-at-world-economic-forum-wef%2F\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-digital-is-a-platinum-sponsor-at-springone-platform-2018%2F&title=Wipro Digital is a Platinum Sponsor at Pivotal’s SpringOne Platform 2018&summary=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-digital-is-a-platinum-sponsor-at-springone-platform-2018%2F&source=wiprodigital.com\n" + 
				"mailto:info@wipro.com\n" + 
				"https://twitter.com/SandhyaRArun\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fprogressive-metering-a-utilitys-strategic-move-into-predictive-planning%2F&title=Thames Water: A Strategic Move into Predictive Planning&summary=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fprogressive-metering-a-utilitys-strategic-move-into-predictive-planning%2F&source=wiprodigital.com\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-digital-is-a-platinum-sponsor-at-springone-platform-2018%2F\n" + 
				"https://twitter.com/Surbhat\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-digital-designit-at-idc-european-digital-executive-summit-2018%2F\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fthe-state-of-martech-study%2F\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-announces-platinum-sponsorship-at-ciab-febraban-2018%2F&title=Wipro Announces Platinum Sponsorship at CIAB FEBRABAN 2018&summary=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-announces-platinum-sponsorship-at-ciab-febraban-2018%2F&source=wiprodigital.com\n" + 
				"https://www.linkedin.com/in/manoj-madhusudhanan-b89a153\n" + 
				"https://realtimeboard.com/index/\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fprogressive-metering-a-utilitys-strategic-move-into-predictive-planning%2F&hashtags=wiprodigital\n" + 
				"https://designthinkingusa.iqpc.com/\n" + 
				"https://support.microsoft.com/en-in/help/17442/windows-internet-explorer-delete-manage-cookies\n" + 
				"https://www.cooper.com/journal/2014/8/service-blueprints-laying-the-foundation\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fteresa-brazen-senior-director-cooper-at-design-thinking-2018%2F&hashtags=wiprodigital\n" + 
				"http://barc.de/\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fwipro-announces-platinum-sponsorship-at-ciab-febraban-2018%2F\n" + 
				"http://www.pac-online/blog\n" + 
				"https://cdn.chiefmartec.com/wp-content/uploads/2018/04/marketing_technology_landscape_2018_slide.jpg\n" + 
				"http://pac.wiprodigital.com/\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fa-design-system-to-enable-future-growth%2F&hashtags=wiprodigital\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fnate-clinton-managing-director-cooper-at-sf-design-week-2018%2F\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-digital-acquire-cooper-leader-ux-interaction-design-expand-designits-capabilities%2F&title=Wipro Digital to Acquire Cooper, a Leader in UX and Interaction Design, and Expand Designit’s Capabilities&summary=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fwipro-digital-acquire-cooper-leader-ux-interaction-design-expand-designits-capabilities%2F&source=wiprodigital.com\n" + 
				"https://twitter.com/akalinovsky1\n" + 
				"http://www.thameswater.co.uk/\n" + 
				"https://www.linkedin.com/in/sudhirkesavan/\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fjoin-wipro-digital-for-two-iot-panels-at-world-economic-forum-wef%2F&hashtags=wiprodigital\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fcase-study-redefining-newborn-care-for-volusense%2F&hashtags=wiprodigital\n" + 
				"https://www.facebook.com/WiproDigital/\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F09%2Fturning-data-into-consumer-growth%2F&title=Turning Data Into Consumer Growth&summary=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F09%2Fturning-data-into-consumer-growth%2F&source=wiprodigital.com\n" + 
				"http://leader.wiprodigital.com/\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fa-design-system-to-enable-future-growth%2F&title=A Design System to Enable Future Growth&summary=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fa-design-system-to-enable-future-growth%2F&source=wiprodigital.com\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F30%2Frethinking-service-blueprints-for-agile-delivery%2F&hashtags=wiprodigital\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fvendor-profile-digital-cx-iot-by-pac-2018%2F&title=Vendor Profile: Digital CX by Pierre Audoin Consultants (PAC), 2018&summary=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fvendor-profile-digital-cx-iot-by-pac-2018%2F&source=wiprodigital.com\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fnew-survey-highlights-leadership-crisis-digital-transformation%2F\n" + 
				"https://twitter.com/CXPgroup\n" + 
				"mailto:shraboni.banerjee@wipro.com\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2F2018%2F09%2F20%2Fthe-emergence-of-intelligent-mobile-apps%2F\n" + 
				"https://www.linkedin.com/in/rajan-kohli-69365a\n" + 
				"http://www.pac-online.com\n" + 
				"https://www.facebook.com/customaudiences/app/tos/?ref=u2u\n" + 
				"https://medium.designit.com/\n" + 
				"http://bit.ly/2rIwO6Z\n" + 
				"http://bit.ly/2rWa6YM\n" + 
				"https://www.linkedin.com/in/mukundkalmanker\n" + 
				"https://business.twitter.com/en/help/ads-policies/other-policy-requirements/policies-for-conversion-tracking-and-tailored-audiences.html\n" + 
				"https://twitter.com/SRINIHG\n" + 
				"http://twitter.com/share?text=Wipro Digital&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fcase-study-speaking-from-the-heart%2F&hashtags=wiprodigital\n" + 
				"mailto:mulliganb@ruderfinn.com\n" + 
				"https://s17776.pcdn.co/wp-content/uploads/2016/08/Wipro-Digital-Cookies-05-28-18.png\n" + 
				"https://twitter.com/rajan_kohli1\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fteresa-brazen-senior-director-cooper-at-design-thinking-2018%2F&title=Teresa Brazen, Senior Director, Cooper at Design Thinking 2018&summary=https%3A%2F%2Fwiprodigital.com%2Fevents%2Fteresa-brazen-senior-director-cooper-at-design-thinking-2018%2F&source=wiprodigital.com\n" + 
				"https://twitter.com/TeresaBrazen\n" + 
				"https://www.linkedin.com/in/srinivasaahg\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fvendor-profile-digital-cx-iot-by-pac-2018%2F\n" + 
				"https://www.linkedin.com/in/rahul-shah-218a782\n" + 
				"https://t.co/x334Sp05s2\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F30%2Frethinking-service-blueprints-for-agile-delivery%2F&title=Rethinking Service Blueprints for Agile Delivery&summary=https%3A%2F%2Fwiprodigital.com%2F2018%2F08%2F30%2Frethinking-service-blueprints-for-agile-delivery%2F&source=wiprodigital.com\n" + 
				"https://s17776.pcdn.co/wp-content/uploads/2018/08/Paul-Harrison-Rethinking-Service-Blueprints-for-Agile-Delivery-2.jpg\n" + 
				"https://2018.sfdesignweek.org/\n" + 
				"https://www.linkedin.com/legal/sas-terms#additional-terms-for-optional-conversion-tracking\n" + 
				"https://www.linkedin.com/in/sandhyararun\n" + 
				"https://www.linkedin.com/in/mnmurthy\n" + 
				"http://www.volusense.com/\n" + 
				"https://support.google.com/chrome/answer/95647?co=GENIE.Platform=Desktop&hl=en\n" + 
				"https://twitter.com/rahulshahtweets\n" + 
				"https://support.mozilla.org/en-US/kb/enable-and-disable-cookies-website-preferences\n" + 
				"http://www.worldfinancialreview.com/\n" + 
				"mailto:vipin.nair1@wipro.com\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fcase-study-speaking-from-the-heart%2F&title=Call Me: Speaking From the Heart&summary=https%3A%2F%2Fwiprodigital.com%2Fcases%2Fcase-study-speaking-from-the-heart%2F&source=wiprodigital.com\n" + 
				"https://springoneplatform.io/\n" + 
				"https://designit.com\n" + 
				"https://legal.twitter.com/ads-terms/us.html#twitteradsprogramt%26cs\n" + 
				"https://twitter.com/beal_a\n" + 
				"https://www.facebook.com/dialog/share?app_id=785186781583414&display=popup&href=https%3A%2F%2Fwiprodigital.com%2F2018%2F09%2F18%2Fsound-design-from-the-ears-of-a-motion-designer%2F\n" + 
				"http://vision2030.wiprodigital.com/\n" + 
				"https://www.facebook.com/business/help/651294705016616?helpref=faq_content\n" + 
				"https://voicebot.ai/2018/04/03/over-half-of-smartphone-owners-use-voice-assistants-siri-leads-the-pack/\n" + 
				"http://www.linkedin.com/shareArticle?mini=true&url=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fnew-survey-highlights-leadership-crisis-digital-transformation%2F&title=New Survey Highlights Leadership Crisis in Digital Transformation&summary=https%3A%2F%2Fwiprodigital.com%2Fnews%2Fnew-survey-highlights-leadership-crisis-digital-transformation%2F&source=wiprodigital.com";
		try {
			String returnString = crawler.crawl("http://wiprodigital.com", 2);
//			System.out.println(returnString);
			assertEquals(expectedReturn, returnString);
		} catch (MalformedURLException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
