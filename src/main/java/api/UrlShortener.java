package api;

import exception.SystemRuntimeException;
import model.Url;
import model.UrlDTO;
import utils.CharRandomGenerator;
import validaton.UrlValidator;

import java.util.Date;

public class UrlShortener {

    private final IUrlStoreService urlStoreService = InMemoryUrlStoreService.getInstance();

    private static final String DOMAIN = "http://sho.com/";

    public String generateShortenUrlRandomly(UrlDTO urlDTO) {
        return checkIfShortenUrlExist(urlDTO, false);
    }

    public String generateShortenUrlBySeoKeyword(UrlDTO urlDTO) {

        if (urlDTO.getSeoKeyword() == null || urlDTO.getSeoKeyword().isEmpty())
            throw new SystemRuntimeException("Seo keyword is blank");

        return checkIfShortenUrlExist(urlDTO, true);
    }

    public String findOriginalUrl(String shortenUrl) {

        Url persistedShortenUrl = urlStoreService.findByShortenUrl(shortenUrl);
        if (persistedShortenUrl == null) {
            throw new SystemRuntimeException("No original url was found with this url");
        }
        if (isUrlExpired(shortenUrl, persistedShortenUrl)) {
            throw new SystemRuntimeException("Shorten url has expired");
        }
        return persistedShortenUrl.getOriginalUrl();
    }

    private String checkIfShortenUrlExist(UrlDTO urlDTO, boolean isSeoKeyword) {
        this.isUrlValid(urlDTO.getUrl());

        String shortenUrl = urlStoreService.findByOriginalUrl(urlDTO.getUrl());
        if (shortenUrl != null) {
            Url persistedShortenUrl = urlStoreService.findByShortenUrl(shortenUrl);
            if (persistedShortenUrl != null && !isUrlExpired(shortenUrl, persistedShortenUrl)) return shortenUrl;
        }

        String shortenPart = isSeoKeyword ? urlDTO.getSeoKeyword() : encodeUrl();
        return validateAndSave(shortenPart, urlDTO);
    }

    private String validateAndSave(String shortenUrl, UrlDTO urlDTO) {

        shortenUrl = DOMAIN + shortenUrl;

        synchronized (this) {
            urlStoreService.storeUrl(shortenUrl, new Url(urlDTO.getUrl(), calculateExpiration(urlDTO.getUrlValiditySeconds())));
        }
        return shortenUrl;
    }

    private void isUrlValid(String url) {
        UrlValidator.getInstance().validateURL(url);
    }

    private String encodeUrl() {
        return CharRandomGenerator.generateRandomPassword(6);
    }

    private Date calculateExpiration(Integer urlValiditySeconds) {
        return new Date(System.currentTimeMillis() + urlValiditySeconds);
    }

    private boolean isUrlExpired(String shortenUrl, Url persistedUrl) {

        if (persistedUrl.getExpirationDate().before(new Date())) {
            urlStoreService.removeUrl(shortenUrl, persistedUrl);
            return true;
        }
        return false;
    }
}
