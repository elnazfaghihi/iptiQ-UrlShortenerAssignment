package api;

import model.Url;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUrlStoreService implements IUrlStoreService {

    public static InMemoryUrlStoreService INSTANCE = null;
    private final ConcurrentHashMap<String, Url> urlConcurrentHashMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> originalUrlConcurrentHashMap = new ConcurrentHashMap<>();

    private InMemoryUrlStoreService() {

    }

    public static InMemoryUrlStoreService getInstance() {
        return INSTANCE = INSTANCE == null ? new InMemoryUrlStoreService() : INSTANCE;
    }

    @Override
    public Url findByShortenUrl(String shortenUrl) {
        return urlConcurrentHashMap.get(shortenUrl);
    }

    @Override
    public void storeUrl(String shortenUrl, Url url) {
        originalUrlConcurrentHashMap.put(url.getOriginalUrl(), shortenUrl);
        urlConcurrentHashMap.put(shortenUrl, url);
    }

    @Override
    public void removeUrl(String shortenUrl, Url persistedUrl) {
        urlConcurrentHashMap.remove(shortenUrl);
        originalUrlConcurrentHashMap.remove(persistedUrl.getOriginalUrl());
    }

    @Override
    public String findByOriginalUrl(String originalUrl) {
        return originalUrlConcurrentHashMap.get(originalUrl);
    }
}
