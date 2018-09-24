<h1><b>WebCrawler</b></h1>

<h2>Overview</h2>

Please write a simple web crawler in a language of your choice.  (Please be aware that we favor Apple hardware, so Microsoft based solutions require the use of containers or virtual machines).

The crawler should be limited to one domain. Given a starting URL – say [http://wiprodigital.com](http://wiprodigital.com) - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.

The output should be a simple structured site map (this does not need to be a traditional XML sitemap - just some sort of output to reflect what your crawler has discovered) showing links to other pages under the same domain, links to external URLs, and links to static content such as images for each respective page.

Please provide a README.md file that explains how to build, test, and run your solution. Also, detail anything further that you would like to achieve with more time.

Once done, please make your solution available on Github and forward the link. Where possible please include your commit history to provide visibility of your thinking and working practice.

<h2>Summary</h2>

The design is simple enough. It uses the HTML library jsoup for parsing the HTML document and locating the required tags. The tags are then parsed to find the desired tags. The data is then stored in a number of HashSets. These HashSets are then processed to a String with an identify multi-word tag at the beginning of each list to identify what type of link is being displayed. 

I decided on using a return of a String to make it easier to display the result, but since the data is stored in a HashSet it is possible to return the data in any order or to return the data as a Collection of some sort. I also used HashSets because I didn't believe that the application needed to show more than one indicator of a link.

The main method is recursive, since that is the easiest way to keep track of the depth of the crawl. Also, the depth of the crawl is defaulted to just the first page, but it is possible for the user to modify the depth at their discretion. I set it up this way because of the recursion, even though since the first tenet of this problem is to only be following the links within the local domain, which by definition should have a finite structure, I didn't want a runaway recursion to cause this application to fail, unless the user deliberately push the process. By using a depth it becomes easier to modify this application to process more than just the local links.

There are a number of private helper methods to facilitate modifying the code as well as a static main method so that the application can be run from a class file or as a runnable jar file. I have given instructions on how to build the runnable jar file and run the application through it. 

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

A test file has been created using jupiter junit testing framework. This test file is found in the junit package of the WebCrawler project. There are only a few tests developed to keep the verification moderate for future development. Very few tests will need to be modified and any new features should only require a simple test or set of tests to verify those new features.

The tests verify that:
<ul>
	<li>A null or empty String sent as the site parameter will return null</li>
	<li>A malformed site will return a MalformedURLException</li>
	<li>An incorrect syntax in the site parameter will return an URISyntaxException</li>
	<li>A correct site URL will return a String of all the links in the page</li>
	<li>Calling the class without a depth parameter is the same as calling the class with a depth parameter of 1</li>
	<li>A correct site URL with depth will return a String of all the links in the page to the depth specified</li>
</ul>

<h2>Running</h2>

The WebCrawler is run as an executable Java jar file. The website argument is the starting page of the website to crawl the optional depth parameter is the depth the WebCrawler is to run. If the depth parameter is not used then the depth is 1.

<pre>java -jar webcrawler.jar &lt;website&gt; &lsqb;depth&rsqb;</pre>

<h2>TO DO</h2>

<ul>
<li>Better handling of Errors and Exceptions</li>
<li>Better display of data. With indicators as to the page that the link was discovered on and the order or line number that the link was discovered</li>
<li>Display the number of times any link was displayed on each page</li>
<li>Add more arguments to running the application so that extra functionality, such as only return local links or outside links, is possible</li>
<li>Handle mailto: URIs</li>
<li>Organize/Group and save malformed sites for further processing</li>
<li>Explore different data returns for found links (discover and print attributes)</li>
<li>Speed up/Optimize process for parsing sites</li>
<li>Explore other means to store data(links) and return results</li>
</ul>
