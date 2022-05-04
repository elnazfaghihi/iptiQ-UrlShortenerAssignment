package api;

import exception.SystemRuntimeException;
import model.UrlDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UrlShortenerTest {
    UrlShortener urlShortener;
    private static final String DOMAIN = "http://sho.com/";

    @BeforeAll
    public void init() {
        urlShortener = new UrlShortener();
    }

    @Test
    public void testShortenUrlBySeoKeywordWithInvalidUrl_thenThrowSystemRuntimeException() {
        assertThrows(SystemRuntimeException.class, () -> urlShortener.generateShortenUrlBySeoKeyword(new UrlDTO("http://***", 600)));
    }

    @Test
    public void testShortenUrlBySeoKeywordWithBlankKeyword_thenThrowSystemRuntimeException() {
        assertThrows(SystemRuntimeException.class, () -> urlShortener.generateShortenUrlBySeoKeyword(new UrlDTO("http://somews.com/abc/1/page.html", 600)));
    }

    @Test
    public void testShortenUrlBySeoKeyword_thenOk() {
        assertEquals(DOMAIN + "myLovelyPage", urlShortener.generateShortenUrlBySeoKeyword(new UrlDTO("http://somews.com/abc/1/page.html", "myLovelyPage", 600)));
    }

    @Test
    public void testFindOriginalUrlByExpiredShortenUrl_thenThrowSystemRuntimeException() throws InterruptedException {
        String persistedShortenUrl = urlShortener.generateShortenUrlRandomly(new UrlDTO("http://somews.com/abc/1/page.html", 1));
        Thread.sleep(1000);
        assertThrows(SystemRuntimeException.class, () -> urlShortener.findOriginalUrl(persistedShortenUrl));
    }

    @Test
    public void testFindOriginalUrlByNotExistShortenUrl_thenThrowSystemRuntimeException() {
        assertThrows(SystemRuntimeException.class, () -> urlShortener.findOriginalUrl("http://somews.com/A6MD7C"));
    }

    @Test
    public void testShortenUrlRandomlyChars_thenOk() {
        String shortenUrl = urlShortener.generateShortenUrlRandomly(new UrlDTO("http://somews.com/abc/1/page.html", 6000));
        assertEquals(shortenUrl, urlShortener.generateShortenUrlRandomly(new UrlDTO("http://somews.com/abc/1/page.html", 6000)));
    }
}
