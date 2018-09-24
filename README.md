<h1><b>WebCrawler</b></h1>

<h2>Overview</h2>

Please write a simple web crawler in a language of your choice.  (Please be aware that we favor Apple hardware, so Microsoft based solutions require the use of containers or virtual machines).

The crawler should be limited to one domain. Given a starting URL – say [http://wiprodigital.com](http://wiprodigital.com) - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.

The output should be a simple structured site map (this does not need to be a traditional XML sitemap - just some sort of output to reflect what your crawler has discovered) showing links to other pages under the same domain, links to external URLs, and links to static content such as images for each respective page.

Please provide a README.md file that explains how to build, test, and run your solution. Also, detail anything further that you would like to achieve with more time.

Once done, please make your solution available on Github and forward the link. Where possible please include your commit history to provide visibility of your thinking and working practice.

<h2>Build</h2>

To build the executable jar file (all files should be in the same directory):
<ul>
	<li>Download the jsoup-1.10.2.jar file</li>
	<li>Locate or create the MANIFEST.mf file</li>
	<ul>
		<li>Manifest-Version: 0.1.0</li>
		<li>Main-Class: WebCrawler</li>
	</ul>
	<li>Create class file for WebCrawler </li>
	<ul>
		<li>javac WebCrawler.java</li>
	</ul>
	<li>Combine elements to create jar</li>
	<ul>
		<li>jar cmf MANIFEST.mf webcrawler.jar WebCrawler.java WebCrawler.class jsoup-1.10.2.jar</li>
	</ul>
</ul>

<h2>Test</h2>

A test file has been created using jupiter junit testing framework. This test file is found in the junit package of the WebCrawler project

The tests verify that:
<ul>
	<li>A null or empty String sent as the site parameter will return null</li>
	<li>A malformed site will return a MalformedURLException</li>
	<li>An incorrect syntax in the site parameter will return an URISyntaxException</li>
	<li>A correct site URL will return a String of all the links in the page</li>
	<li>A correct site URL with depth will return a String of all the links in the page to the depth specified</li>
</ul>

<h2>Running</h2>

The WebCrawler is run as an executable Java jar file. The website argument is the starting page of the website to crawl the optional depth parameter is the depth the WebCrawler is to run. If the depth parameter is not used then the depth is 1.

java -jar webcrawler.jar <website> [depth]
