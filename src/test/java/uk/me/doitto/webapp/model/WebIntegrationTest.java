/**
 * Copyright (C) 2010 Ian C. Smith <m4r35n357@gmail.com>
 *
 * This file is part of JavaEE6Webapp.
 *
 *     JavaEE6Webapp is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     JavaEE6Webapp is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with JavaEE6Webapp.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 
 */
package uk.me.doitto.webapp.model;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author ian
 *
 */
public class WebIntegrationTest {

    private WebClient webClient;
    
    private static final String HOST = "localhost";
    
    private static final String PORT = "9090";
    
    private static final String APP_URL = "http://" + HOST + ":" + PORT + "/jee6webapp";

    /**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
        webClient = new WebClient();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
        webClient.closeAllWindows();
	}

//    @Ignore
    @Test
    public void testIndex () {
    	String url = APP_URL + "/index.xhtml";
        try {
//            HtmlPage page = webClient.getPage(url);
//            assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
//
//            final String pageAsXml = page.asXml();
//            assertTrue(pageAsXml.contains("<body class=\"composite\">"));
//
//            final String pageAsText = page.asText();
//            assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
            url = APP_URL + "/next.xhtml";
            webClient.getPage(url);
            url = APP_URL + "/pages/another.xhtml";
            webClient.getPage(url);
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    }
    
    @Test
    public void testAppUserController () {
    	String url = APP_URL + "/pages/listUsers.xhtml";
        try {
            HtmlPage page = webClient.getPage(url);
            String pageAsText = page.asText();
            assertTrue("String not found!", pageAsText.contains("LIST"));
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    	url = APP_URL + "/pages/editUser.xhtml";
        try {
            HtmlPage page = webClient.getPage(url);
            String pageAsText = page.asText();
            assertTrue("String not found!", pageAsText.contains("EDIT"));
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    }
    
    @Test
    public void testArtistController () {
    	String url = APP_URL + "/pages/listArtists.xhtml";
        try {
            HtmlPage page = webClient.getPage(url);
            String pageAsText = page.asText();
            assertTrue("String not found!", pageAsText.contains("LIST"));
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    	url = APP_URL + "/pages/editArtist.xhtml";
        try {
            HtmlPage page = webClient.getPage(url);
            String pageAsText = page.asText();
            assertTrue("String not found!", pageAsText.contains("EDIT"));
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    }
    
    @Test
    public void testAlbumController () {
    	String url = APP_URL + "/pages/listAlbums.xhtml";
        try {
            HtmlPage page = webClient.getPage(url);
            String pageAsText = page.asText();
            assertTrue("String not found!", pageAsText.contains("LIST"));
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    	url = APP_URL + "/pages/editAlbum.xhtml";
        try {
            HtmlPage page = webClient.getPage(url);
            String pageAsText = page.asText();
            assertTrue("String not found!", pageAsText.contains("EDIT"));
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    }
    
    @Test
    public void testTrackController () {
    	String url = APP_URL + "/pages/listTracks.xhtml";
        try {
            HtmlPage page = webClient.getPage(url);
            String pageAsText = page.asText();
            assertTrue("String not found!", pageAsText.contains("LIST"));
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    	url = APP_URL + "/pages/editTrack.xhtml";
        try {
            HtmlPage page = webClient.getPage(url);
            String pageAsText = page.asText();
            assertTrue("String not found!", pageAsText.contains("EDIT"));
        } catch (Exception e) {
            fail("Unexpected exception in test. Is Glassfish Running at " + url + " ? ->" + e);
        }
    }
    
}
