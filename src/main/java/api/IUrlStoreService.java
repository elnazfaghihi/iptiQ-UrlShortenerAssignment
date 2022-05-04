package api;

import model.Url;

public interface IUrlStoreService {
    Url findByShortenUrl(String shortenUrl);

    void storeUrl(String shortenUrl, Url url);

    void removeUrl(String shortenUrl, Url persistedUrl);

    String findByOriginalUrl(String originalUrl);
}
